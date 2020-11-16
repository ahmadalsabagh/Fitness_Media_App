package com.example.fitnessmediaapp;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String username;

    public User(String firstName, String lastName, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

    public User(){
        //empty constructor to comply with firebase settings
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
