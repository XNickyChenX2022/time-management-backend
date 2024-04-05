package com.bank.timemanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.timemanagementapp.dto.users.UserDetailsTransferDTO;
import com.example.timemanagementapp.exceptions.UserAlreadyExistsException;
import com.example.timemanagementapp.model.User;
import com.example.timemanagementapp.repositories.UserRepository;
import com.example.timemanagementapp.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceWithAnnotationsUnitTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;
    Logger logger = LoggerFactory.getLogger(UserServiceWithAnnotationsUnitTests.class);

    @Test
    public void testLoadUserByUsernameWithExistingUser() {
        User user = new User("nickychen2022", "$2a$10$ZYBl78KmPVvt16fvcf0WJ.0x/ULpsGAgwBOOJcUIf1OV5gO2ve0.q");

        when(userRepository.findByUsernameIgnoreCase("nickychen2022")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("nickychen2022");
        logger.info("Username: {}", userDetails.getUsername());
        assertEquals("nickychen2022", userDetails.getUsername());
        assertEquals("$2a$10$ZYBl78KmPVvt16fvcf0WJ.0x/ULpsGAgwBOOJcUIf1OV5gO2ve0.q", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String nonExistingUsername = "nonExistingUser";
        when(userRepository.findByUsernameIgnoreCase(nonExistingUsername)).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userService.loadUserByUsername(nonExistingUsername));
        assertEquals("User not found with username: " + nonExistingUsername, exception.getMessage());
    }

    @Test
    public void testCreateUser_UserAlreadyExists() {
        String existingUsername = "existingUser";
        UserDetails existingUserDetails = new UserDetailsTransferDTO("existingUser", "existingPassword");
        when(userRepository.findByUsernameIgnoreCase(existingUsername)).thenReturn(Optional.of(new User()));

        UserAlreadyExistsException exception = assertThrows(
                UserAlreadyExistsException.class,
                () -> userService.createUser(existingUserDetails));
        assertEquals("username already exists.", exception.getMessage());
    }
}
