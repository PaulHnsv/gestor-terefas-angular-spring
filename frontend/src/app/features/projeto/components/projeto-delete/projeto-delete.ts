import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { ProjetoService } from '../../services/projeto.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from 'node_modules/@angular/common/types/_common_module-chunk';

@Component({
  standalone: true,
  selector: 'app-projeto-delete',
  templateUrl: './projeto-delete.html',
  styleUrl: './projeto-delete.scss',
})
export class ProjetoDelete {
  @Input() projectId: string | null = null;

  @Output() deleted = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();
  
  ngOnInit(): void {}

  constructor(private projetoService: ProjetoService) {}
  ngOnChanges(changes: SimpleChanges) {
    if (changes['projectId'] && this.projectId) {
      this.projectId = changes['projectId'].currentValue;
    }
  }

  deleteProjectById() {
    if (!this.projectId) return;

    this.projetoService.delete(this.projectId).subscribe({
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
