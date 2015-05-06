package com.netcracker.rest;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
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
		}
		return "Wrong login credentials";
	}

	/**
	 * Login method
	 * @param phone - phone number to auth
	 * @param password
	 * @return - text
	 */
	@GET
	@Path ("loginByPhone/{phone}/{password}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String getUserIdByPhone (@PathParam("phone") String phone, @PathParam("password") String password) {
		UserEntity userEntity = user.loginByPhone(phone, password);
		if (userEntity != null) {
			return String.format("%s got by %s %s", userEntity.toString(), phone, password); //should be changed later
		}
		return "Wrong login credentials";
	}

	@GET
	@Path ("login/{id}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String getUserById (@PathParam("id") BigInteger id) throws Exception{
		UserEntity userEntity = user.read(id);
		if (user != null) {
			return userEntity.toString();
		}
		return "No such user in DB";
	}

	@POST
	@Path("/create/{firstName}/{lastName}/{password}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String createUser(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName, @PathParam("password") String pass){
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(firstName);
		userEntity.setLastName(lastName);
		userEntity.setPassword(pass);
		return userEntity.toString();
	}

	@PUT
	@Path("/update/{id}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String updateByIdUser(@PathParam("id") BigInteger id){
		UserEntity userEntity = user.read(id);
		//some to do
		return userEntity.toString();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void deleteByIdUser(@PathParam("id") BigInteger id){
		try {
			user.delete(user.read(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
