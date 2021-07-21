package com.example.tiktokclone.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokclone.R;
import com.example.tiktokclone.databinding.VideoLayoutBinding;
import com.example.tiktokclone.models.entities.Video;
import com.example.tiktokclone.views.activities.LikedVideoActivity;
import com.example.tiktokclone.views.activities.MainActivity;

import java.util.ArrayList;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    ArrayList<Video.Msg> videos;
    Activity activity;
    Context context;

    public VideoAdapter(Activity activity, ArrayList<Video.Msg> videos) {
        this.activity = activity;
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(VideoLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        holder.setdata(videos.get(position));
        holder.likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity instanceof MainActivity) {
                    Video.Msg video = videos.get(position);
                    int isLiked = video.getLiked();
                    if (isLiked == 0) {
                        video.setLiked(1);
                        video.getCount().setLike_count(video.getCount().getLike_count() + 1);
                        holder.likes.setText(String.valueOf(video.getCount().getLike_count()));
                        holder.likes.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_liked, 0, 0);
                        ((MainActivity) activity).makeFavVideo(video);
                    } else {
                        Toast.makeText(context,R.string.video_already_liked, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        holder.likedVideos.setOnClickListener(v -> {
            if (activity instanceof MainActivity) {
                Intent intent = new Intent(activity, LikedVideoActivity.class);
                context.startActivity(intent);
            }
        });
        holder.shareVideo.setOnClickListener(v -> {
            String type = "text/plain";
            String subject = "Checkout this video";
            String extraText = "Checkout this video \n" + videos.get(position).getVideo();
            String shareWith = "Share with";

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(type);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, extraText);
            context.startActivity(Intent.createChooser(intent, shareWith));
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView videoTitle;
        TextView videoDescription;
        ProgressBar progBar;
        ImageView profilePic;
        TextView likes;
        TextView comments;
        TextView likedVideos;
        ImageView shareVideo;

        public ViewHolder(@NonNull VideoLayoutBinding itemView) {
            super(itemView.getRoot());
            videoView = itemView.videoView;
            videoTitle = itemView.videoTitle;
            videoDescription = itemView.videoDescription;
            progBar = itemView.progressBar;
            profilePic = itemView.ivProfilePic;
            likes = itemView.tvLikes;
            comments = itemView.tvComments;
            likedVideos = itemView.tvSavedVideos;
            shareVideo = itemView.ivShare;
        }

        void setdata(Video.Msg video) {
            videoView.setVideoPath(video.getVideo());
            videoTitle.setText(video.getUser_info().getFirst_name());
            videoDescription.setText(video.getDescription());
            likes.setText(String.valueOf(video.getCount().getLike_count()));
            comments.setText(String.valueOf(video.getCount().getVideo_comment_count()));
            Glide.with(context)
                    .load(video.getUser_info().getProfile_pic())
                    .centerCrop()
                    .circleCrop()
                    .into(profilePic);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progBar.setVisibility(View.GONE);
                    mp.start();
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}
