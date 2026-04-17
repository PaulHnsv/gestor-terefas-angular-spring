import { Priority } from "src/app/shared/interfaces/Priority";
import { ProjectResponse } from "../../projeto/models/projeto.model";

export interface TaskResponse {
  id: string;
  title: string;
  description?: string | null;
  project: ProjectResponse;
  priority: Priority;
  status: Status;
}

export interface TaskRequest {
  title: string;
  description?: string | null;
  project: ProjectResponse;
  priority: Priority;
  status: Status;
}