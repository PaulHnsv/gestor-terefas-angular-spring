export interface AppError {
  status: number;
  code: string;
  message: string;
  path?: string;
  fieldErrors?: Record<string, string[]>;
  raw?: unknown;
}