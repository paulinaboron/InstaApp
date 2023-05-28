package com.example.insta.model;

import android.graphics.Bitmap;

public class User {
    private String name;
    private String lastname;
    private String email;
    private String pass;
    private boolean confirmed;

    public User(String name, String lastname, String email, String pass) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.pass = pass;
        this.confirmed = false;
    }

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public boolean isConfirmed() {
        return confirmed;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }
}
