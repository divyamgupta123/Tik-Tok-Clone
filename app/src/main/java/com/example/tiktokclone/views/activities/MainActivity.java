package com.example.tiktokclone.views.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiktokclone.databinding.ActivityMainBinding;
import com.example.tiktokclone.models.entities.LikedVideo;
import com.example.tiktokclone.models.entities.Video;
import com.example.tiktokclone.models.network.Methods;
import com.example.tiktokclone.models.network.RetrofitClient;
import com.example.tiktokclone.viewmodel.LikedVideoViewModel;
import com.example.tiktokclone.views.adapters.VideoAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ViewPager2 viewPager2;
    ArrayList<Video.Msg> videos;
    private LikedVideoViewModel mLikedVideoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getVideos();

        viewPager2 = binding.viewpager;
        mLikedVideoViewModel = new ViewModelProvider(this).get(LikedVideoViewModel.class);

    }

    public void getVideos() {
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Video> call = methods.getAllVideos();

        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                videos = response.body().getMsg();
                viewPager2.setAdapter(new VideoAdapter(MainActivity.this, videos));

            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {

            }
        });
    }

    public void makeFavVideo(Video.Msg video) {

        LikedVideo favVideo = new LikedVideo(
                video.getVideo(),
                video.getUser_info().getFirst_name(),
                video.getDescription(),
                String.valueOf(video.getCount().getLike_count()),
                String.valueOf(video.getCount().getVideo_comment_count()),
                true,
                video.getUser_info().getProfile_pic(),
                video.getThum()
        );
        mLikedVideoViewModel.insert(favVideo);
    }

}