package com.netcracker.classes;

import java.io.Serializable;

/**
 * Created by NikichXP on 05.05.2015.
 */
public class UserJson implements Serializable {

    private String Pass;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;

    public UserJson() {

    }

    public UserJson(String pass, String email, String firstname, String lastname, String phone) {
        this.email = email;
        this.Pass = pass;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getPass() {
        return Pass;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPass(String pass) {
        this.Pass = pass;
    }

    public void setEmail(String email){this.email = email;}

    public void setFirstName(String firstname){this.firstname = firstname;}

    public void setLastName(String lastname){this.lastname = lastname;}

    public void setPhone(String phone){this.phone=phone;}
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", Pass='" + Pass + '\'' +
                '}';
    }
}
