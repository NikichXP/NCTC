package com.netcracker.rest;

import com.netcracker.session.SessionHandler;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.UUID;

/**
 * Rest container for auth methods
 * Created by NikichXP on 04.05.2015.
 */
@Stateless
@Path("auth")
public class AuthRest {
    @GET
    @Path("auth")
    @Produces("text/plain")
    public String generateAuth() {
        UUID uuid = UUID.randomUUID();
        SessionHandler.createSession(uuid.toString());
        return uuid.toString();
    }
}
