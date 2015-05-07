package com.netcracker.session;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.*;

/**
 * Created by NikichXP on 04.05.2015.
 */
@Singleton
public class SessionHandler {

    @EJB
    User u;
    /**
     * Default session time (in minutes)
     */
    public static final int DEFAULT_SESSION_TIME = 30; //minutes
    private static LinkedList<Session> activeSessions = new LinkedList<>();

    /**
     * Creates session
     * @param sessionToken - unique session token
     * @return - flag of success
     */
    public boolean createSession (String sessionToken, String login) {
        try {
            UserEntity user = null;
            if (login.matches("0[0-9]{9}")) {
                login = "+38" + login;
            }
            if (login.matches("\\+380[0-9]{9}")) {
                user = u.findByPhone(login);
            } else if (login.matches("[a-zA-Z0-9]+@[a-z0-9]+.[a-z0-9]{2,}")) {
                user = u.findByEmail(login);
            } else {
                throw new Exception();
            }
            activeSessions.add(new Session(sessionToken, user));
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

    public String generateSession(String loginData) {
        UUID uuid = UUID.randomUUID();
        createSession(uuid.toString(), loginData);
        return uuid.toString();
    }
}
