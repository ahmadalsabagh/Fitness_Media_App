package com.example.fitnessmediaapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Exercises")
public class ExerciseEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @NonNull
    @ColumnInfo(name = "ExerciseName")
    private String exerciseName;

    @NonNull
    @ColumnInfo(name = "Description")
    private String description;

    @NonNull
    @ColumnInfo(name = "Image")
    private String image;

    @Ignore
    public ExerciseEntity(int id, @NonNull String exerciseName, @NonNull String description, @NonNull String image){
        this.id = id;
        this.exerciseName = exerciseName;
        this.description = description;
        this.image = image;
    }

    public int getId(int id){return this.id;}
    public String getExerciseName(int id){return this.exerciseName;}

}
