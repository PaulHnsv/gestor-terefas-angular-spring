import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BehaviorSubject, catchError, EMPTY, tap } from 'rxjs';
import { ProjetoService } from '../../services/projeto.service';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  standalone: true,
  selector: 'app-projeto-create',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './projeto-create.html',
  styleUrl: './projeto-create.scss',
})
export class ProjetoCreate implements OnInit {
@Output() created = new EventEmitter<void>();
  state: FormState = { status: 'idle', fieldErrors: {} };

  form;

  constructor(
    private fb: FormBuilder,
    private projetoService: ProjetoService,
  ) {
    this.form = this.createForm();
  }

  ngOnInit(): void {
  }

  private createForm() {
    return this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(80)]],
      descricao: [''],
    });
  }

  get nameCtrl() {
    return this.form.controls.nome;
  }

  get loading() {
    return this.state.status === 'loading';
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
      name: (this.form.value.nome ?? '').trim(),
      description: this.form.value.descricao?.trim() || null,
    };

    this.state = { status: 'loading', fieldErrors: {} };

    this.projetoService
      .create(payload)
      .pipe(
        tap((created) => {
          this.form.reset({ nome: '', descricao: '' });
          this.state = {
            status: 'success',
            successMessage: `Projeto criado: ${created.name}`,
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
            errorMessage: body?.message || 'Falha ao criar projeto.',
            fieldErrors: {},
          };
          return EMPTY;
        }),
      )
      .subscribe();
  }
}
