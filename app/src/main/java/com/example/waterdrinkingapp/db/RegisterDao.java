package com.example.waterdrinkingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RegisterDao {

    @Query("select * from RegisterTable")
    public abstract List<RegisterEntity> getEntities();

    @Query("select count(*) from RegisterTable where email=:email and password=:password ")
    public abstract int checkIfExists(String email, String password);

    @Insert
    void insertEntity(RegisterEntity data);

    @Update
    void updateEntity(RegisterEntity data);

    @Delete
    void deleteEntity(RegisterEntity data);

    @Query("delete from RegisterTable")
    void deleteAllData();
}
