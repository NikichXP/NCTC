package com.netcracker.session;

import com.netcracker.entity.UserEntity;

import java.time.*;

/** Created by NikichXP on 04.05.2015. */
public class Session {
    /** Session token */
    private final String token;
    /** Link to user entity */
    private final UserEntity userEntity;
    /** Session expiration time */
    private LocalDateTime expireTime;

    /**
     * Session can only be constructed by it's own unique token id
     * @param sessionToken - session token id
     */
    protected Session(String sessionToken, UserEntity userEntity) {
        this.userEntity = userEntity;
        this.token = sessionToken;
        expireTime = LocalDateTime.now().plusMinutes(SessionHandler.DEFAULT_SESSION_TIME);
    }

    public UserEntity getUserEntity (){
        return userEntity;
    }

    /**
     * Getter for token
     * @return
     */
    public String getSessionToken () {
        if (LocalDateTime.now().isBefore(expireTime)) {
            return token;
        } else {
            return null;
        }
    }

    /**
     * Check if it is valid
     * @return - true if valid, false - if invalid
     */
    protected boolean validate() {
        if (LocalDateTime.now().isAfter(expireTime)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Refresh session active time
     */
    protected void refresh () {
        this.expireTime = LocalDateTime.now().plusMinutes(SessionHandler.DEFAULT_SESSION_TIME + 1);
    }
}
