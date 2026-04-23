import { Priority } from "src/app/shared/interfaces/Priority";
import { ProjectResponse } from "../../projeto/models/projeto.model";
import { Status } from "src/app/shared/interfaces/Status";


export interface TaskResponse {
  id: string;
  title: string;
  description?: string | null;
  project: ProjectResponse | null;
  priority: Priority;
  status: Status;
}

export interface TaskRequest {
  title: string;
  description?: string | null;
  project: ProjectResponse | null;
  priority: Priority;
  status: Status;
}