package com.example.timemanagementapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.example.timemanagementapp.dto.transfer.UserDetailsTransferDTO;
import com.example.timemanagementapp.exceptions.UserAlreadyExistsException;
import com.example.timemanagementapp.model.User;
import com.example.timemanagementapp.repositories.UserRepository;

@Service
public class UserService implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsTransferDTO.build(user);
    }

    @Override
    public void createUser(UserDetails userDetails) throws UserAlreadyExistsException {
        if (userExists(userDetails.getUsername())) {
            throw new UserAlreadyExistsException("username already exists.");
        }
        User user = new User(userDetails.getUsername(), passwordEncoder.encode(userDetails.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDetails
                        .getUsername()));
        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    @Override
    public boolean userExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        return true;
    }

}
