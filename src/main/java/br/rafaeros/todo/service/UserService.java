package br.rafaeros.todo.service;

import br.rafaeros.todo.dto.UserRegisterDTO;
import br.rafaeros.todo.model.User;
import br.rafaeros.todo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public User registerUser(UserRegisterDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User(dto.getUsername(), dto.getEmail(), encodedPassword);
        return userRepository.save(user);
    }
}
