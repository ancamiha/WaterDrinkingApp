package com.example.waterdrinkingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("select * from InformationTable")
    public abstract List<InformationEntity> getEntities();

    @Insert
    void insertDetails(InformationEntity data);

    @Delete
    void deleteDetails(InformationEntity data);

    @Query("delete from InformationTable")
    void deleteAllData();
}
