package com.netcracker.session;

import javax.ejb.Singleton;
import java.util.*;

/**
 * Created by NikichXP on 04.05.2015.
 */
@Singleton
public class SessionHandler {
    public static final long DEFAULT_SESSION_TIME = 30; //minutes
    private static LinkedList<Session> activeSessions = new LinkedList<Session>();

    public boolean createSession (String sessionToken) {
        try {
            activeSessions.add(new Session(sessionToken));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean validateSession (String sessionToken) {
        while (!activeSessions.get(0).validate()) {
            activeSessions.remove(0);
        }
        Iterator<Session> it = activeSessions.iterator();
        Session ptr;
        while (it.hasNext()) {
            ptr = it.next();
            if (ptr.getSessionToken().equals(sessionToken)) {
                return true;            //Session is now validated!
            }
        }
        return false;
    }

    public void unvalidate (String sessionToken) {
        Iterator<Session> it = activeSessions.iterator();
        Session ptr;
        int i = 0;
        while (it.hasNext()) {
            ptr = it.next();
            if (ptr.getSessionToken().equals(sessionToken)) {
                activeSessions.remove(i);
                return;
            }
            i++;
        }
    }
}
