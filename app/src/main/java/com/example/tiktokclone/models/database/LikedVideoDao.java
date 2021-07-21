package com.example.tiktokclone.models.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tiktokclone.models.entities.LikedVideo;

import java.util.List;

@Dao
public interface LikedVideoDao {

    @Query("SELECT * FROM fav_video_table WHERE isLiked = 1")
    LiveData<List<LikedVideo>> getAllFavVideos();

    @Insert
    void insertVideo(LikedVideo favVideo);

    @Delete
    void deleteVideo(LikedVideo favVideo);


}
