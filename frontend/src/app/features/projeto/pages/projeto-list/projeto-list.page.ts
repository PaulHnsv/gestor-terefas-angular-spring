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
import { ProjetoDelete } from '../../components/projeto-delete/projeto-delete';

@Component({
  selector: 'app-projetos-list',
  standalone: true,
  templateUrl: './projeto-list.page.html',
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ProjetoCreate, ProjetoEdit, ProjetoDelete],
})
export class ProjetosListComponent implements OnInit {

  projetos$!: Observable<ViewState<ProjectResponse[]>>;
  refresh$ = new BehaviorSubject<void>(undefined);
  state: FormState = { status: 'idle', fieldErrors: {} };

  showDeleteModal = false;
  showSearchByIdModal: any;

  selectedProject: ProjectResponse | null = null;
  projectIdToDelete: string | null = null;
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

  openDeleteModal(projectId: string) {
    this.projectIdToDelete = projectId;
    this.showDeleteModal = true;
  }

  openEditModal(project: ProjectResponse) {
    this.selectedProject = project;
    this.showEditModal = true;
  }

  closeEditModal() {
    this.showEditModal = false;
    this.selectedProject = null;
  }

  closeDeleteModal() {
    this.showDeleteModal = false;
    this.selectedProject = null;
  }
}
