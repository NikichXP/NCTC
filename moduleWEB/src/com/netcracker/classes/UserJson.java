package com.netcracker.classes;

import java.io.Serializable;

/**
 * Created by NikichXP on 05.05.2015.
 */
public class UserJson implements Serializable {

    private String pass;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    public UserJson() {

    }

    public UserJson(String pass, String email, String firstName, String lastName, String phone) {
        this.email = email;
        this.pass = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
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

    public void setFirstName(String firstName){this.firstName=firstName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public void setPhone(String phone){this.phone=phone;}
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
