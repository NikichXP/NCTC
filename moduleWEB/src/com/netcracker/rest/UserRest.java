package com.netcracker.rest;

import com.netcracker.classes.UserJson;
import com.netcracker.entity.UserUserAccessLevelEntity;
import com.netcracker.entity.UserAccessLevelEntity;
import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;
import com.netcracker.facade.local_int.UserAccessLevel;
import com.netcracker.rest.utils.SecuritySettings;
import com.netcracker.service.Mail;
import com.netcracker.service.SessionHandler;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * User facade for ReST
 *
 * @author NikichXP
 */

@Path("user")
public class UserRest {

    @EJB
    User user;
    @EJB
    UserAccessLevel userAccessLevel;


    /**
     * Auth method. Generates unique service token linked to userdata.
     *
     * @param login - phone or e-mail
     * @param pass  - password
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
        return SessionHandler.generateSession(ue, pass); //our service token
    }

    /**
     * Checks if service is valid
     *
     * @param sid - service id
     * @return - true of false
     */
    @GET
    @Path("checkAuth/{sid}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String checkAuth(@PathParam("sid") String sid) {
        return Boolean.toString(SessionHandler.isValidSession(sid));
    }

    @GET
    @Path("sendMail/{email}/{theme}/{text}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String sendMail(@PathParam("email") String email, @PathParam("theme") String theme, @PathParam("text") String text) {
        return Mail.testSend(email, theme, text);
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    public Response getUser(UserJson userJson) {
        UserEntity userEntity = null;
        if (userJson.getCred().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
            userEntity = user.loginByEmail(userJson.getCred(), userJson.getPass());
        } else if (userJson.getCred().matches("\\d+")) {
            userEntity = user.loginByPhone(userJson.getCred().replace("+", "").replace(" ", ""), userJson.getPass());
        }
        SessionHandler.generateSession(userEntity, userJson.getPass());
        //TODO: Add returning of service token id to response (Nikita)
        if (userEntity != null) {
            return Response.status(200).entity(userEntity.getUuid()).build();
        } else {
            return Response.status(404).entity("Bad login credentials").build();
        }
    }

    @POST
    @Path("getAccessLevelsByUuid")
    @Consumes("text/plain")
    public Response getNextU(String uuid) {
        System.out.println(uuid);
        UserEntity userEntity = user.findByUuid(uuid);
        Collection<UserAccessLevelEntity> userAccessLevels = userEntity.getUserAccessLevelEntities();

        StringBuilder sb = new StringBuilder();
        sb.append("{\"userAccessLevel\":[");
        for (UserAccessLevelEntity userAccessLevel : userAccessLevels) {
            sb.append("{\"id\":\"")
                    .append(userAccessLevel.getId())
                    .append("\",\"level\":\"")
                    .append(userAccessLevel.getName())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");

        if (!userAccessLevels.isEmpty()) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }


    /**
     * Get user permission category
     *
     * @param sid - service UUID
     * @return - level of permission
     * @see UserUserAccessLevelEntity
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
     *
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
            userEntity.setPhone(userJson.getPhone().replace("+", "").replace(" ", ""));
            userEntity.setEmail(userJson.getEmail());
            userEntity.setDateRegistered(new Timestamp(new Date().getTime()));
            userEntity.setUuid(randomUuid);
            userEntity.setUserAccessLevelEntities(Arrays.asList(userAccessLevel.read(new BigInteger("2"))));
            user.create(userEntity);
            Mail.testSend(userJson.getEmail(), "Taxi Service confirmation",
                    "http://localhost:8080/moduleWEB_war_archive/api/user/confirm?encryptedUuid="
                            + SecuritySettings.encrypt(randomUuid));
            //TODO Replace with real URL
        }
        if (userEntity == null) {
            return Response.status(404).entity("Phone or email is already in use").build();
        } else {
            return Response.status(201).entity(randomUuid).build();
        }
    }

    @POST
    @Path("create_driver")
    @Consumes("application/json")
    public Response cuUser(UserJson userJson) {
        UserEntity userEntity = null;
        if (userJson.getId() == null) {
            userEntity = createUserEntityByUserJson(userJson);
            user.create(userEntity);
        } else {
            userEntity = editUserEntityByJson(userJson);
            user.update(userEntity);
        }
        if (userEntity == null) {
            return Response.status(404).entity("Phone or email is already in use").build();
        } else {
            return Response.status(201).entity("user add").build();
        }
    }

    @POST
    @Path("delete")
    @Consumes("application/json")
    public Response deleteUserById(UserJson id) {
        UserEntity userEntity = user.read(new BigInteger(id.getId()));
        user.delete(userEntity);
        if (userEntity == null) {
            return Response.status(200).entity("Driver is delete").build();
        } else {
            return Response.status(404).entity("driver is not delete").build();
        }
    }

    private UserEntity  createUserEntityByUserJson(UserJson userJson){
        UserEntity userEntity = null;
        String randomUuid = UUID.randomUUID().toString();
        if (!user.isEmailUsed(userJson.getEmail()) && !user.isPhoneUsed(userJson.getPhone())) {
            userEntity = new UserEntity();
            userEntity.setFirstName(userJson.getFirstName());
            userEntity.setLastName(userJson.getLastName());
            userEntity.setPassword(userJson.getPass());
            userEntity.setPhone(userJson.getPhone().replace("+", "").replace(" ", ""));
            userEntity.setEmail(userJson.getEmail());
            userEntity.setDateRegistered(new Timestamp(new Date().getTime()));
            userEntity.setUuid(randomUuid);
            userEntity.setUserAccessLevelEntities(Arrays.asList(userAccessLevel.read(new BigInteger("3"))));
        }
        return userEntity;
    }

    private UserEntity editUserEntityByJson(UserJson userJson){
        UserEntity userEntity = user.read(new BigInteger(userJson.getId()));
        userEntity.setPhone("380000000000");
        userEntity.setEmail("a@mial.com");
        user.update(userEntity);
        String randomUuid = UUID.randomUUID().toString();
        if (!user.isEmailUsed(userJson.getEmail()) && !user.isPhoneUsed(userJson.getPhone())) {
            userEntity.setFirstName(userJson.getFirstName());
            userEntity.setLastName(userJson.getLastName());
            userEntity.setPassword(userJson.getPass());
            userEntity.setPhone(userJson.getPhone().replace("+", "").replace(" ", ""));
            userEntity.setEmail(userJson.getEmail());
            userEntity.setDateRegistered(new Timestamp(new Date().getTime()));
            userEntity.setUuid(randomUuid);
            userEntity.setUserAccessLevelEntities(Arrays.asList(userAccessLevel.read(new BigInteger("3"))));
        }
        return userEntity;
    }

    @GET
    @Path("confirm")
    public Response confirm(@QueryParam("encryptedUuid") String encryptedUuid) {
        UserEntity userEntity = user.findByUuid(SecuritySettings.decrypt(encryptedUuid));
        if (userEntity == null) {
            return Response.status(404).entity("Wrong user uuid passed").build();
        }
        userEntity.setConfirmed(true);
        user.update(userEntity);
        return Response.status(201).entity("" +
                "<script>" +
                "alert('Email confirmed');" +
                "document.location.href = \"http://localhost:8080/moduleWEB_war_archive/customer.jsp\";" +
                "</script>").build(); //TODO change to real url
    }

    @POST
    @Path("getUserDataByUuid")
    @Consumes("text/plain")
    public Response getUserDataByUUID(String uuid) {
        UserEntity userEntity = user.findByUuid(uuid);

        StringBuilder sb = new StringBuilder();
        sb.append("{\"userData\":[");

        sb.append("{\"name\":\"")
                .append(userEntity.getFirstName())
                .append("\",\"phone\":\"")
                .append(userEntity.getPhone())
                .append("\",\"myMail\":\"")
                .append(userEntity.getEmail())
                .append("\" },");
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (userEntity != null) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }


    @POST
    @Path("getUserDataById")
    @Consumes("text/plain")
    public Response getUserDataByID(String id) {
        UserEntity userEntity = user.read(new BigInteger(id));

        StringBuilder sb = new StringBuilder();
        sb.append("{\"userData\":[")
                .append("{\"firstName\":\"")
                .append(userEntity.getFirstName())
                .append("\",\"lastName\":\"")
                .append(userEntity.getLastName())
                .append("\",\"phone\":\"")
                .append(userEntity.getPhone())
                .append("\",\"userId\":\"")
                .append(userEntity.getId())
                .append("\",\"email\":\"")
                .append(userEntity.getEmail())
                .append("\",\"getPassword\":\"")
                .append(userEntity.getPassword())
                .append("\" },");
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (userEntity != null) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }


}
