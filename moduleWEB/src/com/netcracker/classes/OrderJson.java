package com.netcracker.classes;

public class OrderJson {

    private String id;
    private String publicToken;
    private String customerUserId;
    private String customerUserUuid;
    private String driverUserId;
    private String driverUserUuid;
    private String contactName;
    private String contactPhone;
    private String requestedSeatsCount;

    private String email;

    private String type;
    private String state;

    private String timeCreated;
    private String timeRequested;
    private String timeOfDriverArrival;
    private String timeStarted;
    private String timeCompleted;

    private String fromAddress;
    private String fromX;
    private String fromY;
    private String[] toAddress;
    private String[] distance;
    private String[] toX;
    private String[] toY;
    private String[] pathId;
    private String[] pathCompleted;
    private String pathsCompleted;

    private String sex;

    private String carClass;
    private String musicType;
    private boolean asSoonAsPossible;
    private boolean smokingFriendly;
    private boolean animalFriendly;
    private boolean wifi;
    private boolean airConditioner;
    private String customerPreCreateComment;

    private String customerPostCompleteComment;
    private String customerRefuseCause;
    private String driverRefuseCause;
    private String customerRefuseComment;
    private String driverRefuseComment;
    private String totalLength;

    private String totalMultiplier;
    private String totalPrice;
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public void setPublicToken(String publicToken) {
        this.publicToken = publicToken;
    }

    public String getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(String customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getCustomerUserUuid() {
        return customerUserUuid;
    }

    public void setCustomerUserUuid(String customerUserUuid) {
        this.customerUserUuid = customerUserUuid;
    }

    public String getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(String driverUserId) {
        this.driverUserId = driverUserId;
    }

    public String getDriverUserUuid() {
        return driverUserUuid;
    }

    public void setDriverUserUuid(String driverUserUuid) {
        this.driverUserUuid = driverUserUuid;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRequestedSeatsCount() {
        return requestedSeatsCount;
    }

    public void setRequestedSeatsCount(String requestedSeatsCount) {
        this.requestedSeatsCount = requestedSeatsCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeRequested() {
        return timeRequested;
    }

    public void setTimeRequested(String timeRequested) {
        this.timeRequested = timeRequested;
    }

    public String getTimeOfDriverArrival() {
        return timeOfDriverArrival;
    }

    public void setTimeOfDriverArrival(String timeOfDriverArrival) {
        this.timeOfDriverArrival = timeOfDriverArrival;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public String getPathsCompleted() {
        return pathsCompleted;
    }

    public void setPathsCompleted(String pathsCompleted) {
        this.pathsCompleted = pathsCompleted;
    }

    public void setTimeStarted(String timeStarted) {
        this.timeStarted = timeStarted;
    }

    public String getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(String timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromX() {
        return fromX;
    }

    public void setFromX(String fromX) {
        this.fromX = fromX;
    }

    public String getFromY() {
        return fromY;
    }

    public void setFromY(String fromY) {
        this.fromY = fromY;
    }

    public String[] getToAddress() {
        return toAddress;
    }

    public void setToAddress(String[] toAddress) {
        this.toAddress = toAddress;
    }

    public String[] getDistance() {
        return distance;
    }

    public void setDistance(String[] distance) {
        this.distance = distance;
    }

    public String[] getToX() {
        return toX;
    }

    public void setToX(String[] toX) {
        this.toX = toX;
    }

    public String[] getToY() {
        return toY;
    }

    public void setToY(String[] toY) {
        this.toY = toY;
    }

    public String[] getPathId() {
        return pathId;
    }

    public void setPathId(String[] pathId) {
        this.pathId = pathId;
    }

    public String[] getPathCompleted() {
        return pathCompleted;
    }

    public void setPathCompleted(String[] pathCompleted) {
        this.pathCompleted = pathCompleted;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public boolean isAsSoonAsPossible() {
        return asSoonAsPossible;
    }

    public void setAsSoonAsPossible(boolean asSoonAsPossible) {
        this.asSoonAsPossible = asSoonAsPossible;
    }

    public boolean isSmokingFriendly() {
        return smokingFriendly;
    }

    public void setSmokingFriendly(boolean smokingFriendly) {
        this.smokingFriendly = smokingFriendly;
    }

    public boolean isAnimalFriendly() {
        return animalFriendly;
    }

    public void setAnimalFriendly(boolean animalFriendly) {
        this.animalFriendly = animalFriendly;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String getCustomerPreCreateComment() {
        return customerPreCreateComment;
    }

    public void setCustomerPreCreateComment(String customerPreCreateComment) {
        this.customerPreCreateComment = customerPreCreateComment;
    }

    public String getCustomerPostCompleteComment() {
        return customerPostCompleteComment;
    }

    public void setCustomerPostCompleteComment(String customerPostCompleteComment) {
        this.customerPostCompleteComment = customerPostCompleteComment;
    }

    public String getCustomerRefuseCause() {
        return customerRefuseCause;
    }

    public void setCustomerRefuseCause(String customerRefuseCause) {
        this.customerRefuseCause = customerRefuseCause;
    }

    public String getDriverRefuseCause() {
        return driverRefuseCause;
    }

    public void setDriverRefuseCause(String driverRefuseCause) {
        this.driverRefuseCause = driverRefuseCause;
    }

    public String getCustomerRefuseComment() {
        return customerRefuseComment;
    }

    public void setCustomerRefuseComment(String customerRefuseComment) {
        this.customerRefuseComment = customerRefuseComment;
    }

    public String getDriverRefuseComment() {
        return driverRefuseComment;
    }

    public void setDriverRefuseComment(String driverRefuseComment) {
        this.driverRefuseComment = driverRefuseComment;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(String totalLength) {
        this.totalLength = totalLength;
    }

    public String getTotalMultiplier() {
        return totalMultiplier;
    }

    public void setTotalMultiplier(String totalMultiplier) {
        this.totalMultiplier = totalMultiplier;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    private String arrayToJson(Object[] var0){
        if(var0 == null) {
            return "null";
        } else {
            int var1 = var0.length - 1;
            if(var1 == -1) {
                return "[]";
            } else {
                StringBuilder var2 = new StringBuilder();
                var2.append('[');
                var2.append('\"');
                int var3 = 0;

                while(true) {
                    var2.append(String.valueOf(var0[var3]));
                    if(var3 == var1) {
                        return var2.append("\"]").toString();
                    }

                    var2.append("\", \"");
                    ++var3;
                }
            }
        }
    }

    public static String toString(Object[] var0) {
        if(var0 == null) {
            return "null";
        } else {
            int var1 = var0.length - 1;
            if(var1 == -1) {
                return "[]";
            } else {
                StringBuilder var2 = new StringBuilder();
                var2.append('[');
                int var3 = 0;

                while(true) {
                    var2.append(String.valueOf(var0[var3]));
                    if(var3 == var1) {
                        return var2.append(']').toString();
                    }

                    var2.append(", ");
                    ++var3;
                }
            }
        }
    }


    @Override
    public String toString() {
        return "{\"id\":" + (id == null ? "null" : "\"" + id + "\"") + ", " +
                "\"publicToken\":" + (publicToken == null ? "null" : "\"" + publicToken + "\"") + ", " +
                "\"customerUserId\":" + (customerUserId == null ? "null" : "\"" + customerUserId + "\"") + ", " +
                "\"customerUserUuid\":" + (customerUserUuid == null ? "null" : "\"" + customerUserUuid + "\"") + ", " +
                "\"driverUserId\":" + (driverUserId == null ? "null" : "\"" + driverUserId + "\"") + ", " +
                "\"driverUserUuid\":" + (driverUserUuid == null ? "null" : "\"" + driverUserUuid + "\"") + ", " +
                "\"contactName\":" + (contactName == null ? "null" : "\"" + contactName + "\"") + ", " +
                "\"contactPhone\":" + (contactPhone == null ? "null" : "\"" + contactPhone + "\"") + ", " +
                "\"requestedSeatsCount\":" + (requestedSeatsCount == null ? "null" : "\"" + requestedSeatsCount + "\"") + ", " +
                "\"email\":" + (email == null ? "null" : "\"" + email + "\"") + ", " +
                "\"type\":" + (type == null ? "null" : "\"" + type + "\"") + ", " +
                "\"state\":" + (state == null ? "null" : "\"" + state + "\"") + ", " +
                "\"timeCreated\":" + (timeCreated == null ? "null" : "\"" + timeCreated + "\"") + ", " +
                "\"timeRequested\":" + (timeRequested == null ? "null" : "\"" + timeRequested + "\"") + ", " +
                "\"timeOfDriverArrival\":" + (timeOfDriverArrival == null ? "null" : "\"" + timeOfDriverArrival + "\"") + ", " +
                "\"timeStarted\":" + (timeStarted == null ? "null" : "\"" + timeStarted + "\"") + ", " +
                "\"timeCompleted\":" + (timeCompleted == null ? "null" : "\"" + timeCompleted + "\"") + ", " +
                "\"fromAddress\":" + (fromAddress == null ? "null" : "\"" + fromAddress + "\"") + ", " +
                "\"fromX\":" + (fromX == null ? "null" : "\"" + fromX + "\"") + ", " +
                "\"fromY\":" + (fromY == null ? "null" : "\"" + fromY + "\"") + ", " +
                "\"toAddress\":" + arrayToJson(toAddress) + ", " +
                "\"distance\":" + arrayToJson(distance) + ", " +
                "\"toX\":" + arrayToJson(toX) + ", " +
                "\"toY\":" + arrayToJson(toY) + ", " +
                "\"pathId\":" + arrayToJson(pathId) + ", " +
                "\"pathCompleted\":" + arrayToJson(pathCompleted) + ", " +
                "\"pathsCompleted\":" + (pathsCompleted == null ? "null" : "\"" + pathsCompleted + "\"") + ", " +
                "\"sex\":" + (sex == null ? "null" : "\"" + sex + "\"") + ", " +
                "\"carClass\":" + (carClass == null ? "null" : "\"" + carClass + "\"") + ", " +
                "\"musicType\":" + (musicType == null ? "null" : "\"" + musicType + "\"") + ", " +
                "\"asSoonAsPossible\":\"" + asSoonAsPossible + "\"" + ", " +
                "\"smokingFriendly\":\"" + smokingFriendly + "\"" + ", " +
                "\"animalFriendly\":\"" + animalFriendly + "\"" + ", " +
                "\"wifi\":\"" + wifi + "\"" + ", " +
                "\"airConditioner\":\"" + airConditioner + "\"" + ", " +
                "\"customerPreCreateComment\":" + (customerPreCreateComment == null ? "null" : "\"" + customerPreCreateComment + "\"") + ", " +
                "\"customerPostCompleteComment\":" + (customerPostCompleteComment == null ? "null" : "\"" + customerPostCompleteComment + "\"") + ", " +
                "\"customerRefuseCause\":" + (customerRefuseCause == null ? "null" : "\"" + customerRefuseCause + "\"") + ", " +
                "\"driverRefuseCause\":" + (driverRefuseCause == null ? "null" : "\"" + driverRefuseCause + "\"") + ", " +
                "\"customerRefuseComment\":" + (customerRefuseComment == null ? "null" : "\"" + customerRefuseComment + "\"") + ", " +
                "\"driverRefuseComment\":" + (driverRefuseComment == null ? "null" : "\"" + driverRefuseComment + "\"") + ", " +
                "\"totalLength\":" + (totalLength == null ? "null" : "\"" + totalLength + "\"") + ", " +
                "\"totalMultiplier\":" + (totalMultiplier == null ? "null" : "\"" + totalMultiplier + "\"") + ", " +
                "\"totalPrice\":" + (totalPrice == null ? "null" : "\"" + totalPrice + "\"") +
                "}";
    }
}
