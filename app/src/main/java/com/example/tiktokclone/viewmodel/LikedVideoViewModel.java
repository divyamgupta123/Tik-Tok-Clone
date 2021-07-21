package com.example.tiktokclone.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tiktokclone.models.database.LikedVideoRepository;
import com.example.tiktokclone.models.entities.LikedVideo;

import java.util.List;

public class LikedVideoViewModel extends AndroidViewModel {

    private LikedVideoRepository mLikedVideoRepository;
    private final LiveData<List<LikedVideo>> mAllLikedVideo;
    public LikedVideoViewModel(Application application) {
        super(application);
        mLikedVideoRepository = new LikedVideoRepository(application);
        mAllLikedVideo = mLikedVideoRepository.getAllLikedVideos();
    }
    public LiveData<List<LikedVideo>> getAllLikedVideo(){
        return mAllLikedVideo;
    }

    public void insert(LikedVideo favVideo){
        mLikedVideoRepository.insert(favVideo);
    }
    public void delete(LikedVideo favVideo){
        mLikedVideoRepository.delete(favVideo);
    }
}
