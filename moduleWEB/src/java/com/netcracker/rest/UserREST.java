package com.netcracker.rest;

import com.netcracker.facade.UserEntityFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * User facade for ReST 
 * @author NikichXP
 */
@Stateless
@Path("user")
public class UserREST {
    @EJB
    private UserEntityFacade userEntityFacade;
    
    /**
     * Login method
     * @param name
     * @param pass
     * @return 
     */
    @GET
    @Path ("login/{username}/{pass}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String getUserID (@PathParam("username") String name, @PathParam("pass") String pass) {
        //TODO Ask Victor how to "speak" with DB
        return "wololo"; //should be changed later
    }
    
}
