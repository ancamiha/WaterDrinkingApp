package com.example.waterdrinkingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("select * from HistoryTable")
    public abstract List<HistoryEntity> getEntities();

    @Insert
    void insertDetails(HistoryEntity data);

    @Delete
    void deleteDetails(HistoryEntity data);

    @Query("delete from HistoryTable")
    void deleteAllData();
}
