package com.netcracker.rest;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;
import com.netcracker.classes.UserJson;
import com.netcracker.rest.utilize.Registration;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

/**
 * User facade for ReST 
 * @author NikichXP
 */
@Stateless
@Path("user")
public class UserRest {
	@EJB
	User user;


	@POST
	@Path ("login")
	@Consumes("application/json")
<<<<<<< HEAD
	public Response getUser(UserJson userJsone) {
		UserEntity userEntity = user.loginByEmail(userJsone.getEmail(), userJsone.getPass());
		if(userEntity == null){
			userEntity = user.loginByPhone(userJsone.getPhone(), userJsone.getPass());
=======
	@Produces("application/json")
	public UserJson getUser(UserJson user) {
		//String result = "User saved: " + user.toString();
		return user;
	}

	/**
	 * Login method
	 * @param email - login email to auth
	 * @param password
	 * @return - text
	 */
	@GET
	@Path ("loginByEmail/{email}/{password}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String getUserIdByEmail (@PathParam("email") String email, @PathParam("password") String password) {
		UserEntity userEntity = user.loginByEmail(email, password);
		if (userEntity != null) {
			return String.format("%s got by %s %s", userEntity.toString(), email, password); //should be changed later
>>>>>>> origin/master
		}
		if (userEntity != null) {
			return Response.status(200).entity(userJsone.toString()).build();
			//we use session
		} else {
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
