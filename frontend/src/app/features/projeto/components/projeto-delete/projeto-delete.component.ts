import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { ProjetoService } from '../../services/projeto.service';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-projeto-delete',
  imports: [CommonModule],
  templateUrl: './projeto-delete.component.html',
  styleUrl: './projeto-delete.component.scss',
})
export class ProjetoDelete {
  @Input() projectId: string | null = null;
  @Input() projectName: string | null = null;

  @Output() deleted = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  errorMessage: string | null = null;
  isLoading = false;
  
  ngOnInit(): void {}

  constructor(private projetoService: ProjetoService) {}
  ngOnChanges(changes: SimpleChanges) {
    if (changes['projectId'] && this.projectId) {
      this.projectId = changes['projectId'].currentValue;
    }
  }

  deleteProjectById() {
    if (!this.projectId) return;

    this.isLoading = true;
    this.errorMessage = null;

    this.projetoService.delete(this.projectId).subscribe({
      next: () => {
        this.isLoading = false;
        this.deleted.emit();
        this.close.emit();
      },
      error: () => {
        this.errorMessage = 'Falha ao excluir o projeto. Tente novamente.';
        this.isLoading = false;
      },
    });
  }

}
