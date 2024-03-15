package com.example.timemanagementapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.timemanagementapp.model.User;
import com.example.timemanagementapp.repositories.UserRepository;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers(@RequestParam Integer param) {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/:id")
    public Optional<User> getUserbyId(@RequestParam Integer param) {
        return this.userRepository.findById(param);
    }

}
