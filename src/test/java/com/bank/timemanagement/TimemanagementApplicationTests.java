package com.bank.timemanagement;

import static org.mockito.Mockito.description;
import static org.mockito.Mockito.mock;

import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import com.example.timemanagementapp.repositories.UserRepository;
import com.example.timemanagementapp.services.UserService;

@SpringBootTest(classes = { SecurityConfig.class })

class TimemanagementApplicationTests {

	@Test
	void contextLoads() {
	}

	// @Test
	// @DisplayName("test if UserDetails object is returned if no error if found and
	// username exists")
	// public void UserServiceHappyFlow() {
	// UserRepository userRepository = mock(UserRepository.class);
	// PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
	// UserService userService = new UserService(userRepository, passwordEncoder);
	// userService.loadUserByUsername("nickychen2022");
	// }
}
