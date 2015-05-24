package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.PathEntity;
import com.netcracker.facade.local_int.Order;
import com.netcracker.facade.local_int.OrderState;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                    .append(points.get(0).getAddress())
                    .append("\",\"endOrder\":\"")
                    .append(points.get(1).getAddress())
                    .append("\",\"dateOrderCreate\":\"")
                    .append(orderEntity.getTimeCreated().toString())
                    .append("\",\"id\":\"")
                    .append(orderEntity.getId())
                    .append("\",\"distance\":\"")
                    .append(orderEntity.getTotalLength())
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
    @Consumes("text/plain")
    public Response getAssignedOrders(String uuid){
        List<OrderEntity> list = order.getOrdersByStateAndDriverUuid(orderState.findByName("assigned"), uuid);

        StringBuilder sb = new StringBuilder();
        sb.append("{\"orders\":[");
        for (OrderEntity orderEntity : list) {
            List<Point> points = order.getFirstAndLastPoints(orderEntity);
            sb.append("{\"startOrder\":\"")
                    .append(points.get(0).getAddress())
                    .append("\",\"endOrder\":\"")
                    .append(points.get(1).getAddress())
                    .append("\",\"dateOrderCreate\":\"")
                    .append(orderEntity.getTimeCreated().toString())
                    .append("\",\"id\":\"")
                    .append(orderEntity.getId())
                    .append("\",\"distance\":\"")
                    .append(orderEntity.getTotalLength())
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

    @POST
    @Path("history")
    @Consumes("text/plain")
    public Response getOrderHistory(String uuid) {
        List<OrderEntity> list = order.getOrdersByStateAndDriverUuid(orderState.findByName("completed"), uuid);
        list.addAll(order.getOrdersByStateAndDriverUuid(orderState.findByName("refused"), uuid));

        StringBuilder sb = new StringBuilder();
        sb.append("{\"orderHistory\":[");
        for (OrderEntity orderEntity : list) {
            List<Point> points = order.getFirstAndLastPoints(orderEntity);
            sb.append("{\"startOrder\":\"")
                    .append(points.get(0).getAddress())
                    .append("\",\"endOrder\":\"")
                    .append(points.get(1).getAddress())
                    .append("\",\"dateOrderCreate\":\"")
                    .append(orderEntity.getTimeCreated().toString())
                    .append("\",\"id\":\"")
                    .append(orderEntity.getId())
                    .append("\",\"distance\":\"")
                    .append(orderEntity.getTotalLength())
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