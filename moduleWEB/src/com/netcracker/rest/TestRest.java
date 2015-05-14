package com.netcracker.rest;

import com.netcracker.service.Mail;

import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by NikichXP on 14.05.2015.
 */
@Stateless
@Path("test")
public class TestRest {

    @GET
    @Path("sendMail")
    @Produces("text/plain")
    public String sendMail() {
        return Mail.test2();
    }

}
