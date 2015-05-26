package com.netcracker.rest;


import com.netcracker.classes.CarJson;
import com.netcracker.classes.JSON;
import com.netcracker.classes.UserJson;
import com.netcracker.entity.CarEntity;
import com.netcracker.facade.local_int.Car;
import com.netcracker.facade.local_int.CarClass;
import com.netcracker.facade.local_int.DriverCategory;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.Date;


/**
 * Created by Juger on 19.05.2015.
 */

@Path("car_car")
public class CarRest {

    @EJB
    Car car;

    @EJB
    CarClass carClas;

    @EJB
    DriverCategory driverCategory;

    @EJB
    User user;

    @POST
    @Path("create_car")
    @Consumes("application/json")
    public Response cuCar(CarJson carJson) {
        CarEntity carrEntity = null;
        String stutus;
        if (carJson.getId() == null) {
            carrEntity = createCarEntityByCarJson(carJson);
            car.create(carrEntity);
            stutus = "car create";
        } else {
            carrEntity = editCarEntityByJson(carJson);
            car.update(carrEntity);
            stutus = "car edit";
        }
        if (carrEntity == null) {
            return Response.status(404).entity("licence is already in use").build();
        } else {
            return Response.status(201).entity(stutus).build();
        }
    }

    @POST
    @Path("delete")
    @Consumes("application/json")
    public Response deleteCarById(UserJson id) {
        car.delete(new BigInteger(id.getId()));
        if (car.read(new BigInteger(id.getId())) == null) {
            return Response.status(200).entity("car is delete").build();
        } else {
            return Response.status(404).entity("car is not delete").build();
        }
    }

    private CarEntity createCarEntityByCarJson(CarJson carJson) {
        CarEntity carEntity = null;
        if (!car.isLicenceUsed(carJson.getLicencePlate())) {
            carEntity = new CarEntity();
            carEntity.setModel(carJson.getModel());
            carEntity.setLicencePlate(carJson.getLicencePlate());
            carEntity.setCarClassEntity(carClas.findByName(carJson.getClassId()));
            carEntity.setDriverCategoryEntity(driverCategory.findByName(carJson.getRequiredDriverCategory()));
            carEntity.setSeatsCount(new BigInteger(carJson.getSeatCount()));
            carEntity.setDateManufactured(new java.sql.Date(new Date().getTime()));
            carEntity.setAirCondition(new Boolean(carJson.getAirConditioner()));
            carEntity.setUserEntity(user.read(new BigInteger(carJson.getUserId())));
        }
        return carEntity;
    }

    private CarEntity editCarEntityByJson(CarJson carJson) {
        CarEntity carEntity = car.read(new BigInteger(carJson.getId()));
        if(!car.isLicenceUsed(carJson.getLicencePlate()) || carEntity.getLicencePlate().equalsIgnoreCase(carJson.getLicencePlate())) {
            carEntity.setModel(carJson.getModel());
            carEntity.setLicencePlate(carJson.getLicencePlate());
            carEntity.setCarClassEntity(carClas.findByName(carJson.getClassId()));
            carEntity.setDriverCategoryEntity(driverCategory.findByName(carJson.getRequiredDriverCategory()));
            carEntity.setSeatsCount(new BigInteger(carJson.getSeatCount()));
            carEntity.setDateManufactured(new java.sql.Date(new Date().getTime()));
            carEntity.setAirCondition(new Boolean(carJson.getAirConditioner()));
            carEntity.setUserEntity(user.read(new BigInteger(carJson.getUserId())));
        }
        return carEntity;
    }

    @POST
    @Path("getCarDataById")
    @Consumes("text/plain")
    public Response getCarDataByID(String id) {
        CarEntity carEntity = car.read(new BigInteger(id));
//        CarJson json = new CarJson();
//        json.setId(carEntity.getId().toString());
//        json.setModel(carEntity.getModel());
//        json.setLicencePlate(carEntity.getLicencePlate());
//        json.setClassId(carEntity.getCarClassEntity().getName());
//        json.setAirConditioner(carEntity.getAirCondition().toString());
//        json.setRequiredDriverCategory(carEntity.getDriverCategoryEntity().getName());
//        json.setSeatCount(carEntity.getSeatsCount().toString());
//        json.setUserId(carEntity.getUserEntity().getId().toString());
        if (carEntity != null) {
            return Response.status(200).entity(new JSON().getUserJson(carEntity).toString()).build();
        } else {
            return Response.status(404).entity("Bad respons....").build();
        }

    }


}
