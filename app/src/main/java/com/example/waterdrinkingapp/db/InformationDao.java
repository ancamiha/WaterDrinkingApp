package com.example.waterdrinkingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InformationDao {
    @Query("select * from InformationTable")
    public abstract List<InformationEntity> getEntities();

    @Insert
    void insertDetails(InformationEntity data);

    @Update
    void updateDetails(InformationEntity data);

    @Delete
    void deleteDetails(InformationEntity data);

    @Query("delete from InformationTable")
    void deleteAllData();
}
