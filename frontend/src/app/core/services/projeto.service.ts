import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { CreateProjectRequest, ProjetoDTO } from '../../pages/projeto/projeto.model';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class ProjetoService {
  // ajuste para o host/porta do seu Spring
  private readonly baseUrl = environment.apiUrl + '/projetos';

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

  create(payload: CreateProjectRequest): Observable<ProjetoDTO> {
    return this.http.post<ProjetoDTO>(this.baseUrl, payload).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  private handleError(err: HttpErrorResponse) {
    // Aqui você centraliza o parse do erro como quiser
    // Ex: backend retorna { message: "...", fieldErrors: { name: "..." } }
    console.error('Erro na requisição', err);
    return of({} as ProjetoDTO);
  }
}
