package com.netcracker.rest;


import com.netcracker.classes.CarJson;
import com.netcracker.classes.UserJson;
import com.netcracker.entity.CarClassEntity;
import com.netcracker.entity.CarEntity;
import com.netcracker.entity.UserEntity;
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
import java.sql.Timestamp;
import java.util.Arrays;
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
    public Response cuUser(CarJson carJson) {
        CarEntity carrEntity = null;
        if (carJson.getId() == null) {
            carrEntity = createUserEntityByUserJson(carJson);
            car.create(carrEntity);
        } else {
            carrEntity = editUserEntityByJson(carJson);
            car.update(carrEntity);
        }
        if (carrEntity == null) {
            return Response.status(404).entity("Phone or email is already in use").build();
        } else {
            return Response.status(201).entity("car add").build();
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

    private CarEntity createUserEntityByUserJson(CarJson carJson) {
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

    private CarEntity editUserEntityByJson(CarJson carJson) {
        CarEntity carEntity = car.read(new BigInteger(carJson.getId()));
//        carEntity.setLicencePlate("ASDFGHJK");
//        car.update(carEntity);
        if (!car.isLicenceUsed(carJson.getLicencePlate()) || carEntity.getLicencePlate().equals(carJson.getLicencePlate())) {
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
        CarJson json = new CarJson();
        StringBuilder out = new StringBuilder("{\"carData\":[");
        json.setId(carEntity.getId().toString());
        json.setModel(carEntity.getModel());
        json.setLicencePlate(carEntity.getLicencePlate());
        json.setClassId(carEntity.getCarClassEntity().getName());
        json.setAirConditioner(carEntity.getAirCondition().toString());
        json.setRequiredDriverCategory(carEntity.getDriverCategoryEntity().getName());
        json.setSeatCount(carEntity.getSeatsCount().toString());
        json.setUserId(carEntity.getUserEntity().getId().toString());
        out.append(json.toString())
                .append("]}");
        if (carEntity != null) {
            return Response.status(200).entity(out.toString()).build();
        } else {
            return Response.status(404).entity("Bad respons....").build();
        }
//        StringBuilder sb = new StringBuilder();
//        sb.append("{\"carData\":[")
//                .append("{\"carId\":\"")
//                .append(carEntity.getId())
//                .append("\",\"model\":\"")
//                .append(carEntity.getModel())
//                .append("\",\"licencePlate\":\"")
//                .append(carEntity.getLicencePlate())
//                .append("\",\"classCar\":\"")
//                .append(carEntity.getCarClassEntity().getName())
//                .append("\",\"conditioner\":\"")
//                .append(carEntity.getAirCondition().toString())
//                .append("\",\"requiredDriverCategory\":\"")
//                .append(carEntity.getDriverCategoryEntity().getName())
//                .append("\",\"countSeat\":\"")
//                .append(carEntity.getSeatsCount())
//                .append("\",\"carDriverId\":\"")
//                .append(carEntity.getUserEntity().getId())
//                .append("\" },");
//        sb.replace(sb.length() - 1, sb.length(), "");
//        sb.append("]}");
//        if (carEntity != null) {
//            return Response.status(200).entity(sb.toString()).build();
//        } else {
//            return Response.status(404).entity("Bad response.").build();
//        }

    }



}
