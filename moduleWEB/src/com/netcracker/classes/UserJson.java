package com.netcracker.classes;

import java.io.Serializable;

/**
 * Created by NikichXP on 05.05.2015.
 */
public class UserJson implements Serializable {
    public String name;
    public String pass;

    public UserJson() {

    }

    public UserJson(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
