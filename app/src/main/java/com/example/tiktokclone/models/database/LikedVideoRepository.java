package com.example.tiktokclone.models.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tiktokclone.models.entities.LikedVideo;

import java.util.List;

public class LikedVideoRepository {
    private LikedVideoDao mLikedVideoDao;
    private LiveData<List<LikedVideo>> mAllFavVideos;

    public LikedVideoRepository(Application application) {
        LikedVideoDatabase db = LikedVideoDatabase.getDatabase(application);
        mLikedVideoDao = db.favVideoDao();
        mAllFavVideos = mLikedVideoDao.getAllFavVideos();
    }

    public LiveData<List<LikedVideo>> getAllLikedVideos() {
        return mAllFavVideos;
    }

    public void insert(LikedVideo favVideo) {
        LikedVideoDatabase.databaseWriteExecutor.execute(() -> {
            mLikedVideoDao.insertVideo(favVideo);
        });
    }

    public void delete(LikedVideo favVideo) {
        LikedVideoDatabase.databaseWriteExecutor.execute(() -> {
            mLikedVideoDao.deleteVideo(favVideo);
        });
    }

}
