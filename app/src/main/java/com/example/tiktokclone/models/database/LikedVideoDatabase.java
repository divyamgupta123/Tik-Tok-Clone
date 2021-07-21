package com.example.tiktokclone.models.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tiktokclone.models.entities.LikedVideo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LikedVideo.class}, version = 1, exportSchema = false)
public abstract class LikedVideoDatabase extends RoomDatabase {
    public abstract LikedVideoDao favVideoDao();

    private static volatile LikedVideoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static LikedVideoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LikedVideoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LikedVideoDatabase.class, "fav_video_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
