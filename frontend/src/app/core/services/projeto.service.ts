import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ProjetoDTO } from '../../pages/projeto/projeto.model';

@Injectable({ providedIn: 'root' })
export class ProjetoService {
  // ajuste para o host/porta do seu Spring
  private readonly baseUrl = 'http://localhost:8080/projetos';

  constructor(private http: HttpClient) {}

  listar(): Observable<ProjetoDTO[]> {
    return this.http.get<ProjetoDTO[]>(this.baseUrl);
  }

  // opcional: fallback mock pra testar UI rápido
  listarMock(): Observable<ProjetoDTO[]> {
    return of([
      { nome: 'Projeto A', descricao: 'Descrição A', dataCriacao: new Date().toISOString() },
      { nome: 'Projeto B', descricao: 'Descrição B', dataCriacao: new Date().toISOString() }
    ]);
  }
}
