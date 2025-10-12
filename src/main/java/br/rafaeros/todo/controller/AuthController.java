package br.rafaeros.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import br.rafaeros.todo.dto.UserRegisterDTO;
import br.rafaeros.todo.service.UserService;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
        @RequestParam String username,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam String confirmPassword,
        Model model
    ) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorPassword", "Passwords do not match!");
            return "register";
        }

        try {
            UserRegisterDTO userDto = new UserRegisterDTO(username, email, password, confirmPassword);
            userService.registerUser(userDto);
            model.addAttribute("successMessage", "User registered successfully!");
            return "redirect:/login?registered=true";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorEmail", "Email already exists!");
            return "register";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while registering the user." + e.getMessage());
            return "register";
        }
        
    }
}
