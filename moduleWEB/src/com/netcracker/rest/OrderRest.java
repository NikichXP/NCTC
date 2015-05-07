package com.netcracker.rest;

import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.UserEntity;
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

    @GET
    @Path ("loginByEmail/{id}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String getOrderIdByEmail (@PathParam("id")  String id) {
        return order.read(id).toString();
    }

    @POST
    @Path("/create/{phone}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String createOrder(@PathParam("phone")    String phone,
                              @PathParam("lastName") String lastName,
                              @PathParam("password") String pass){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setContactPhone(phone);
        //vova ya sam dopishu))
        return orderEntity.toString();
    }
}
