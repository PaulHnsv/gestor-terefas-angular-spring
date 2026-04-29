import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProjectResponse } from '../../models/projeto.model';
import { ProjetoService } from '../../services/projeto.service';
import { catchError, EMPTY, tap } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormState } from 'src/app/shared/interfaces/FormState';
@Component({
  standalone: true,
  selector: 'app-projeto-edit',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './projeto-edit.component.html',
  styleUrl: './projeto-edit.component.scss',
})
export class ProjetoEdit implements OnInit {
  @Input() project: ProjectResponse | null = null;

  @Output() updated = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  state: FormState = { status: 'idle', fieldErrors: {} };
  editForm;
  constructor(
    private fb: FormBuilder,
    private projetoService: ProjetoService,
  ) {
    this.editForm = this.createForm();
  }
  ngOnInit(): void {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['project'] && this.project) {
      this.editForm.patchValue({
        nome: this.project.name,
        descricao: this.project.description,
      });
    }
  }

  private createForm() {
    return this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(80)]],
      descricao: [''],
    });
  }

  submitEdit() {
    if (!this.project) return;

    const editPayload = {
      name: (this.editForm.value.nome ?? '').trim(),
      description: this.editForm.value.descricao?.trim() || null,
    };

    this.projetoService
      .update(this.project.id, editPayload)
      .pipe(
        tap(() => {
          this.updated.emit();
          this.close.emit();
        }),
        catchError((err) => {
          const body = err?.error;
          this.state = {
            status: 'error',
            errorMessage: body?.message || 'Falha ao atualizar projeto.',
            fieldErrors: body?.fieldErrors ?? {},
          };
          return EMPTY;
        }),
      )
      .subscribe();
  }

  get loading() {
    return this.state.status === 'loading';
  }

  get form() {
    return this.editForm.controls;
  }

  get fieldErrors() {
    return this.state.fieldErrors;
  }
}
