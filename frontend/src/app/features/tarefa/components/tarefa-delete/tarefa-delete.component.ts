import { Component, Input, Output, EventEmitter, SimpleChanges } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TarefaService } from "../../services/tarefa.service";

@Component({
  standalone: true,
  selector: 'app-tarefa-delete',
  imports: [CommonModule],
  templateUrl: './tarefa-delete.component.html',
  styleUrl: './tarefa-delete.component.scss',
})
export class TarefaDelete {
  @Input() tarefaId: string | null = null;
  @Input() tarefaTitle: string | null = null;

  @Output() deleted = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  errorMessage: string | null = null;
  isLoading = false;

  constructor(private tarefaService: TarefaService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['tarefaId'] && this.tarefaId) {
      this.tarefaId = changes['tarefaId'].currentValue;
      this.errorMessage = null;
    }
  }

  deleteTarefaById() {
    if (!this.tarefaId) return;

    this.isLoading = true;
    this.errorMessage = null;

    this.tarefaService.delete(this.tarefaId).subscribe({
      next: () => {
        this.isLoading = false;
        this.deleted.emit();
        this.close.emit();
      },
      error: () => {
        this.isLoading = false;
        this.errorMessage = 'Não foi possível excluir a tarefa. Tente novamente.';
      },
    });
  }
}
