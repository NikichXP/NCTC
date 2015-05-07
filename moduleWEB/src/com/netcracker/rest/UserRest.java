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
 *
 * @author NikichXP
 */
@Stateless
@Path("user")
public class UserRest {
	@EJB
	User user;

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
			return Response.status(200).entity(user.toString()).build();
		} else {
			return Response.status(404).entity("Bad login credentials").build();
		}
	}

	@POST
	@Path("/create")
	@Consumes("text/json")
	public Response createUser(UserJson userJsone) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userJsone.getFirstName());
		userEntity.setLastName(userJsone.getLastName());
		userEntity.setPassword(userJsone.getLastName());
		userEntity.setPhone(userJsone.getPhone());
		userEntity.setEmail(userJsone.getEmail());
		if (Registration.isNotFound(userEntity) == null) {
			return Response.status(200).build();
		} else {
			return Response.status(404).entity(Registration.isNotFound(userEntity)).build();
		}
	}


}