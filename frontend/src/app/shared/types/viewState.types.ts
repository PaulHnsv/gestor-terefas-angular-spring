type ViewState<T> =
  | { status: 'loading' }
  | { status: 'error'; message: string; fieldErrors: Record<string, string> }
  | { status: 'success'; data: T };  

type Status = 'idle' | 'loading' | 'success' | 'error';

interface ProjetoFormState {
  status: Status;
  successMessage?: string;
  errorMessage?: string;
  fieldErrors?: Record<string, string>;
}