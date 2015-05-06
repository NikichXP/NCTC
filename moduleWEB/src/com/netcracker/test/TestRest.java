package com.netcracker.test;

import com.google.gson.Gson;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by NikichXP on 05.05.2015.
 */
@Stateless
@Path("test")
public class TestRest {

    @POST
    @Path("user")
    @Consumes("application/json")
    public Response getUser(User user) {
        String result = "User saved: " + user.toString();
        return Response.status(201).entity(result).build();
    }

    @GET
    @Path("ping")
    @Produces("text/plain")
    public String ping() {
        return "ping!";
    }

}
