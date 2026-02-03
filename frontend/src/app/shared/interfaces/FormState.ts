interface FormState {
  status: Status;
  successMessage?: string;
  errorMessage?: string;
  fieldErrors?: Record<string, string>;
}