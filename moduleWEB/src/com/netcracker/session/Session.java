package com.netcracker.session;

import java.time.*;

/**
 * Created by NikichXP on 04.05.2015.
 */
public class Session {
    private final String token;
    private final LocalDateTime expireTime;

    public Session(String sessionToken) {
        this.token = sessionToken;
        expireTime = LocalDateTime.now().plusMinutes(SessionHandler.DEFAULT_SESSION_TIME);
    }

    public String getSessionToken () {
        if (LocalDateTime.now().isBefore(expireTime)) {
            return token;
        } else {
            return null;
        }
    }

    public boolean validate() {
        if (LocalDateTime.now().isAfter(expireTime)) {
            return false;
        } else {
            return true;
        }
    }
}
