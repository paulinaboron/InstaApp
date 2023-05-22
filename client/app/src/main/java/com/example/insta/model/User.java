package com.example.insta.model;

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
