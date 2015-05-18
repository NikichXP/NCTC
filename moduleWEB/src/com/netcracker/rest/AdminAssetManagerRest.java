package com.netcracker.rest;

import com.netcracker.entity.CarEntity;
import com.netcracker.facade.local_int.Car;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

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
        List<CarEntity> list = car.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"cars\":[");
        for (CarEntity carEntity : list) {
            sb.append("{\"carId\":\"")
                    .append(carEntity.getId())
                    .append("\",\"model\":\"")
                    .append(carEntity.getModel())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (list != null) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }

    }

}
