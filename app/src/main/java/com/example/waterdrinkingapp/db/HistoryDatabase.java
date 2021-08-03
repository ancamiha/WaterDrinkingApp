package com.example.waterdrinkingapp.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {HistoryEntity.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    private static HistoryDatabase INSTANCE;

    public abstract HistoryDao historyDao();

    public static HistoryDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    HistoryDatabase.class,
                    "history-database")
                    .allowMainThreadQueries()
                    .build();

            INSTANCE.historyDao().insertDetails(new HistoryEntity("02.08.2021", 2000));
            INSTANCE.historyDao().insertDetails(new HistoryEntity("01.08.2021", 1750));
            INSTANCE.historyDao().insertDetails(new HistoryEntity("31.07.2021", 2250));
            INSTANCE.historyDao().insertDetails(new HistoryEntity("30.07.2021", 2000));
            INSTANCE.historyDao().insertDetails(new HistoryEntity("29.07.2021", 2000));
            INSTANCE.historyDao().insertDetails(new HistoryEntity("28.07.2021", 2250));
        }
        return INSTANCE;
    }
}

