package com.netcracker.rest;

import com.netcracker.entity.*;
import com.netcracker.facade.local_int.Car;
import com.netcracker.facade.local_int.DriverCategory;
import com.netcracker.facade.local_int.MusicType;
import com.netcracker.facade.local_int.User;

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

    @EJB
    User user;

    @EJB
    DriverCategory driverCategory;

    @POST
    @Path("cars")
    public Response getCars() {
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


    @POST
    @Path("drivers")
    public Response getDrivers() {
        List<UserEntity> list = user.getDrivers();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"cars\":[");
        for (UserEntity userEntity : list) {
            sb.append("{\"driverId\":\"")
                    .append(userEntity.getId())
                    .append("\",\"name\":\"")
                    .append(userEntity.getFirstName())
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

    @POST
    @Path("driver_category")
    public Response getCarClass() {
        List<DriverCategoryEntity> driverCategoryEntities = driverCategory.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"driverCategoryClass\":[");
        for (DriverCategoryEntity categoryEntity : driverCategoryEntities) {
            sb.append("{\"id\":\"")
                    .append(categoryEntity.getId())
                    .append("\",\"name\":\"")
                    .append(categoryEntity.getName())
                    .append("\",\"tariff_multiplier\":\"")
                    .append(categoryEntity.getTariffMultiplier())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (!driverCategoryEntities.isEmpty()) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }
        
    

}
