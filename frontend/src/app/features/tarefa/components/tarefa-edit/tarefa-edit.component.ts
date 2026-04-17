import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TarefaService } from '../../services/tarefa.service';
import { TaskResponse, TaskRequest } from '../../models/tarefa.model';
import { CommonModule } from '@angular/common';
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
  constructor(
    private fb: FormBuilder,
    private tarefaService: TarefaService,
  ) {
    this.editForm = this.createForm();
  }
  ngOnInit(): void {}

    ngOnChanges(changes: SimpleChanges) {
    if (changes['tarefa'] && this.tarefa) {
      this.editForm.patchValue({
        titulo: this.tarefa.title,
        descricao: this.tarefa.description,
      });
    }
  }

  private createForm() {
    return this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(80)]],
      descricao: [''],
    });
  }

  submitEdit() {
    if (!this.tarefa) return;

    const editPayload: TaskRequest = {
      description: this.editForm.value.descricao?.trim() || null,
      title: this.tarefa.title,
      project: this.tarefa.project,
      priority: this.tarefa.priority,
      status: this.tarefa.status,
    };

    this.tarefaService.update(this.tarefa.id, editPayload).subscribe({
      next: () => {
        this.updated.emit();
        this.close.emit();
      },
      error: (err) => {
        console.error('Erro ao atualizar tarefa:', err);
      },
    });
  }

}
