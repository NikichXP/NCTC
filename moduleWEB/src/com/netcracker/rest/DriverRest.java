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
    @Consumes("text/plain")
    public Response getAssignedOrders(String uuid){
        List<OrderEntity> list = order.getOrdersByStateAndDriverUuid(orderState.findByName("assigned"), uuid);

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

    @GET
    @Path("order")
    public Response getOrder(@QueryParam("id") String id) {
        OrderEntity orderEntity = order.read(new BigInteger(id));
        OrderJson orderJson = new OrderJson();

        orderJson.setCustomerUserUuid(orderEntity.getCustomerUserEntity().getUuid());
        orderJson.setPublicToken(orderEntity.getPublicToken());
        orderJson.setContactName(orderEntity.getContactName());
        orderJson.setContactPhone(orderEntity.getContactPhone());
        orderJson.setRequestedSeatsCount(orderEntity.getRequestedSeatsCount().toString());


//        orderEntity.setOrderTypeEntity(orderType.findByName("basic"));
//        orderEntity.setOrderStateEntity(orderState.findByName("queued"));

        orderJson.setTotalMultiplier(orderEntity.getTotalMultiplier().toString());
        orderJson.setTimeCreated(orderEntity.getTimeCreated().toString());
        orderJson.setTimeRequested(orderEntity.getTimeRequested().toString());


//        List<PathEntity> pathEntities = new LinkedList<>();
//
//        PathEntity firstPathEntity = new PathEntity();
//
//        orderJson.setFromAddress(firstPathEntity.getStartAddress());
//        orderJson.setFromX(firstPathEntity.getStartX().toString());
//        orderJson.setFromY(firstPathEntity.getStartY().toString());


//        firstPathEntity.setEndAddress(orderJson.getToAddress()[0]);
//        firstPathEntity.setEndX(new BigDecimal(orderJson.getToX()[0]));
//        firstPathEntity.setEndY(new BigDecimal(orderJson.getToY()[0]));
//        firstPathEntity.setStartX(new BigDecimal(orderJson.getFromX()));
//        firstPathEntity.setStartY(new BigDecimal(orderJson.getFromY()));
//
//        firstPathEntity.setEndAddress(orderJson.getToAddress()[0]);
//        firstPathEntity.setEndX(new BigDecimal(orderJson.getToX()[0]));
//        firstPathEntity.setEndY(new BigDecimal(orderJson.getToY()[0]));
//
//        firstPathEntity.setLength(new BigDecimal(orderJson.getDistance()[0]));
//        firstPathEntity.setPrice(orderEntity.getTotalMultiplier().multiply(firstPathEntity.getLength()));
//        firstPathEntity.setOrderEntity(orderEntity);

//        pathEntities.add(firstPathEntity);
//
//        for (int i = 0; i < orderJson.getToAddress().length - 1; i++) {
//            PathEntity pathEntity = new PathEntity();
//            pathEntity.setStartAddress(orderJson.getToAddress()[i]);
//            pathEntity.setStartX(new BigDecimal(orderJson.getToX()[i]));
//            pathEntity.setStartY(new BigDecimal(orderJson.getToY()[i]));
//
//            pathEntity.setEndAddress(orderJson.getToAddress()[i + 1]);
//            pathEntity.setEndX(new BigDecimal(orderJson.getToX()[i + 1]));
//            pathEntity.setEndY(new BigDecimal(orderJson.getToY()[i + 1]));
//
//            pathEntity.setLength(new BigDecimal(orderJson.getDistance()[i + 1]));
//            pathEntity.setPrice(orderEntity.getTotalMultiplier().multiply(pathEntity.getLength()));
//            pathEntity.setOrderEntity(orderEntity);
//
//            pathEntities.add(pathEntity);
//
//            pathEntities.get(i).setNextPathEntity(pathEntity);
//        }
//
//        orderEntity.setPathEntities(pathEntities);
//        orderEntity.setDriverSex(orderJson.getSex());
//        orderEntity.setCarClassEntity(carClass.findByName(orderJson.getCarClass()));
//        orderEntity.setMusicTypeEntity(musicType.findByName(orderJson.getMusicType()));
//        orderEntity.setSmokingFriendly(orderJson.isSmokingFriendly());
//        orderEntity.setAnimalFriendly(orderJson.isAnimalFriendly());
//        orderEntity.setWifi(orderJson.isWifi());
//        orderEntity.setAirConditioner(orderJson.isAirConditioner());
//
//        orderEntity.setCustomerPreCreateComment(orderJson.getCustomerPreCreateComment());
//
//        BigDecimal totalPrice = new BigDecimal(0);
//        BigDecimal totalLength = new BigDecimal(orderJson.getTotalLength());
//
//        for (PathEntity pathEntity : pathEntities) {
//            pathEntity.setCompleted(false);
//            totalPrice = totalPrice.add(pathEntity.getPrice());
//            totalLength = totalLength.add(pathEntity.getPrice());
//        }
//        orderEntity.setFinalPrice(totalPrice);

        if (orderEntity != null) {
            //TODO replace: return value
            return Response.status(200).entity(id).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }
}