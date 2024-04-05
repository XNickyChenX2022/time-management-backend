package com.example.timemanagementapp.request.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequestDTO {
    @NotBlank(message = "Username must not be blank")
    @Size(min = 8, max = 15, message = "Usernames must be between 8 and 15 characters")
    private String username;
    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 20 characters")
    private String password;

}
