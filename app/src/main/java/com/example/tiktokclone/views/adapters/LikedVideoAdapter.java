package com.example.tiktokclone.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tiktokclone.R;
import com.example.tiktokclone.databinding.LikedVideoItemBinding;
import com.example.tiktokclone.models.entities.LikedVideo;
import com.example.tiktokclone.utils.Constants;
import com.example.tiktokclone.views.activities.LikedVideoPlayActivity;

import java.util.List;

public class LikedVideoAdapter extends RecyclerView.Adapter<LikedVideoAdapter.ViewHolder> {

    List<LikedVideo> videos;

    Context context;

    @NonNull
    @Override
    public LikedVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new LikedVideoAdapter.ViewHolder(LikedVideoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(LikedVideoAdapter.ViewHolder holder, int position) {
        holder.setdata(videos.get(position));
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, LikedVideoPlayActivity.class);
            intent.putExtra(Constants.LIKED_VIDEO_INTENT_KEY, videos.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        TextView videoDescription;
        ImageView profilePic;
        ImageView thumbnail;

        public ViewHolder(@NonNull LikedVideoItemBinding itemView) {
            super(itemView.getRoot());
            videoTitle = itemView.videoTitle;
            videoDescription = itemView.videoDescription;
            profilePic = itemView.ivProfilePic;
            thumbnail = itemView.ivThumbnail;
        }

        void setdata(LikedVideo video) {
            Glide.with(context).load(video.thumbnail).centerCrop().into(thumbnail);
            videoTitle.setText(video.title);
            videoDescription.setText(video.descrtion);
            Glide.with(context)
                    .load(video.profilePicUrl)
                    .apply(new RequestOptions()
                            .centerCrop()
                            .circleCrop()
                            .placeholder(R.drawable.ic_baseline_broken_image_24))
                    .into(profilePic);
        }
    }

    public void setVideosList(List<LikedVideo> list) {
        videos = list;
        notifyDataSetChanged();
    }

}

