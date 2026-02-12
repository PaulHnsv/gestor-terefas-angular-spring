import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProjectResponse } from '../../models/projeto.model';
import { ProjetoService } from '../../services/projeto.service';
import { BehaviorSubject } from 'rxjs';
import { CommonModule } from '@angular/common';
@Component({
  standalone: true,
  selector: 'app-projeto-edit',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './projeto-edit.html',
  styleUrl: './projeto-edit.scss',
})
export class ProjetoEdit implements OnInit {
@Input() project: ProjectResponse | null = null;

@Output() updated = new EventEmitter<void>();
@Output() close = new EventEmitter<void>();


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

    this.projetoService.update(this.project.id, editPayload).subscribe({
      next: () => {
        this.updated.emit();
        this.close.emit();
      },
      error: (err) => {
        console.error('Erro ao atualizar projeto:', err);
      },
    });
  }

}
