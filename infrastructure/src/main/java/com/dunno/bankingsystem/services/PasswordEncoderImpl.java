package com.dunno.bankingsystem.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {

    private final BCryptPasswordEncoder bcrypt;

    public PasswordEncoderImpl(BCryptPasswordEncoder bcrypt){
        this.bcrypt = bcrypt;
    }

    @Override
    public String encode(String rawString) {
        return this.bcrypt.encode(rawString);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword){
        return bcrypt.matches(rawPassword, encodedPassword);
    }

}
