import { Component, Input, Output, EventEmitter, SimpleChanges } from "@angular/core";
import { TarefaService } from "../../services/tarefa.service";

@Component({
  standalone: true,
  selector: 'app-tarefa-delete',
  templateUrl: './tarefa-delete.component.html',
  styleUrl: './tarefa-delete.component.scss',
})
export class TarefaDelete {
  @Input() tarefaId: string | null = null;

  @Output() deleted = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();
  
  ngOnInit(): void {}

  constructor(private tarefaService: TarefaService) {}
  ngOnChanges(changes: SimpleChanges) {
    if (changes['tarefaId'] && this.tarefaId) {
      this.tarefaId = changes['tarefaId'].currentValue;
    }
  }

  deleteTarefaById() {
    if (!this.tarefaId) return;

    this.tarefaService.delete(this.tarefaId).subscribe({
      next: () => {
        this.deleted.emit();
        this.close.emit();
      },
      error: () => {
        // Trate o erro conforme necessário
      },
    });
  }
}
