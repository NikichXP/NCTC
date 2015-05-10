package com.netcracker.classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NikichXP on 05.05.2015.
 */
public class UserJson implements Serializable {

    private String cred;

    private String pass;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    public UserJson() {

    }

    public UserJson(String cred, String pass, String email, String lastName, String phone, String firstName) {
        this.cred = cred;
        this.pass = pass;
        this.email = email;
        this.lastName = lastName;
        this.phone = phone;
        this.firstName = firstName;
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
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
