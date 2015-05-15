package com.netcracker.rest;

import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.OrderStateEntity;
import com.netcracker.facade.local_int.Order;
import com.netcracker.facade.local_int.OrderState;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Juger on 12.05.2015.
 */

@Path("user_dash")
public class UserDashboardRest {

    @EJB
    OrderState orderState;
    @EJB
    Order order;
    @EJB
    User user;

    @POST
    @Path("history")
    @Consumes("text/plain")
    public Response getUserDashboardData(String uuid) {
        OrderStateEntity orderStateEntity = orderState.findByName("queued");
        List<OrderEntity> list = order.getOrdersByStateAndCustomerUuid(orderStateEntity, uuid);
        StringBuilder sb = new StringBuilder();
        sb.append("{\"orderHistory\":[");
        for (OrderEntity orderEntity : list) {
            List<Point> points = order.getFirstAndLastPoints(orderEntity);
            sb.append("{\"startOrder\":\"")
                    .append(points.get(0).toString())
                    .append("\",\"endOrder\":\"")
                    .append(points.get(1).toString())
                    .append("\",\"status\":\"")
                    .append("queued")
                    .append("\",\"price\":\"")
                    .append(orderEntity.getFinalPrice())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (!list.isEmpty()) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }
}
