import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import { ApiError } from '../interfaces/ApiError';
import { AppError } from '../interfaces/AppError';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((err: unknown) => {
      if (err instanceof HttpErrorResponse) {
        const appError = toAppError(err);
        return throwError(() => appError);
      }

      const fallback: AppError = {
        status: 0,
        code: 'FRONTEND_ERROR',
        message: 'Erro inesperado no frontend.',
        raw: err
      };

      return throwError(() => fallback);
    })
  );
};

function toAppError(err: HttpErrorResponse): AppError {
  // rede / CORS / offline
  if (err.status === 0) {
    return {
      status: 0,
      code: 'NETWORK_ERROR',
      message: 'Falha de rede ou CORS. Verifique conexão e backend.',
      raw: err.error
    };
  }

  const api = err.error as Partial<ApiError> | null;

  // seu padrão do backend
  if (api && typeof api === 'object' && 'status' in api && 'message' in api) {
    return {
      status: api.status ?? err.status,
      code: api.error ?? 'UNKNOWN_ERROR',
      message: api.message ?? 'Erro na requisição.',
      path: api.path,
      fieldErrors: extractFieldErrors(api.details),
      raw: api
    };
  }

  // fallback quando não vem no padrão
  return {
    status: err.status,
    code: 'HTTP_ERROR',
    message: err.message || 'Erro HTTP.',
    raw: err.error
  };
}

function extractFieldErrors(details: unknown): Record<string, string[]> | undefined {
  if (!Array.isArray(details)) return undefined;

  const result: Record<string, string[]> = {};
  for (const item of details) {
    if (!item || typeof item !== 'object') continue;

    const field = (item as any).field;
    const message = (item as any).message;

    if (typeof field === 'string' && typeof message === 'string') {
      result[field] ??= [];
      result[field].push(message);
    }
  }

  return Object.keys(result).length ? result : undefined;
}