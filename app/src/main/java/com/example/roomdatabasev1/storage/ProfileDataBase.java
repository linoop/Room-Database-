package com.example.roomdatabasev1.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabasev1.dao.ProfileDao;
import com.example.roomdatabasev1.models.Profile;

@Database(entities = Profile.class, exportSchema = false, version = 1)
public abstract class ProfileDataBase extends RoomDatabase {

    private static final String DB_NAME = "profile_db";
    private static ProfileDataBase instance;

    public static synchronized ProfileDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ProfileDataBase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ProfileDao profileDao();
}
