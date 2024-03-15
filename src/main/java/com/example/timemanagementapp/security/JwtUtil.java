package com.example.timemanagementapp.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.timemanagementapp.dto.transfer.UserDetailsTransferDTO;
import com.example.timemanagementapp.exceptions.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

// import io.jsonwebtoken.*;
@Component
public class JwtUtil {

    private long EXPIRATION_TIME = 30 * 24 * 60 * 60;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.token}")
    private String jwtTokenSecret;

    public SecretKey key() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtTokenSecret));
        return key;
    }

    public String generateToken(Authentication authentication) {
        UserDetailsTransferDTO userDTO = (UserDetailsTransferDTO) authentication.getPrincipal();
        return Jwts.builder().subject(userDTO.getUsername()).expiration(
                new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isExpired(String token) {
        Date expirationDate = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expirationDate != null && expirationDate.before(new Date());
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(key())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}