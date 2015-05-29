package com.netcracker.rest;

import com.netcracker.entity.DriverCategoryEntity;
import com.netcracker.facade.local_int.DriverCategory;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("driverCategory")
public class DriverCategoryRest {

	@EJB
	DriverCategory driverCategory;

	@POST
	@Path("getCategory")
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
			return Response.status(404).entity("Bad response......").build();
		}
	}
}
