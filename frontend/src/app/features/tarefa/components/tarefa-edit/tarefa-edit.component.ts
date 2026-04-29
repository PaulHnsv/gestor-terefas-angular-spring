import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TarefaService } from '../../services/tarefa.service';
import { TaskResponse, TaskRequest } from '../../models/tarefa.model';
import { CommonModule } from '@angular/common';
import { ProjectResponse } from 'src/app/features/projeto/models/projeto.model';
import { Priority } from 'src/app/shared/interfaces/Priority';
import { Status } from 'src/app/shared/interfaces/Status';
import { FormState } from 'src/app/shared/interfaces/FormState';
import { catchError, EMPTY, Observable, tap } from 'rxjs';
import { ProjetoService } from 'src/app/features/projeto/services/projeto.service';


@Component({
  standalone: true,
  selector: 'app-tarefa-edit',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './tarefa-edit.component.html',
  styleUrl: './tarefa-edit.component.scss',
})
export class TarefaEdit implements OnInit {
  @Input() tarefa: TaskResponse | null = null;

  @Output() updated = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  editForm;
  state: FormState = { status: 'idle', fieldErrors: {} };
  projetos$!: Observable<ProjectResponse[]>;

  constructor(
    private fb: FormBuilder,
    private tarefaService: TarefaService,
    private projetoService: ProjetoService,
  ) {
    this.editForm = this.createForm();
  }
  ngOnInit(): void {
    this.projetos$ = this.projetoService.list();
  }

  get form() {
    return this.editForm.controls;
  }

  get loading() {
    return this.state.status === 'loading';
  }

  get fieldErrors() {
    return this.state.fieldErrors;
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['tarefa'] && this.tarefa) {
      this.editForm.patchValue({
        id: this.tarefa.id,
        titulo: this.tarefa.title,
        descricao: this.tarefa.description,
        projeto: this.tarefa.project,
        prioridade: this.tarefa.priority,
        status: this.tarefa.status,
      });
    }
  }

  private createForm() {
    return this.fb.group({
      id: this.fb.control<string>('', [Validators.required]),
      titulo: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(80),
      ]),
      descricao: this.fb.control<string | null>(null),
      prioridade: this.fb.control<Priority>('MEDIA', [Validators.required]),
      status: this.fb.control<Status>(1, [Validators.required]),
      projeto: this.fb.control<ProjectResponse | null>({value: null, disabled: true}, [Validators.required]),
    });
  }

  submitEdit() {
    if (!this.tarefa || this.editForm.invalid) {
      this.editForm.markAllAsTouched();
      return;
    }

    this.state = { status: 'loading', fieldErrors: {} };

    const editPayload: TaskRequest = {
      title: (this.editForm.value.titulo ?? '').trim(),
      project: this.tarefa.project, // Projeto não editável aqui, manter o original
      priority: this.editForm.value.prioridade!,
      status: this.editForm.value.status!,
      description: this.editForm.value.descricao?.trim() || null,
    };

    this.tarefaService.update(this.tarefa.id, editPayload)
      .pipe(
        tap(() => {
          this.updated.emit();
          this.close.emit();
        }),
        catchError((err) => {
          const body = err?.error;
          this.state = {
            status: 'error',
            errorMessage: body?.message || 'Falha ao atualizar tarefa.',
            fieldErrors: body?.fieldErrors ?? {},
          };
          return EMPTY;
        }),
      )
      .subscribe();
  }

  compareProject(a: ProjectResponse | null, b: ProjectResponse | null): boolean {
  return a?.id === b?.id;
  }
}
