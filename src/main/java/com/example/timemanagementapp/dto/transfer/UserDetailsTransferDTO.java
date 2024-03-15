package com.example.timemanagementapp.dto.transfer;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.timemanagementapp.model.User;

public class UserDetailsTransferDTO implements UserDetails {
    private User user;

    public UserDetailsTransferDTO(String username, String password) {
        this.user = new User(username, password);
    }

    public UserDetailsTransferDTO(final User user) {
        this.user = user;
    }

    public static UserDetailsTransferDTO build(final User user) {
        UserDetailsTransferDTO userDTO = new UserDetailsTransferDTO(user);
        return userDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        System.out.println(authority);
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
