import { Component, OnInit } from '@angular/core';
import { ProjetoService } from '../../core/services/projeto.service';
import { ProjetoDTO } from '../../pages/projeto/projeto.model';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-projetos-list',
  templateUrl: './projeto-list.component.html',
   imports: [CommonModule],
})
export class ProjetosListComponent implements OnInit {
  projetos: ProjetoDTO[] = [];
  carregando = false;
  erro: string | null = null;

  constructor(private projetoService: ProjetoService) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.carregando = true;
    this.erro = null;

    this.projetoService.listar().subscribe({
      next: (data) => {
        console.log('Projetos carregados', data);
        this.projetos = data ?? [];
        this.carregando = false;
      },
      error: (err) => {
        this.erro = 'Falha ao carregar projetos. Verifique API/CORS.';
        this.carregando = false;
        console.error(err);
      }
    });

    // Se quiser testar sem backend:
    // this.projetoService.listarMock().subscribe(data => this.projetos = data);
  }
}
