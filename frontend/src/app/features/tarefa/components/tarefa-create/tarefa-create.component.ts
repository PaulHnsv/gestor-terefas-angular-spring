import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { tap, catchError, EMPTY, Observable } from 'rxjs';
import { TarefaService } from '../../services/tarefa.service';
import { Priority } from 'src/app/shared/interfaces/Priority';
import { Status } from 'src/app/shared/interfaces/Status';
import { ProjectResponse } from 'src/app/features/projeto/models/projeto.model';
import { ProjetoService } from 'src/app/features/projeto/services/projeto.service';
import { FormState } from 'src/app/shared/interfaces/FormState';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  selector: 'app-tarefa-create',
  templateUrl: './tarefa-create.component.html',
  styleUrl: './tarefa-create.component.scss',
})
export class TarefaCreate {
  @Output() created = new EventEmitter<void>();
  state: FormState = { status: 'idle', fieldErrors: {} };

  form;
  projetos$!: Observable<ProjectResponse[]>;

  constructor(
    private fb: FormBuilder,
    private tarefaService: TarefaService,
    private projetoService: ProjetoService,
  ) {
    this.form = this.createForm();
  }

  ngOnInit(): void {
    this.projetos$ = this.projetoService.list();
  }

  private createForm() {
    return this.fb.group({
      titulo: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(80),
      ]),
      descricao: this.fb.control<string | null>(null),
      prioridade: this.fb.control<Priority>('MEDIA', [Validators.required]),
      status: this.fb.control<Status>(1, [Validators.required]),
      projeto: this.fb.control<ProjectResponse | null>(null, [Validators.required]),
    });
  }

  get f() {
    return this.form.controls;
  }

  get loading() {
    return this.state.status === 'loading';
  }

  get fieldErrors() {
    return this.state.fieldErrors;
  }

  submit(): void {
    this.state = { status: 'idle', fieldErrors: {} };

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      this.state = {
        status: 'error',
        errorMessage: 'Corrija os campos obrigatórios.',
        fieldErrors: {},
      };
      return;
    }

    const payload = {
      title: (this.form.value.titulo ?? '').trim(),
      description: this.form.value.descricao?.trim() || null,
      priority: this.form.value.prioridade!,
      status: this.form.value.status!,
      project: this.form.value.projeto as ProjectResponse,
    };

    this.state = { status: 'loading', fieldErrors: {} };

    this.tarefaService
      .create(payload)
      .pipe(
        tap((created) => {
          this.form.reset({ titulo: '', descricao: '' });
          this.state = {
            status: 'success',
            successMessage: `Tarefa criada: ${created.title}`,
            fieldErrors: {},
          };
          this.created.emit();
        }),
        catchError((err) => {
          const body = err?.error;

          if (body?.fieldErrors) {
            this.state = {
              status: 'error',
              errorMessage: body.message || 'Erro de validação.',
              fieldErrors: body.fieldErrors,
            };
            return EMPTY;
          }

          this.state = {
            status: 'error',
            errorMessage: body?.message || 'Falha ao criar tarefa.',
            fieldErrors: {},
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
