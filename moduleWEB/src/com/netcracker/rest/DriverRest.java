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
        List<OrderEntity> list = new ArrayList<>(orderState.findByName("queued").getOrderEntities());
        Collections.sort(list, (o1, o2) -> o2.getTimeCreated().toString().compareTo(o1.getTimeCreated().toString()));
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
    @Path("historyByLength")
    @Consumes("text/plain")
    public Response getOrderHistoryByLength(String uuid) {
        List<OrderEntity> list = order.sortByLengthAndUUIDAndState(uuid,orderState.findByName("completed"));
        list.addAll(order.sortByLengthAndUUIDAndState(uuid, orderState.findByName("refused")));

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
    @POST
    @Path("historyByDate")
    @Consumes("text/plain")
    public Response getOrderHistoryByDate(String uuid) {
        List<OrderEntity> list = order.sortByDateAndUUIDAndState(uuid,orderState.findByName("completed"));
        list.addAll(order.sortByDateAndUUIDAndState(uuid, orderState.findByName("refused")));

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
    @POST
    @Path("historyByPrice")
    @Consumes("text/plain")
    public Response getOrderHistoryByPrice(String uuid) {
        List<OrderEntity> list = order.sortByPriceAndUUIDAndState(uuid, orderState.findByName("completed"));
        list.addAll(order.sortByPriceAndUUIDAndState(uuid, orderState.findByName("refused")));

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