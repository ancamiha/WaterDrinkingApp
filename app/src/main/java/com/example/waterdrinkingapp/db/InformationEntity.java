package com.example.waterdrinkingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//@Entity(foreignKeys = @ForeignKey(entity = RegisterEntity.class,
//        parentColumns = "id",
//        childColumns = "registerId",
//        onDelete = ForeignKey.NO_ACTION
//))
@Entity(tableName = "InformationTable")
public class InformationEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

//    @ColumnInfo(name = "registerId")
//    private int registerId;

    @ColumnInfo(name = "weight")
    private String weight;

    @ColumnInfo(name = "activity")
    private String activity;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
//    public int getRegisterId() {
//        return registerId;
//    }
//    public void setRegisterId(int registerId) {
//        this.registerId = registerId;
//    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
}
