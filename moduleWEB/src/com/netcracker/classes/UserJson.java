package com.netcracker.classes;

/**
 * Created by NikichXP on 05.05.2015.
 */
public class UserJson {

    private String cred;

    private String id;
    private String uuid;

    private String userSex;
    private String animalFriendly;
    private String smokingFriendly;

    private String pass;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String alternativePhone;
    private String carId;

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getSmokingFriendly() {
        return smokingFriendly;
    }

    public void setSmokingFriendly(String smokingFriendly) {
        this.smokingFriendly = smokingFriendly;
    }

    public String getAnimalFriendly() {
        return animalFriendly;
    }

    public void setAnimalFriendly(String animalFriendly) {
        this.animalFriendly = animalFriendly;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCred() {
        return cred;
    }

    public void setCred(String cred) {
        this.cred = cred;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email){this.email = email;}

    public void setFirstName(String firstname){this.firstName = firstname;}

    public void setLastName(String lastname){this.lastName = lastname;}

    public void setPhone(String phone){this.phone=phone;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserJson{");
        sb.append("cred='").append(cred).append('\'');
        sb.append(", pass='").append(pass).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", userSex='").append(userSex).append('\'');
        sb.append(", alternativePhone='").append(alternativePhone).append('\'');
        sb.append(", animalFriendly='").append(animalFriendly).append('\'');
        sb.append(", smokingFriendly='").append(smokingFriendly).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
