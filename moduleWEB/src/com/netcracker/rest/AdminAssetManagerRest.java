package com.netcracker.rest;

import com.netcracker.entity.CarEntity;
import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.Car;
import com.netcracker.facade.local_int.DriverCategory;
import com.netcracker.facade.local_int.User;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

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
		sb.append("{\"dataEntity\":[");
		for (CarEntity carEntity : list) {
			sb.append("{\"id\":\"")
					.append(carEntity.getId())
					.append("\",\"name\":\"")
					.append(carEntity.getModel())
					.append("\" },");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]}");
		if (list != null) {
			return Response.status(200).entity(sb.toString()).build();
		} else {
			return Response.status(404).entity("Bad response.......").build();
		}

	}


	@POST
	@Path("drivers")
	public Response getDrivers() {
		List<UserEntity> list = user.getDrivers();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"dataEntity\":[");
		for (UserEntity userEntity : list) {
			sb.append("{\"id\":\"")
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
			return Response.status(404).entity("Bad response......").build();
		}

	}


}
