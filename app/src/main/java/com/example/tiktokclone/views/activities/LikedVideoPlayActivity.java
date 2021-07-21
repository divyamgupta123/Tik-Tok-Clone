package com.example.tiktokclone.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.tiktokclone.R;
import com.example.tiktokclone.databinding.ActivityLikedVideoPlayBinding;
import com.example.tiktokclone.models.entities.LikedVideo;
import com.example.tiktokclone.utils.Constants;
import com.example.tiktokclone.viewmodel.LikedVideoViewModel;

public class LikedVideoPlayActivity extends AppCompatActivity {

    ActivityLikedVideoPlayBinding binding;
    private LikedVideoViewModel mLikedVideoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikedVideoPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mLikedVideoViewModel = new ViewModelProvider(this).get(LikedVideoViewModel.class);
        LikedVideo favVideo = (LikedVideo) getIntent().getSerializableExtra(Constants.LIKED_VIDEO_INTENT_KEY);
        setdata(favVideo);

        binding.tvLikes.setOnClickListener(v -> {
            favVideo.isLiked = !favVideo.isLiked;
            if (!favVideo.isLiked) {
                binding.tvLikes.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_unlike, 0, 0);
                favVideo.likes = String.valueOf(Integer.parseInt(favVideo.likes) - 1);
                mLikedVideoViewModel.delete(favVideo);
            } else {
                binding.tvLikes.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_liked, 0, 0);
                favVideo.likes = String.valueOf(Integer.parseInt(favVideo.likes) + 1);
                binding.tvLikes.setText(favVideo.likes);
                mLikedVideoViewModel.insert(favVideo);
            }
            binding.tvLikes.setText(favVideo.likes);
        });

        binding.ivShare.setOnClickListener(v -> {
            String type = "text/plain";
            String subject = "Checkout this video";
            String extraText = "Checkout this video \n" + favVideo.url;
            String shareWith = "Share with";

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(type);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, extraText);
            startActivity(Intent.createChooser(intent, shareWith));
        });
    }

    void setdata(LikedVideo video) {
        binding.videoView.setVideoPath(video.url);
        binding.videoTitle.setText(video.title);
        binding.videoDescription.setText(video.descrtion);
        binding.tvLikes.setText(video.likes);
        binding.tvComments.setText(video.comments);
        Glide.with(this)
                .load(video.profilePicUrl)
                .centerCrop()
                .circleCrop()
                .into(binding.ivProfilePic);

        binding.videoView.setOnPreparedListener(mp -> {
            binding.progressBar.setVisibility(View.GONE);
            mp.start();
        });
        binding.videoView.setOnCompletionListener(mp -> mp.start());
        binding.tvLikes.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_liked, 0, 0);
    }
}