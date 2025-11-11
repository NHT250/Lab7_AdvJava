package com.howto.DemoUser.controller;

import com.howto.DemoUser.model.User;
import com.howto.DemoUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET /api/user
    @GetMapping
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // GET /api/user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/user
    @PostMapping
    public User saveUser(@Validated @RequestBody User user) {
        // Nếu body có id, JPA sẽ coi là update nếu id tồn tại
        return userRepository.save(user);
    }
}
