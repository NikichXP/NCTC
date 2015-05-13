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



        if (orderJson == null) {
            return Response.status(404).entity("OrderJson is null.").build();
        } else {
            return Response.status(201).entity(orderJson.toString()).build();
        }

    }
}
