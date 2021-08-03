package com.example.waterdrinkingapp.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {InformationEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract InformationDao informationDao();

    public static AppDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "app-database")
                    .build();
        }
        return INSTANCE;
    }
}
