package br.rafaeros.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.rafaeros.todo.dto.UserRegisterDTO;
import br.rafaeros.todo.service.UserService;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model) {

        if (error != null) {
            model.addAttribute("warning", "Wrong email or password!");
        }

        if (logout != null) {
            model.addAttribute("success", "You have been logged out successfully!");
        }
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
        Model model,
        RedirectAttributes redirectedAttributes
    ) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorPassword", "Passwords do not match!");
            return "register";
        }

        try {
            UserRegisterDTO userDto = new UserRegisterDTO(username, email, password, confirmPassword);
            userService.registerUser(userDto);
            redirectedAttributes.addFlashAttribute("success", "User registered successfully!");
            return "redirect:/login?registered=true";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("warning", "Email already exists!");
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while registering the user." + e.getMessage());
            return "register";
        }
        
    }
}
