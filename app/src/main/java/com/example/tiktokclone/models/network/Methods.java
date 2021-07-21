package com.example.tiktokclone.models.network;

import com.example.tiktokclone.models.entities.Video;
import com.example.tiktokclone.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
    @GET(Constants.URL_PATH)
    Call<Video> getAllVideos();
}
