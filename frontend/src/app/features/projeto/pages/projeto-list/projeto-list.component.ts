import { Component, OnInit } from '@angular/core';
import { ProjetoService } from '../../services/projeto.service';
import { ProjectResponse } from '../../models/projeto.model';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {
  catchError,
  map,
  shareReplay,
  startWith,
  switchMap,
  tap,
} from 'rxjs/operators';
import { BehaviorSubject, EMPTY, Observable, of } from 'rxjs';

@Component({
  selector: 'app-projetos-list',
  standalone: true,
  templateUrl: './projeto-list.component.html',
  imports: [CommonModule, ReactiveFormsModule],
})
export class ProjetosListComponent implements OnInit {
  projetos$!: Observable<ViewState<ProjectResponse[]>>;

  private refresh$ = new BehaviorSubject<void>(undefined);

  state: ProjetoFormState = { status: 'idle', fieldErrors: {} };

  get loading() {
    return this.state.status === 'loading';
  }

  form;

  constructor(
    private fb: FormBuilder,
    private projetoService: ProjetoService,
  ) {
    this.form = this.createForm();
  }

  private createForm() {
    return this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(80)]],
      descricao: [''],
    });
  }

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

  get nameCtrl() {
    return this.form.controls.nome;
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
          this.refresh$.next();
          this.state = {
            status: 'success',
            successMessage: `Projeto criado: ${created.name}`,
            fieldErrors: {},
          };
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
