package com.dunno.bankingsystem.services;

public interface PasswordEncoder {

    String encode(String rawString);
    boolean matches(String rawPassword, String encodedPassword);
}
