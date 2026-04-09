package com.dunno.bankingsystem.models;

import com.dunno.bankingsystem.exceptions.InvalidFieldException;
import com.dunno.bankingsystem.models.valueobjects.Email;
import com.dunno.bankingsystem.models.valueobjects.Password;

public class User {

    private Long id;
    private Email email;
    private Password password;

    public User(Email email, Password password) {
        changeEmail(email);
        changePassword(password);
    }

    private User(Long id, Email email, Password password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId(){
        return this.id;
    }

    public void changeEmail(Email newEmail){

        if(newEmail == null) {
            throw new InvalidFieldException("email", "Email cannot be null.");
        }

        this.email = newEmail;
    }

    public Email getEmail() {
        return email;
    }

    public void changePassword(Password newPassword){

        if(newPassword == null) {
            throw new InvalidFieldException("password", "Password cannot be null.");
        }

        this.password = newPassword;
    }

    public Password getPassword() {
        return password;
    }

    public static User restore(Long id, Email email, Password password){
        return new User(id, email, password);
    }
}
