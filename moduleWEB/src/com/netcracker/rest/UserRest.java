package com.netcracker.rest;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.math.BigInteger;
import java.util.List;

/**
 * User facade for ReST 
 * @author NikichXP
 */
@Stateless
@Path("user")
public class UserRest {
	@EJB
	User uef;

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
		String ret = "";
		for (UserEntity anUser : user) {
			ret += anUser.toString() + "\n";
		}
		return ret + name + " | " + pass; //should be changed later

	}

	@GET
	@Path ("login/{id}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String getUserByID (@PathParam("id") BigInteger id) throws Exception{
		return uef.read(id).toString();
	}

	@POST
	@Path("/create/{firstName}/{lastName}/{password}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String createUser(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName, @PathParam("password") String pass){
		UserEntity ue = new UserEntity();
		ue.setFirstName(firstName);
		ue.setLastName(lastName);
		ue.setPassword(pass);
		return ue.toString();
	}

	@PUT
	@Path("/update/{id}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String updateByIdUser(@PathParam("id") BigInteger id){
		UserEntity ue = uef.read(id);
		//some to do
		return ue.toString();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void deleteByIdUser(@PathParam("id") BigInteger id){
		try {
			uef.delete(uef.read(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
