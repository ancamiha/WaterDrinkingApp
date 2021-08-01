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

    @Query("select water_intake from InformationTable where registerId=:uid")
    int getWaterIntake(String uid);

    @Query("select current_quantity from InformationTable where registerId=:uid")
    int getCurrentQuantity(String uid);

    @Query("update InformationTable set current_quantity=:qty where registerId=:uid")
    void updateDetails(int qty, String uid);

    @Delete
    void deleteDetails(InformationEntity data);

    @Query("delete from InformationTable")
    void deleteAllData();
}
