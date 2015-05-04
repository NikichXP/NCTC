package com.netcracker.rest;

import com.netcracker.session.SessionHandler;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Rest container for auth methods
 * Created by NikichXP on 04.05.2015.
 */
@Stateless
@Path("auth")
public class AuthRest {
    /** 0x41 is A */
    public static int upperCharStartValue = 0x41;
    public static int alphabetLength = 0x5A - 0x41 + 1; //cause I don't know how many of them in english
    /** 0x41 is a */
    public static int lowerCharStartValue = 0x61;

    @GET
    @Path("auth")
    @Produces("text/plain")
    public String generateAuth() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SessionHandler.TOKEN_SIZE; i++) {
            if (Math.random() < 0.15) {
                int n = (int) (Math.random() * 10);
                sb.append(n);
            } else if (Math.random() > 0.5) {
                int n = (int) (Math.random() * alphabetLength);
                n += upperCharStartValue;
                sb.append((char) n);
            } else {
                int n = (int) (Math.random() * alphabetLength);
                n += lowerCharStartValue;
                sb.append((char) n);
            }
        }
        SessionHandler.createSession(sb.toString());
        return sb.toString();
    }

}
