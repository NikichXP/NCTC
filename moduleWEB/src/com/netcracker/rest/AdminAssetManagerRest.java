package com.netcracker.rest;

import com.netcracker.facade.local_int.Car;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Juger on 18.05.2015.
 */

@Path("admin_asset_manager")
public class AdminAssetManagerRest {

    @EJB
    Car car;

    @POST
    @Path("cars")
    public Response getOrderHistoryData() {

        return Response.status(200).entity("Bad response.").build();

    }

}
