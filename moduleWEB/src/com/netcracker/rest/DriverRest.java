package com.netcracker.rest;

import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.facade.local_int.Order;
import com.netcracker.facade.local_int.OrderState;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ubuntu on 19.05.2015.
 */
@Path("driver")
public class DriverRest {

    @EJB
    private Order order;

    @EJB
    private OrderState orderState;

    @POST
    @Path("getQueuedOrders")
    public Response getQueuedOrders(){

        Collection<OrderEntity> list = orderState.findByName("queued").getOrderEntities();

        StringBuilder sb = new StringBuilder();
        sb.append("{\"orders\":[");
        for (OrderEntity orderEntity : list) {
            List<Point> points = order.getFirstAndLastPoints(orderEntity);
            sb.append("{\"startOrder\":\"")
                    .append(points.get(0).toString())
                    .append("\",\"endOrder\":\"")
                    .append(points.get(1).toString())
                    .append("\",\"dateOrderCreate\":\"")
                    .append(orderEntity.getTimeCreated().toString())
                    .append("\",\"id\":\"")
                    .append(orderEntity.getId())
                    .append("\",\"statusOrder\":\"")
                    .append(orderEntity.getOrderStateEntity().getName())
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

    @POST
    @Path("getAssignedOrders")
    public Response getAssignedOrders(){

        Collection<OrderEntity> list = orderState.findByName("assigned").getOrderEntities();

        StringBuilder sb = new StringBuilder();
        sb.append("{\"orders\":[");
        for (OrderEntity orderEntity : list) {
            List<Point> points = order.getFirstAndLastPoints(orderEntity);
            sb.append("{\"startOrder\":\"")
                    .append(points.get(0).toString())
                    .append("\",\"endOrder\":\"")
                    .append(points.get(1).toString())
                    .append("\",\"dateOrderCreate\":\"")
                    .append(orderEntity.getTimeCreated().toString())
                    .append("\",\"id\":\"")
                    .append(orderEntity.getId())
                    .append("\",\"statusOrder\":\"")
                    .append(orderEntity.getOrderStateEntity().getName())
                    .append("\",\"price\":\"")
                    .append(orderEntity.getFinalPrice())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (!list.isEmpty()) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(204).entity("Bad response.").build();
        }
    }

}
