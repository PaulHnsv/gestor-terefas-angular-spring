import { Component, OnInit } from '@angular/core';
import { TarefaService } from '../../services/tarefa.service';
import { TaskResponse } from '../../models/tarefa.model';
import { CommonModule } from '@angular/common';
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

  state: FormState = { status: 'idle', fieldErrors: {} };

  showDeleteModal = false;
  showSearchByIdModal: any;

  selectedTask: TaskResponse | null = null;
  taskIdToDelete: string | null = null;
  showEditModal = false;

  constructor(private tarefaService: TarefaService) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
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

    this.tarefasFiltradas$ = combineLatest([this.tarefas$, this.statusFiltro$]).pipe(
      map(([viewState, filtro]) => {
        if (viewState.status !== 'success') return viewState;

        const filtradas =
          filtro === 0
            ? viewState.data
            : viewState.data.filter((t) => t.status === filtro);

        // Devolve um novo ViewState com os dados filtrados
        return { ...viewState, data: filtradas };
      }),
    );
  }

  openDeleteModal(taskId: string) {
    this.taskIdToDelete = taskId;
    this.showDeleteModal = true;
  }

  openEditModal(task: TaskResponse) {
    console.log('Tarefa selecionada para edição:', task);
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
