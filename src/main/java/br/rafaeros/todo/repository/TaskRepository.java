package br.rafaeros.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.rafaeros.todo.model.Task;
import br.rafaeros.todo.model.enums.TaskPriority;
import br.rafaeros.todo.model.enums.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(long userId);

    @Query("SELECT t FROM Task t WHERE t.user.id = ?1 AND t.status = ?2")
    List<Task> findAllStatusByUserId(long userId, TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.user.id = ?1 AND t.status = ?2")
    List<Task> findByUserIdAndStatus(long userId, TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.user.id = ?1 AND t.priority = ?2")
    List<Task> findByUserIdAndPriority(long userId, TaskPriority priority);

    @Query("SELECT t FROM Task t WHERE t.user.id = ?1 AND t.status = ?2 AND t.priority = ?3")
    List<Task> findByUserIdAndStatusAndPriority(long userId, TaskStatus status, TaskPriority priority);
}
