package com.netcracker.classes;

import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;

public class OrderJson implements Serializable {

    private String from;
    private String to;

    private String phone;
    private String gender;
    private String carType;

    private boolean wifi;
    private boolean petFriendly;
    private boolean smoking;
    private boolean cargo;
    private boolean airConditioner;

    public OrderJson() {

    }

    public OrderJson(String from, String to, String phone,
                     String gender, String carType, boolean wifi,
                     boolean petFriendly, boolean smoking,
                     boolean cargo, boolean airConditioner) {
        this.from = from;
        this.to = to;
        this.phone = phone;
        this.gender = gender;
        this.carType = carType;
        this.wifi = wifi;
        this.petFriendly = petFriendly;
        this.smoking = smoking;
        this.cargo = cargo;
        this.airConditioner = airConditioner;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isCargo() {
        return cargo;
    }

    public void setCargo(boolean cargo) {
        this.cargo = cargo;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderJson{");
        sb.append("from=").append(from);
        sb.append(", to=").append(to);
        sb.append(", phone=").append(phone);
        sb.append(", gender=").append(gender);
        sb.append(", carType=").append(carType);
        sb.append(", wifi=").append(wifi);
        sb.append(", petFriendly=").append(petFriendly);
        sb.append(", smoking=").append(smoking);
        sb.append(", cargo=").append(cargo);
        sb.append(", airConditioner=").append(airConditioner);
        sb.append('}');
        return sb.toString();
    }
}
