package com.netcracker.classes;

import java.math.BigInteger;

/**
 * Created by Juger on 20.05.2015.
 */
public class CarJson {

    private BigInteger id;

    private String model;
    private String licensePlate;
    private String classId;
    private String requiredDriverCategory;
    private String userId;
    private String airConditioner;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getRequiredDriverCategory() {
        return requiredDriverCategory;
    }

    public void setRequiredDriverCategory(String requiredDriverCategory) {
        this.requiredDriverCategory = requiredDriverCategory;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(String airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public String toString() {
        return "{\"id\":" + (id == null ? "null" : "\"" + id + "\"") + ", " +
                "\"model\":" + (model == null ? "null" : "\"" + model + "\"") + ", " +
                "\"licensePlate\":" + (licensePlate == null ? "null" : "\"" + licensePlate + "\"") + ", " +
                "\"classId\":" + (classId == null ? "null" : "\"" + classId + "\"") + ", " +
                "\"requiredDriverCategory\":" + (requiredDriverCategory == null ? "null" : "\"" + requiredDriverCategory + "\"") + ", " +
                "\"userId\":" + (userId == null ? "null" : "\"" + userId + "\"") + ", " +
                "\"airConditioner\":" + (airConditioner == null ? "null" : "\"" + airConditioner + "\"") +
                "}";
    }
}
