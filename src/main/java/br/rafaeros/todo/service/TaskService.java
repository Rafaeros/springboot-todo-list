package br.rafaeros.todo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.rafaeros.todo.model.Task;
import br.rafaeros.todo.model.enums.TaskStatus;
import br.rafaeros.todo.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAllByUserId(long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public void updateTaskStatus(long taskId, TaskStatus status, LocalDateTime date) {
        Task task = taskRepository.findById(taskId).orElseThrow(null);
        task.setStatus(status);
        task.setCompletedAt(date);
        taskRepository.save(task);
    }

}
