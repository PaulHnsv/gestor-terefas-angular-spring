import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { ProjectResponse, ProjectRequest } from '../models/projeto.model';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class ProjetoService {
  private readonly baseUrl = environment.apiUrl + '/api/projects';

  constructor(private http: HttpClient) {}

  list(): Observable<ProjectResponse[]> {
    return this.http.get<ProjectResponse[]>(this.baseUrl);
  }

  create(payload: ProjectRequest): Observable<ProjectResponse> {
    return this.http.post<ProjectResponse>(this.baseUrl, payload).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  delete(id: string): Observable<ProjectResponse> {
    return this.http.delete<ProjectResponse>(`${this.baseUrl}/${id}`).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  update(id: string, payload: ProjectRequest): Observable<ProjectResponse> {
    return this.http.put<ProjectResponse>(`${this.baseUrl}/${id}`, payload).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  get(id: string): Observable<ProjectResponse> {
    return this.http.get<ProjectResponse>(`${this.baseUrl}/${id}`).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  private handleError(err: HttpErrorResponse) {
    // Aqui você centraliza o parse do erro como quiser
    // Ex: backend retorna { message: "...", fieldErrors: { name: "..." } }
    console.error('Erro na requisição', err);
    return of({} as ProjectResponse);
  }
}
