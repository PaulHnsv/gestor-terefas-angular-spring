export interface FormState {
  status: FormStatus;
  successMessage?: string;
  errorMessage?: string;
  fieldErrors?: Record<string, string>;
}

export type FormStatus = 'idle' | 'loading' | 'success' | 'error';
