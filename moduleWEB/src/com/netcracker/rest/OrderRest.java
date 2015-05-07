package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.entity.CarEntity;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.PathEntity;
import com.netcracker.facade.local_int.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by Juger on 05.05.2015.
 */

@Stateless
@Path("order")
public class OrderRest {

    @EJB
    Order order;

    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public OrderJson createOrder(OrderJson orderJson){
        OrderEntity orderEntity = new OrderEntity();
        CarEntity carEntity = new CarEntity();
        PathEntity pathEntity = new PathEntity();

        pathEntity.setStartAddress(orderJson.getFrom());
        pathEntity.setEndAddress(orderJson.getTo());

        orderEntity.setContactPhone(orderJson.getPhone());
        orderEntity.setDriverSex(orderJson.getGender());
        return orderJson;
    }
}
