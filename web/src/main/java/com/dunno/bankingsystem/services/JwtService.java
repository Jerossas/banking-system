package com.dunno.bankingsystem.services;

import com.dunno.bankingsystem.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generate(User user);
    String extractEmail(String token);
    boolean isValid(String token, UserDetails userDetails);
}
