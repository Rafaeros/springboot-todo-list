package br.rafaeros.todo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.rafaeros.todo.model.User;
import br.rafaeros.todo.service.UserService;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userService.findByEmail(email);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", user.getName());
        return "home";
    }

}
