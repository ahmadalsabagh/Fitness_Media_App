package com.example.fitnessmediaapp;

public class Post {
    private String content;
    private String location;
    private String time;
    private String username;

    public Post() {
        //Empty for Firebase
    }

    public Post(String content, String location, String time, String username) {
        this.content = content;
        this.location = location;
        this.time = time;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }
}
