import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { ProjectResponse, ProjectRequest } from '../models/projeto.model';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class ProjetoService {
  // ajuste para o host/porta do seu Spring
  private readonly baseUrl = environment.apiUrl + '/api/projects';

  constructor(private http: HttpClient) {}

  list(): Observable<ProjectResponse[]> {
    return this.http.get<ProjectResponse[]>(this.baseUrl);
  }

  create(payload: ProjectRequest): Observable<ProjectRequest> {
    return this.http.post<ProjectRequest>(this.baseUrl, payload).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  private handleError(err: HttpErrorResponse) {
    // Aqui você centraliza o parse do erro como quiser
    // Ex: backend retorna { message: "...", fieldErrors: { name: "..." } }
    console.error('Erro na requisição', err);
    return of({} as ProjectRequest);
  }
}
