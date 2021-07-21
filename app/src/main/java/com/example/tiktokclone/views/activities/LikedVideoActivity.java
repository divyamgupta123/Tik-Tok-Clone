package com.example.tiktokclone.views.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.tiktokclone.views.adapters.LikedVideoAdapter;
import com.example.tiktokclone.viewmodel.LikedVideoViewModel;
import com.example.tiktokclone.databinding.ActivityFavVideoBinding;

public class LikedVideoActivity extends AppCompatActivity {

    ActivityFavVideoBinding binding;

    private LikedVideoViewModel mLikedVideoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityFavVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mLikedVideoViewModel = new ViewModelProvider(this).get(LikedVideoViewModel.class);

        mLikedVideoViewModel.getAllLikedVideo().observe(this, favVideos->{
            binding.rvFavVideos.setLayoutManager(new GridLayoutManager(this,2));
            LikedVideoAdapter adapter = new LikedVideoAdapter();
            binding.rvFavVideos.setAdapter(adapter);

            if(favVideos.isEmpty()){
                binding.rvFavVideos.setVisibility(View.GONE);
                binding.tvNoFavoriteDishesAvailable.setVisibility(View.VISIBLE);

            }else{
                adapter.setVideosList(favVideos);
            }

        });


    }
}