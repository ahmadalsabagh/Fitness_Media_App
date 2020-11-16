package com.example.fitnessmediaapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index(value = {"username"},
        unique = true)}, tableName = "Users") //CHANGE TABLE NAME & verify username column matches
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @NonNull
    @ColumnInfo(name = "Username")
    private String username;

    @NonNull
    @ColumnInfo(name = "FirstName")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "LastName")
    private String lastName;

    @NonNull
    @ColumnInfo(name = "Password")
    private String password;

    @Ignore
    public UserEntity(int id, @NonNull String username, @NonNull String firstName, @NonNull String lastName, @NonNull String password){
        this.id = id;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.username = username;
    }

    public int getId(){
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setId(@NonNull int id){
        this.id = id;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public boolean checkPassword(@NonNull String password){
        if(!this.password.equals(password)){
            return false;
        }
        return true;
    }




}
