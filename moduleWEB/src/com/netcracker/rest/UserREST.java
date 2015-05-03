package com.netcracker.rest;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.UserEntityFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.*;

/**
 * User facade for ReST 
 * @author NikichXP
 */
@Stateless
@Path("user")
public class UserREST {
    @EJB
    UserEntityFacade uef;

    /**
     * Login method
     * @param name - login name to auth
     * @param pass - password
     * @return -
     */
    @GET
    @Path ("login/{username}/{pass}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String getUserID (@PathParam("username") String name, @PathParam("pass") String pass) {
        //TODO Ask Victor how to "speak" with DB
        List<UserEntity> user = uef.findAll();
        String ret = new String();
        for (int i = 0; i < user.size(); i++) {
            ret += user.get(i).toString();
        }
        return ret + name + " | " + pass; //should be changed later

    }
    
}
