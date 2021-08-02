package com.example.waterdrinkingapp.db;

import androidx.lifecycle.LiveData;
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
    LiveData<Integer> getWaterIntake(String uid);

    @Query("select current_quantity from InformationTable where registerId=:uid")
    LiveData<Integer> getCurrentQuantity(String uid);

    @Query("select percent from InformationTable where registerId=:uid")
    LiveData<Integer> getPercent(String uid);

    @Query("update InformationTable set current_quantity=:qty where registerId=:uid")
    void updateCurrentQuantity(int qty, String uid);

//    @Query("update InformationTable set percent=" +
//            "(select current_quantity from InformationTable where registerId=:uid) * 100 " +
//            "/ (select water_intake from InformationTable where registerId=:uid) " +
//            "WHERE registerId=:uid;")
//    void updatePercent(String uid);

    @Delete
    void deleteDetails(InformationEntity data);

    @Query("delete from InformationTable")
    void deleteAllData();
}
