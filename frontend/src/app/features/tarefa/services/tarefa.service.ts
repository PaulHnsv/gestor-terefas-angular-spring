import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TaskRequest, TaskResponse } from '../models/tarefa.model';

@Injectable({ providedIn: 'root' })
export class TarefaService {
  private readonly baseUrl = environment.apiUrl + '/api/tasks';

  constructor(private http: HttpClient) {}

  list(): Observable<TaskResponse[]> {
    return this.http.get<TaskResponse[]>(this.baseUrl);
  }

  listByProjectId(projectId: string): Observable<TaskResponse[]> {
    return this.http.get<TaskResponse[]>(`${this.baseUrl}/${projectId}`);
  }

  create(payload: TaskRequest): Observable<TaskResponse> {
    return this.http.post<TaskResponse>(this.baseUrl, payload).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  delete(id: string): Observable<TaskResponse> {
    return this.http.delete<TaskResponse>(`${this.baseUrl}/${id}`).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  update(id: string, payload: TaskRequest): Observable<TaskResponse> {
    return this.http.put<TaskResponse>(`${this.baseUrl}/${id}`, payload).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  get(id: string): Observable<TaskResponse> {
    return this.http.get<TaskResponse>(`${this.baseUrl}/${id}`).pipe(
      catchError((err) => this.handleError(err))
    );
  }

  private handleError(err: HttpErrorResponse) {
    // Aqui você centraliza o parse do erro como quiser
    // Ex: backend retorna { message: "...", fieldErrors: { name: "..." } }
    console.error('Erro na requisição', err);
    return of({} as TaskResponse);
  }
}
