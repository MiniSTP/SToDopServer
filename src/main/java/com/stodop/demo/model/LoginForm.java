package com.stodop.demo.model;

import java.io.Serializable;

public class LoginForm implements Serializable {
    private String email;
    private String password;
    public LoginForm(){};
    public LoginForm(String email,String password){
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
