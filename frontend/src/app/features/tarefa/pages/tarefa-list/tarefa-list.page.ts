import { Component, OnInit } from '@angular/core';
import { TarefaService } from '../../services/tarefa.service';
import { TaskResponse } from '../../models/tarefa.model';
import { CommonModule } from '@angular/common';
import { ViewState } from 'src/app/shared/types/viewState.types';
import { ReactiveFormsModule } from '@angular/forms';
import {
  BehaviorSubject,
  catchError,
  combineLatest,
  map,
  Observable,
  of,
  shareReplay,
  startWith,
  switchMap,
} from 'rxjs';
import { FormsModule } from '@angular/forms';
import { TarefaCreate } from '../../components/tarefa-create/tarefa-create.component';
import { TarefaEdit } from '../../components/tarefa-edit/tarefa-edit.component';
import { TarefaDelete } from '../../components/tarefa-delete/tarefa-delete.component';
import { FormState } from 'src/app/shared/interfaces/FormState';
import { ProjectResponse } from 'src/app/features/projeto/models/projeto.model';
import { ProjetoService } from 'src/app/features/projeto/services/projeto.service';

@Component({
  selector: 'app-tarefas-list',
  standalone: true,
  templateUrl: './tarefa-list.page.html',
  imports: [CommonModule, ReactiveFormsModule, FormsModule, TarefaDelete, TarefaCreate, TarefaEdit],
})
export class TarefasListPage implements OnInit {
  tarefas$!: Observable<ViewState<TaskResponse[]>>;
  tarefasFiltradas$!: Observable<ViewState<TaskResponse[]>>;

  refresh$ = new BehaviorSubject<void>(undefined);
  statusFiltro$ = new BehaviorSubject<number>(0);
  projetoFiltro$ = new BehaviorSubject<string>('0');

  state: FormState = { status: 'idle', fieldErrors: {} };

  showDeleteModal = false;
  selectedTask: TaskResponse | null = null;
  taskIdToDelete: string | null = null;
  showEditModal = false;

  projetos$!: Observable<ProjectResponse[]>;

  constructor(private tarefaService: TarefaService, private projetoService: ProjetoService) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.projetos$ = this.projetoService.list();

    this.tarefas$ = this.refresh$.pipe(
      switchMap(() =>
        this.tarefaService.list().pipe(
          map((data) => ({ status: 'success', data }) as const),
          startWith({ status: 'loading' } as const),
          catchError(() =>
            of({
              status: 'error',
              message: 'Falha ao carregar tarefas.',
              fieldErrors: {},
            } as const),
          ),
        ),
      ),
      shareReplay(1),
    );

    this.tarefasFiltradas$ = combineLatest([
      this.tarefas$,
      this.statusFiltro$,
      this.projetoFiltro$,
    ]).pipe(
      map(([viewState, statusFiltro, projetoFiltro]) => {
        if (viewState.status !== 'success') return viewState;

        let filtradas = viewState.data;

        if (statusFiltro !== 0) {
          filtradas = filtradas.filter((t) => t.status === statusFiltro);
        }

        if (projetoFiltro !== '0') {
          filtradas = filtradas.filter((t) => String(t.project?.id) === projetoFiltro);
        }

        return { ...viewState, data: filtradas };
      }),
    );
  }

  onStatusChange(event: Event): void {
    this.statusFiltro$.next(+(event.target as HTMLSelectElement).value);
  }

  onProjetoChange(event: Event): void {
    this.projetoFiltro$.next((event.target as HTMLSelectElement).value);
  }

  openDeleteModal(taskId: string) {
    this.taskIdToDelete = taskId;
    this.showDeleteModal = true;
  }

  openEditModal(task: TaskResponse) {
    this.selectedTask = task;
    this.showEditModal = true;
  }

  closeEditModal() {
    this.showEditModal = false;
    this.selectedTask = null;
  }

  closeDeleteModal() {
    this.showDeleteModal = false;
    this.selectedTask = null;
  }
}
