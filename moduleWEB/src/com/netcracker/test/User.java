package com.netcracker.test;

import java.io.Serializable;

/**
 * Created by NikichXP on 05.05.2015.
 */
public class User implements Serializable {
    public String name;

    public User () {

    }
    public User (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
