package com.netcracker.rest;

import com.netcracker.service.Mail;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("test")
public class TestRest {

	@GET
	@Path("sendMail")
	@Produces("text/plain")
	public String sendMail() {
		return Mail.test2();
	}

}
