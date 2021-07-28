package com.example.waterdrinkingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "InformationTable")
public class InformationEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "registerId")
    private int registerId;

    @ColumnInfo(name = "weight")
    private Double weight;

    @ColumnInfo(name = "activity")
    private int activity;

    @ColumnInfo(name = "water_intake")
    private Double waterIntake;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRegisterId() {
        return registerId;
    }
    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public int getActivity() {
        return activity;
    }
    public void setActivity(int activity) {
        this.activity = activity;
    }
    public Double getWaterIntake() {
        return waterIntake;
    }
    public void setWaterIntake(Double waterIntake) {
        this.waterIntake = waterIntake;
    }
}
