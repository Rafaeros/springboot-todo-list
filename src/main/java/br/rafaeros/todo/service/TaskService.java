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

    public Task findById(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(null);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAllByUserId(long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public List<Task> findAllPendingByUserId(long userId) {
        return taskRepository.findAllStatusByUserId(userId, TaskStatus.PENDING);
    }

    public List<Task> findAllCompletedByUserId(long userId) {
        return taskRepository.findAllStatusByUserId(userId, TaskStatus.COMPLETED);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTaskStatus(long taskId, TaskStatus status, LocalDateTime date) {
        Task task = taskRepository.findById(taskId).orElseThrow(null);
        task.setStatus(status);
        task.setCompletedAt(date);
        taskRepository.save(task);
    }

    public void deleteTask(long taskId) {
        taskRepository.deleteById(taskId);
    }

}
