package com.netcracker.session;

import javax.ejb.Singleton;
import java.util.*;

/**
 * Created by NikichXP on 04.05.2015.
 */
@Singleton
public class SessionHandler {
    /**
     * Default session time (in minutes)
     */
    public static final int DEFAULT_SESSION_TIME = 30; //minutes
    private static LinkedList<Session> activeSessions = new LinkedList<Session>();

    /**
     * Creates session
     * @param sessionToken - unique session token
     * @return - flag of success
     */
    public static boolean createSession (String sessionToken) {
        try {
            activeSessions.add(new Session(sessionToken));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if session is valid
     * @param sessionToken - session token to be checked
     * @return - result of validity
     */
    public static boolean isValidSession(String sessionToken) {
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

    /**
     * Session stops being valid
     * @param sessionToken - Session to stop being valid
     */
    public static void invalidate (String sessionToken) {
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

    /**
     * Extends session validity up to 30 minutes
     * @param sessionToken - session to be refreshed
     * @return - result
     */
    public boolean refresh (String sessionToken) {
        Iterator<Session> it = activeSessions.iterator();
        Session ptr;
        int i = 0;
        while (it.hasNext()) {
            ptr = it.next();
            if (ptr.getSessionToken().equals(sessionToken)) {
                activeSessions.remove(i);
                ptr.refresh();
                activeSessions.add(ptr);
                return true;
            }
            i++;
        }
        return false;
    }

    public static String generateSession() {
        UUID uuid = UUID.randomUUID();
        createSession(uuid.toString());
        return uuid.toString();
    }
}
