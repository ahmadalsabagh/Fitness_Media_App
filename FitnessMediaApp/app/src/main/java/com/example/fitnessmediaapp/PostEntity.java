package com.example.fitnessmediaapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import javax.annotation.Nonnull;

@Entity(foreignKeys = @ForeignKey(entity = UserEntity.class,
        parentColumns = "ID",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE),tableName = "Posts")
public class PostEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "UserId")
    private int userId;

    @NonNull
    @ColumnInfo(name = "Content")
    private String content;

    @NonNull
    @ColumnInfo(name = "Time")
    private String time;

    @NonNull
    @ColumnInfo(name = "Location")
    private String location;

    @Ignore
    public PostEntity(int id, @NonNull int userId, @NonNull String content, @NonNull String time, @NonNull String location) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.time = time;
        this.location = location;
    }

    public ArrayList getPost(@NonNull int id){
        ArrayList<String> postArray = new ArrayList<String>();
        postArray.add(Integer.toString(this.userId));
        postArray.add(this.content);
        postArray.add(this.time);
        postArray.add(this.location);

        return postArray;
    }
}
