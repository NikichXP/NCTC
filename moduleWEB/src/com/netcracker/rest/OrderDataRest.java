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
public class OrderDataRest {

    @EJB
    OrderState orderState;
    @EJB
    Order order;
    @EJB
    User user;

    @POST
    @Path("history")
    @Consumes("text/plain")
    public Response getOrderHistoryData(String uuid) {
        List<OrderEntity> list = order.getOrdersByStateAndCustomerUuid(orderState.findByName("completed"), uuid);
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("refused"), uuid));
        StringBuilder sb = new StringBuilder();
        sb.append("{\"orderHistory\":[");
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
    @Path("customer_list")
    @Consumes("text/plain")
    public Response getOrderListData(String uuid) {
        List<OrderEntity> list = order.getOrdersByStateAndCustomerUuid(orderState.findByName("queued"), uuid);
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("assigned"), uuid));
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("in progress"), uuid));
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("updated"), uuid));
        StringBuilder sb = new StringBuilder();
        sb.append("{\"orderHistory\":[");
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

}
