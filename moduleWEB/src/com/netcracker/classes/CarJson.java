package com.netcracker.classes;

import java.math.BigInteger;

/**
 * Created by Juger on 20.05.2015.
 */
public class CarJson {

    private String id;

    private String model;
    private String licencePlate;
    private String classId;
    private String requiredDriverCategory;
    private String userId;
    private String airConditioner;
    private String seatCount;

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licensePlate) {
        this.licencePlate = licensePlate;
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
                "\"seatCount\":" + (seatCount == null ? "null" : "\"" + seatCount + "\"") +
                "\"model\":" + (model == null ? "null" : "\"" + model + "\"") + ", " +
                "\"licencePlate\":" + (licencePlate == null ? "null" : "\"" + licencePlate + "\"") + ", " +
                "\"classId\":" + (classId == null ? "null" : "\"" + classId + "\"") + ", " +
                "\"requiredDriverCategory\":" + (requiredDriverCategory == null ? "null" : "\"" + requiredDriverCategory + "\"") + ", " +
                "\"userId\":" + (userId == null ? "null" : "\"" + userId + "\"") + ", " +
                "\"airConditioner\":" + (airConditioner == null ? "null" : "\"" + airConditioner + "\"") +
                "}";
    }
}
