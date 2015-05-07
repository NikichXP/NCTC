package com.netcracker.rest;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;
import com.netcracker.classes.UserJson;
import com.netcracker.rest.utilize.Registration;
import com.netcracker.session.SessionHandler;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.UUID;

/**
 * User facade for ReST 
 * @author NikichXP
 */
@Stateless
@Path("user")
public class UserRest {
	@EJB
	User user;


	@GET
	@Path("auth")
	@Produces("text/plain")
	public static String generateAuth() {
		return SessionHandler.generateSession(); //our session token
	}

	@POST
	@Path ("login")
	@Consumes("application/json")
	public Response getUser(UserJson userJsone) {
		UserEntity userEntity = user.loginByEmail(userJsone.getEmail(), userJsone.getPass());
		if(userEntity == null) {
			userEntity = user.loginByPhone(userJsone.getPhone(), userJsone.getPass());
		}
		if(userEntity != null){
			return Response.status(200).entity(user.toString()).build();
		}
		else{
			return Response.status(404).entity("user not found").build();
		}
	}

	@POST
	@Path("/create")
	@Consumes("text/json")
	public Response createUser(UserJson userJsone){
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userJsone.getFirstName());
		userEntity.setLastName(userJsone.getLastName());
		userEntity.setPassword(userJsone.getLastName());
		userEntity.setPhone(userJsone.getPhone());
		userEntity.setEmail(userJsone.getEmail());
		if(Registration.isNotFound(userEntity) == null){
			return Response.status(200).build();
		} else {
			return Response.status(404).entity(Registration.isNotFound(userEntity)).build();
		}
	}


}
