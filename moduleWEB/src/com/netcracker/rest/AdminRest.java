package com.netcracker.rest;

import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Евгений on 16.05.2015.
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
