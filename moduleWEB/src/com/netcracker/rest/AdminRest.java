package com.netcracker.rest;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Someone on 16.05.2015.
 */

@Path("admin_table")
public class AdminRest {
    @EJB
    User user;


    @POST
    @Path("users_table")
    @Consumes("text/plain")
    public Response getUsersData() {
        List<UserEntity> list = user.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"users_tb\":[");
        for (UserEntity userEntity : list) {

            sb.append("{\"firstName\":\"")
                    .append(userEntity.getFirstName())
                    .append("\",\"lastName\":\"")
                    .append(userEntity.getLastName())
                    .append("\",\"phone\":\"")
                    .append(userEntity.getPhone())
                    .append("\",\"email\":\"")
                    .append(userEntity.getEmail())
                    .append("\",\"id_d\":\"")
                    .append(userEntity.getId())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (list != null) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }


    @POST
    @Path("users_form")
    @Consumes("text/plain")
    public Response getUsersDataForForm() {
        List<UserEntity> list = user.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"users_form\":[");
        for (UserEntity userEntity : list) {

            sb.append("{\"firstName\":\"")
                    .append(userEntity.getFirstName())
                    .append("\",\"lastName\":\"")
                    .append(userEntity.getLastName())
                    .append("\",\"id\":\"")
                    .append(userEntity.getId())
                    .append("\",\"phone\":\"")
                    .append(userEntity.getPhone())
                    .append("\",\"email\":\"")
                    .append(userEntity.getEmail())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (list != null) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }
}
