package com.netcracker.rest;

import com.netcracker.classes.UserJson;
import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;
import com.netcracker.rest.utils.SecuritySettings;
import com.netcracker.session.SessionHandler;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

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


	/**
	 * Auth method. Generates unique session token linked to userdata.
	 * @param login - phone or e-mail
	 * @param pass - password
	 * @return - token UUID
	 */
	@GET
	@Path("auth/{loginData}/{pass}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String generateAuth(@PathParam("loginData") String login, @PathParam("pass") String pass) {
		UserEntity ue = null;
		//Don't delete this method, it's for debugging (Nikita)
		if (login.matches("0[0-9]{9}")) {
			login = "+38" + login;
		}
		if (login.matches("\\+380[0-9]{9}")) {
			ue = user.loginByPhone(login, pass);
		} else if (login.matches("[a-zA-Z0-9]+@[a-z0-9]+.[a-z0-9]{2,}")) {
			ue = user.loginByEmail(login, pass);
		}
		return SessionHandler.generateSession(ue, pass); //our session token
	}

	/**
	 * Checks if session is valid
	 * @param sid - session id
	 * @return - true of false
	 */
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
		SessionHandler.generateSession(userEntity, userJson.getPass());
		//TODO: Add returning of session token id to response (Nikita)
		if (userEntity != null) {
			return Response.status(200).entity(userEntity.getUuid()).build();
		} else {
			return Response.status(404).entity("Bad login credentials").build();
		}
	}

	@POST
	@Path("nextAfterLogin")
	@Consumes("text/plain")
	public Response getNextU(String requestFromClient) {

		if (requestFromClient != null) {
			return Response.status(200).entity(requestFromClient).build();
		} else {
			return Response.status(404).entity("Bad response.").build();
		}
	}

	@POST
	@Path("nextAfterReg")
	@Consumes("text/plain")
	public Response getNextR(String requestFromClient) {

		if (requestFromClient != null) {
			return Response.status(200).entity(requestFromClient).build();
		} else {
			return Response.status(404).entity("Bad response.").build();
		}
	}



	/**
	 * Get user permission category
	 * @param sid - session UUID
	 * @return - level of permission
	 * @see com.netcracker.entity.UserUserAccessLevelEntity
	 */
	@GET
	@Path("validPermission/{sid}")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String validatePermissions(@PathParam("sid") String sid) {
		//TODO: Validation or checking of permission (Nikita)
		return null;
	}

	/**
	 * Creates a new user
	 * @param userJson - JSON mapping of user
	 * @return - Response
	 */
	@POST
	@Path("create")
	@Consumes("application/json")
	public Response createUser(UserJson userJson) {
		UserEntity userEntity = null;
		String randomUuid = UUID.randomUUID().toString();
		if (!user.isEmailUsed(userJson.getEmail()) && !user.isPhoneUsed(userJson.getPhone())) {
			userEntity = new UserEntity();
			userEntity.setFirstName(userJson.getFirstName());
			userEntity.setLastName(userJson.getLastName());
			userEntity.setPassword(userJson.getPass());
			userEntity.setPhone(userJson.getPhone());
			userEntity.setEmail(userJson.getEmail());
			userEntity.setDateRegistered(new Timestamp(new Date().getTime()));
			userEntity.setUuid(randomUuid);
			user.create(userEntity);
//			Send email with text "confirm"+SecuritySettings.encrypt(randomUuid);
			//TODO Send confirmation email here (Viktor)
		}
		if (userEntity == null) {
			return Response.status(404).entity("Phone or email is already in use").build();
		} else {
			return Response.status(201).entity(randomUuid).build();
		}
	}

	@GET
	@Path("confirm")
	public Response confirm(@QueryParam("encryptedUuid") String encryptedUuid) {
		UserEntity userEntity = user.findByUuid(SecuritySettings.decrypt(encryptedUuid));
		userEntity.setConfirmed(true);
		user.update(userEntity);
		if (userEntity == null) {
			return Response.status(404).entity("Wrong user uuid passed").build();
		}
		return Response.status(201).entity("Email confirmed").build();
	}


}
