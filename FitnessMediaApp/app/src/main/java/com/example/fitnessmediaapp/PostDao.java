package com.example.fitnessmediaapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostDao {
    @Query("SELECT * FROM Posts")
    List<Post> getAll();

    @Insert
    void insertAll(Post... posts);

    @Delete
    void delete(Post post);
}