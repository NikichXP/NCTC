package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.entity.*;
import com.netcracker.facade.local_int.Car;
import com.netcracker.facade.local_int.CarClass;
import com.netcracker.facade.local_int.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

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
    public Response createOrder(OrderJson orderJson){

//        CarEntity carEntity = new CarEntity(); //creating cars is Administrator's task
////        car_class is read only table, you can't do this:
////        CarClassEntity carClassEntity = new CarClassEntity();
//        PathEntity pathEntity = new PathEntity();
//        OrderEntity orderEntity = new OrderEntity();
////        carClassEntity.setName(orderJson.getCarType()); can't do this either, the only correct way is :
//        CarClassEntity carClassEntity = carClass.read(new BigInteger("1"));
////        car already has it's carClass, you cant do this:
////        carEntity.setCarClassEntity(carClassEntity);
//        pathEntity.setStartAddress(orderJson.getFrom());
//        pathEntity.setEndAddress(orderJson.getTo());
////        pathEntity.setStartX(new BigInteger("")); - mandatory
////        pathEntity.setStartY(new BigInteger("")); - mandatory
////        pathEntity.setEndX(new BigInteger("")); - mandatory
////        pathEntity.setEndY(new BigInteger("")); - mandatory
//
//        orderEntity.setDriverSex(orderJson.getGender());
//        orderEntity.setWifi(orderJson.isWifi());
//        orderEntity.setContactPhone(orderJson.getPhone());
//        orderEntity.setAnimalFriendly(orderJson.isPetFriendly());
//        orderEntity.setSmokingFriendly(orderJson.isSmoking());
//
//        //TODO Victor add to db cargo and conditioner!!! - DONE
////        orderEntity.setAirConditioner(true/false);
////        ñargo - is one of the order types, not a boolean field
//        pathEntity.setOrderId(orderEntity.getId());
//        order.create(orderEntity);
//        path.create(pathEntity);
//        car.create(carEntity); //creating cars is Administrator's task
//        carClass.create(carClassEntity);

        if (orderJson == null) {
            return Response.status(404).entity("OrderJson is null.").build();
        } else {
            return Response.status(201).entity(orderJson.toString()).build();
        }

    }
}
