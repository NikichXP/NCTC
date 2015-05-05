package com.netcracker.test;

import com.google.gson.Gson;

import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by NikichXP on 05.05.2015.
 */
@Stateless
@Path("test")
public class TestRest {

    @GET
    @Path("user")
    @Produces("text/plain")
    @Consumes("application/json")
    public String getUser(User user) {
       // Gson gson = new Gson();
       // User user = gson.fromJson(m, User.class);
        //User u = new User(m);
        return  user.toString(); //user.toString();
    }

    @GET
    @Path("ping")
    @Produces("text/plain")
    public String ping() {
        return "ping!";
    }

}
