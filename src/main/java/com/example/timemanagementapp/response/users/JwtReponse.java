package com.example.timemanagementapp.response.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtReponse {
    private String jwt;
    private String username;
    private final String type = "Bearer";
}
