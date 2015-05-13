package com.netcracker.rest;

import com.netcracker.classes.UserDashboardJson;
import com.netcracker.classes.UserJson;
import com.netcracker.entity.MusicTypeEntity;
import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.Order;
import com.netcracker.service.SessionHandler;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Juger on 12.05.2015.
 */

@Stateless
@Path("user_dash")
public class UserDashboardRest {

    @EJB
    Order order;

    @POST
    @Path("history")
    public Response getUser(UserJson userJson) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"orderHistory\":[");
        for (int i = 0; i < 10; i++) {
            sb.append("{\"startOrder\":\"")
                    .append("Kiev")
                    .append("\",\"endOrder\":\"")
                    .append("Odesa")
                    .append("\",\"price\":\"")
                    .append("20")
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        return Response.status(200).entity(sb.toString()).build();
    }
}
