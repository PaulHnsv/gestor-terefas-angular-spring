export interface ProjetoDTO {
  nome: string;
  descricao: string;
  dataCriacao: string; 
  // vem do backend como string (ISO). Ex: "2026-01-24T12:34:56.000+00:00"
}

export interface CreateProjectRequest {
  nome: string;
  descricao?: string | null;
}