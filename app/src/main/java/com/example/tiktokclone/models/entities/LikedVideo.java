package com.example.tiktokclone.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "fav_video_table")
public class LikedVideo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public
    int id = 0;
    @ColumnInfo
    public
    String url;
    @ColumnInfo
    public
    String title;
    @ColumnInfo
    public
    String descrtion;
    @ColumnInfo
    public
    String likes;
    @ColumnInfo
    public
    String comments;
    @ColumnInfo
    public
    boolean isLiked = false;
    @ColumnInfo
    public
    String profilePicUrl;
    @ColumnInfo
    public
    String thumbnail;

    public LikedVideo(String url, String title, String descrtion, String likes, String comments, boolean isLiked, String profilePicUrl, String thumbnail) {
        this.url = url;
        this.title = title;
        this.descrtion = descrtion;
        this.likes = likes;
        this.comments = comments;
        this.isLiked = isLiked;
        this.profilePicUrl = profilePicUrl;
        this.thumbnail = thumbnail;
    }

}
