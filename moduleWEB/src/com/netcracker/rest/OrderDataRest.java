package com.netcracker.rest;

import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.facade.local_int.Order;
import com.netcracker.facade.local_int.OrderState;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;
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
    public Response getOrderHistoryDataByDate(String uuid) {
        List<OrderEntity> list = order.getOrdersByStateAndCustomerUuid(orderState.findByName("completed"), uuid);
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("refused"), uuid));
        Collections.sort(list, (o1, o2) -> o2.getTimeCreated().toString().compareTo(o1.getTimeCreated().toString()));
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
    public Response getOrderHistoryDataByPrice(String uuid) {
        List<OrderEntity> list = order.getOrdersByStateAndCustomerUuid(orderState.findByName("completed"), uuid);
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("refused"), uuid));
        Collections.sort(list, (o1, o2) -> o2.getFinalPrice().compareTo(o1.getFinalPrice()));
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
    @Path("historyByLength")
    @Consumes("text/plain")
    public Response getOrderHistoryDataByLength(String uuid) {
        List<OrderEntity> list = order.getOrdersByStateAndCustomerUuid(orderState.findByName("completed"), uuid);
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("refused"), uuid));
        Collections.sort(list, (o1, o2) -> o2.getTotalLength().compareTo(o1.getTotalLength()));
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
    @Path("customer_list")
    @Consumes("text/plain")
    public Response getOrderListData(String uuid) {
        List<OrderEntity> list = order.getOrdersByStateAndCustomerUuid(orderState.findByName("queued"), uuid);
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("assigned"), uuid));
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("in progress"), uuid));
        list.addAll(order.getOrdersByStateAndCustomerUuid(orderState.findByName("updated"), uuid));
        Collections.sort(list, (o1, o2) -> o2.getTimeCreated().toString().compareTo(o1.getTimeCreated().toString()));
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