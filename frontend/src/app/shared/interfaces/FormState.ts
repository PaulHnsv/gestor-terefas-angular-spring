type FormStatus = 'idle' | 'loading' | 'success' | 'error';

interface FormState {
  status: FormStatus;
  successMessage?: string;
  errorMessage?: string;
  fieldErrors?: Record<string, string>;
}