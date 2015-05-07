package com.netcracker.rest.utilize;

import com.netcracker.entity.UserEntity;

/**
 * Created by Juger on 07.05.2015.
 */
public class Registration {

    public static String isNotFound(UserEntity userEntity) {
        if(userEntity.getPhone() == null && userEntity.getEmail() == null)
            return "set phone or email";
        if(userEntity.getFirstName() == null)
            return "set firstName";
        if(userEntity.getPassword() == null)
            return "set password";
        else
            return null;
    }
}
