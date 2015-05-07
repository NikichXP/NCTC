package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.entity.*;
import com.netcracker.facade.local_int.Car;
import com.netcracker.facade.local_int.CarClass;
import com.netcracker.facade.local_int.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by Juger on 05.05.2015.
 */

@Stateless
@Path("order")
public class OrderRest {

    @EJB
    Order order;
    @EJB
    com.netcracker.facade.local_int.Path path;
    @EJB
    Car car;
    @EJB
    CarClass carClass;


    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public OrderJson createOrder(OrderJson orderJson){

        CarEntity carEntity = new CarEntity();
        CarClassEntity carClassEntity = new CarClassEntity();
        PathEntity pathEntity = new PathEntity();
        OrderEntity orderEntity = new OrderEntity();
        carClassEntity.setName(orderJson.getCarType());
        carEntity.setCarClassByClassId(carClassEntity);
        pathEntity.setStartAddress(orderJson.getFrom());
        pathEntity.setEndAddress(orderJson.getTo());

        orderEntity.setDriverSex(orderJson.getGender());
        orderEntity.setWifi(orderJson.isWifi());
        orderEntity.setContactPhone(orderJson.getPhone());
        orderEntity.setAnimalFriendly(orderJson.isPetFriendly());
        orderEntity.setSmokingFriendly(orderJson.isSmoking());

        //TODO Victor add to db cargo and conditioner!!!
        pathEntity.setOrderId(orderEntity.getId());
        order.create(orderEntity);
        path.create(pathEntity);
        car.create(carEntity);
        carClass.create(carClassEntity);
        return orderJson;
    }
}
