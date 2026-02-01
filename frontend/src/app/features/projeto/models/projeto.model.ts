export interface ProjectResponse {
  name: string;
  description: string;
  id: string; 
}

export interface ProjectRequest {
  name: string;
  description?: string | null;
}