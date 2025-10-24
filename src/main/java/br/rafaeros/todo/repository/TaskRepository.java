package br.rafaeros.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.rafaeros.todo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(long userId);
}
