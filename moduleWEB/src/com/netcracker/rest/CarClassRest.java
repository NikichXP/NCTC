package com.netcracker.rest;

import com.netcracker.entity.CarClassEntity;
import com.netcracker.entity.MusicTypeEntity;
import com.netcracker.facade.local_int.CarClass;
import com.netcracker.facade.local_int.MusicType;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Ubuntu on 17.05.2015.
 */

@Path("car")
public class CarClassRest {
    @EJB
    CarClass carClass;

    @POST
    @Path("class")
    public Response getCarClass() {
        List<CarClassEntity> musicTypeEntities = carClass.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"carClass\":[");
        for (CarClassEntity carClassEntity : musicTypeEntities) {
            sb.append("{\"id\":\"")
                    .append(carClassEntity.getId())
                    .append("\",\"name\":\"")
                    .append(carClassEntity.getName())
                    .append("\",\"tariff_multiplier\":\"")
                    .append(carClassEntity.getTariffMultiplier())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (!musicTypeEntities.isEmpty()) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }
}
