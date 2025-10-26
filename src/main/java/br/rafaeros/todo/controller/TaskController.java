package br.rafaeros.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.rafaeros.todo.model.Task;
import br.rafaeros.todo.model.User;
import br.rafaeros.todo.model.enums.TaskPriority;
import br.rafaeros.todo.model.enums.TaskStatus;
import br.rafaeros.todo.service.TaskService;
import br.rafaeros.todo.service.UserService;
import jakarta.validation.Valid;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // Read
    @GetMapping("/tasks")
    public String tasks(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        User user = userService.findByEmail(email);
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", user.getName());
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("TaskStatus", TaskStatus.class);
        model.addAttribute("pendingTasks", taskService.findAllPendingByUserId(user.getId()));
        model.addAttribute("completedTasks", taskService.findAllCompletedByUserId(user.getId()));
        return "tasks";
    }

    // Create
    @PostMapping("/tasks")
    public String createTask(@Valid Task task, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("priorities", TaskPriority.values());
        if (user == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/tasks";
        }

        try {
            task.setUser(user);
            taskService.createTask(task);
            redirectAttributes.addFlashAttribute("success", "Task created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/tasks";
        }
        return "redirect:/tasks";
    }

    // Update Form
    @GetMapping("/tasks/edit/{taskId}")
    public String editTask(@PathVariable("taskId") long taskId, Model model) {
        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("priorities", TaskPriority.values());
        return "edit-task";
    }

    // Update Request
    @PostMapping("/tasks/update/{taskId}")
    public String updateTask(@PathVariable("taskId") long taskId, @Valid Task task, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("priorities", TaskPriority.values());
        if (user == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/tasks";
        }

        try {
            task.setUser(user);
            taskService.updateTask(task);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/tasks";
        }

        redirectAttributes.addFlashAttribute("success", "Task updated successfully!");
        return "redirect:/tasks";
    }

    // Update Request
    @PostMapping("/tasks/{taskId}/status")
    public String updateTaskStatus(@PathVariable("taskId") long taskId, @RequestParam TaskStatus status,
            RedirectAttributes redirectAttributes) {
        LocalDateTime now = LocalDateTime.now();
        try {
            taskService.updateTaskStatus(taskId, status, now);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/tasks";
        }
        redirectAttributes.addFlashAttribute("success", "Task status updated successfully!");
        return "redirect:/tasks";
    }

    // Delete
    @GetMapping("/tasks/{taskId}")
    public String deleteTask(@PathVariable("taskId") long taskId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        if (user == null) {
            return "redirect:/login";
        }

        try {
            taskService.deleteTask(taskId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/tasks";
        }

        redirectAttributes.addFlashAttribute("success", "Task deleted successfully!");
        return "redirect:/tasks";
    }
}
