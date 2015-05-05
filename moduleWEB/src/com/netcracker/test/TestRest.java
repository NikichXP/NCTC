package com.netcracker.test;

import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by NikichXP on 05.05.2015.
 */
@Stateless
@Path("test")
public class TestRest {

    @GET
    @Path("user/{name}")
    @Produces("application/json")
    @Consumes("text/plain")
    public User getUser(@PathParam("name")String m) {
        User u = new User(m);
        return u;
    }

    @GET
    @Path("ping")
    @Produces("text/plain")
    public String ping() {
        return "ping!";
    }

}
