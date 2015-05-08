package com.netcracker.rest;

import com.netcracker.classes.UserJson;
import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;
import com.netcracker.session.SessionHandler;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Date;

/**
 * User facade for ReST
 *
 * @author NikichXP
 */
@Stateless
@Path("user")
public class UserRest {
	@EJB
	User user;

	@GET
	@Path("checkAuth/{sid}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String checkAuth(@PathParam("sid") String sid) {
		return Boolean.toString(SessionHandler.isValidSession(sid));
	}

	@POST
	@Path("login")
	@Consumes("application/json")
	public Response getUser(UserJson userJson) {
		UserEntity userEntity = null;
		if (userJson.getCred().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
			userEntity = user.loginByEmail(userJson.getCred(), userJson.getPass());
		} else if (userJson.getCred().matches("\\d+")) {
			userEntity = user.loginByPhone(userJson.getCred(), userJson.getPass());
		}

		if (userEntity != null) {
			return Response.status(200).entity(userEntity.toString()).build();
		} else {
			return Response.status(404).entity("Bad login credentials").build();
		}

	}

	@POST
	@Path("create")
	@Consumes("application/json")
	public Response createUser(UserJson userJson) {
		UserEntity userEntity = null;
		if (!user.isEmailUsed(userJson.getEmail()) && !user.isPhoneUsed(userJson.getPhone())) {
			userEntity = new UserEntity();
			userEntity.setFirstName(userJson.getFirstName());
			userEntity.setLastName(userJson.getLastName());
			userEntity.setPassword(userJson.getPass());
			userEntity.setPhone(userJson.getPhone());
			userEntity.setEmail(userJson.getEmail());
			userEntity.setDateRegistered(new Timestamp(new Date().getTime()));
			user.create(userEntity);
		}

		if (userEntity == null) {
			return Response.status(404).entity("Pass or email is already in use").build();
		} else {
			return Response.status(201).entity(userEntity.toString()).build();
		}
	}


}
