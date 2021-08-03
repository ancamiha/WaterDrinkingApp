package com.example.waterdrinkingapp;

public class Info {
    public String registerId;
    public Double weight;
    public Integer currentQuantity, waterIntake, percent, activity;

    public Info() {

    }

    public Info(Integer currentQuantity, Integer percent) {
        this.currentQuantity = currentQuantity;
        this.percent = percent;
    }

    public Info(String registerId, Double weight, Integer currentQuantity,
                Integer waterIntake, Integer percent, Integer activity) {
        this.registerId = registerId;
        this.weight = weight;
        this.currentQuantity = currentQuantity;
        this.waterIntake = waterIntake;
        this.percent = percent;
        this.activity = activity;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Integer getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Integer waterIntake) {
        this.waterIntake = waterIntake;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }
}
