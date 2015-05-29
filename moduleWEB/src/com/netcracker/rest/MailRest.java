package com.netcracker.rest;

import com.netcracker.service.Mail;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("mail")
public class MailRest {

	@GET
	@Path("sendMail")
	public String sendMail(@QueryParam("mail") String mail, @QueryParam("theme") String theme, @QueryParam("text") String text) {
		return Mail.sendMail(mail, theme, text);
	}

	@GET
	@Path("sendGroupMail")
	public String sendGroupMail(@QueryParam("mail") String mail, @QueryParam("theme") String theme, @QueryParam("text") String text) {
		String[] mails = mail.split(",");
		for (String mailz : mails) {
			mailz.trim();
		}
		return Mail.groupSend(theme, text, mails);
	}
}
