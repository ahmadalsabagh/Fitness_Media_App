package com.example.fitnessmediaapp;

public class CustomExerciseList {
    private String description;
    private String exerciseName;
    private String firstName;
    private String lastName;
    private String username;
    private String image;
    private String userID;

    public CustomExerciseList() {
        //Empty for Firebase
    }

    public CustomExerciseList(String description, String exerciseName, String firstName, String lastName, String username, String image, String userID) {
        this.description = description;
        this.exerciseName = exerciseName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.image = image;
        this.userID = userID;
    }

    public String getDescription() {return description;}
    public String getExerciseName() {return exerciseName;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getUsername() {
        return username;
    }
    public String getImage() {return image;}
    public String getUserID() {return userID;}
}
