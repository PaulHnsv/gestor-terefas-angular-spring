import { Component, OnInit } from '@angular/core';
import { ProjetoService } from '../../services/projeto.service';
import { ProjectResponse } from '../../models/projeto.model';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import {
  BehaviorSubject,
  catchError,
  map,
  Observable,
  of,
  shareReplay,
  startWith,
  switchMap,
} from 'rxjs';
import { FormsModule } from '@angular/forms';
import { ProjetoCreate } from '../../components/projeto-create/projeto-create';
import { ProjetoEdit } from '../../components/projeto-edit/projeto-edit';

@Component({
  selector: 'app-projetos-list',
  standalone: true,
  templateUrl: './projeto-list.component.html',
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ProjetoCreate, ProjetoEdit],
})
export class ProjetosListComponent implements OnInit {
  projetos$!: Observable<ViewState<ProjectResponse[]>>;
  refresh$ = new BehaviorSubject<void>(undefined);
  state: FormState = { status: 'idle', fieldErrors: {} };

  showDeleteModal: any;
  showSearchByIdModal: any;

  searchResult: any;
  searchId: any;

  selectedProject: ProjectResponse | null = null;
  showEditModal = false;

  constructor(private projetoService: ProjetoService) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.projetos$ = this.refresh$.pipe(
      switchMap(() =>
        this.projetoService.list().pipe(
          map((data) => ({ status: 'success', data }) as const),
          startWith({ status: 'loading' } as const),
          catchError(() =>
            of({
              status: 'error',
              message: 'Falha ao carregar projetos.',
              fieldErrors: {},
            } as const),
          ),
        ),
      ),
      shareReplay(1),
    );
  }

  /* Modal e ações de busca/exclusão por ID */
  closeSearchByIdModal() {
    this.showSearchByIdModal = false;
  }

  searchProjectById() {
    this.projetoService.get(this.searchId).subscribe({
      next: (project) => {
        this.searchResult = project;
      },
      error: () => {
        this.searchResult = null;
      },
    });
  }
  closeDeleteModal() {
    this.showDeleteModal = false;
  }
  deleteProjectById() {
    this.projetoService.delete(this.searchId).subscribe({
      next: () => {
        this.refresh$.next();
        this.closeDeleteModal();
      },
      error: () => {
        // Trate o erro conforme necessário
      },
    });
  }

  openDeleteModal(project: ProjectResponse) {
    this.searchId = project.id;
    this.showDeleteModal = true;
  }

  openEditModal(project: ProjectResponse) {
    console.log('Projeto selecionado para edição:', project);
    this.selectedProject = project;
    this.showEditModal = true;
  }

  closeEditModal() {
    this.showEditModal = false;
    this.selectedProject = null;
  }
}
