package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
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
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public OrderJson createOrder(OrderJson orderJson){
        //vova ya sam dopishu))
        return orderJson;
    }
}
