package com.paulo.gestortarefas.features.tasks.domain.model;

import com.paulo.gestortarefas.features.projects.adapters.outbound.persistence.ProjectJpaEntity;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.shared.utils.Priority;
import com.paulo.gestortarefas.shared.utils.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Task {

    private Long id;
    private String title;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;
    private String description;
    private Project project;
    private LocalDateTime completedAt;

    public Task(Long id, String title, Priority priority, Status status, LocalDateTime createdAt, String description, Project project, LocalDateTime completedAt) {

        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.description = description;
        this.project = project;
        this.completedAt = completedAt;
    }

    public Task(String title, Priority priority, Status status, String description, Project project) {
        validateTitle(title);
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.project = project;
    }

    public void concludeTask() {
        if (isConclude()) {
            throw new IllegalStateException("A tarefa já está concluída.");
        }
        this.status = Status.CONCLUIDA;
        this.completedAt = LocalDateTime.now();
    }

    public void startTask() {
        if (this.status != Status.PENDENTE) {
            throw new IllegalStateException(
                    "Apenas tarefas pendentes podem ser iniciadas.");
        }
        this.status = Status.EM_ANDAMENTO;
    }

    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public boolean isConclude() {
        return this.status == Status.CONCLUIDA;
    }

    private void validateTitle(String title){
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("o titulo é obrigatório");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Task task)) return false;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{id='%s', title='%s', status=%s, priority=%s}"
                .formatted(id, title, status, priority);
    }
}
