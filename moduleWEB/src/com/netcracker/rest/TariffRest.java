package com.netcracker.rest;

import com.netcracker.classes.TariffJson;
import com.netcracker.classes.TimeRate;
import com.netcracker.entity.OrderTypeEntity;
import com.netcracker.entity.TariffEntity;
import com.netcracker.facade.local_int.OrderType;
import com.netcracker.facade.local_int.Tariff;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/* 15:37 17.05.2015 by Viktor Taranenko */
@Path("tariff")
public class TariffRest {
	@EJB
	private OrderType orderType;
	@EJB
	private Tariff tariff;

	@POST
	@Path("getAll")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String getAllTariffs(String orderTypeName) {
		List<TariffEntity> allTariffs = tariff.findAll();
		OrderTypeEntity orderTypeEntity = orderType.findByName(orderTypeName);

		TariffJson tariffJson = new TariffJson();
		tariffJson.setOrderTypeRate(orderTypeEntity.getTariffMultiplier().toString());

		List<TimeRate> hourlyRates = new LinkedList<>();
		TimeRate[] hourlyRatesArr = new TimeRate[1];
		List<TimeRate> distanceRates = new LinkedList<>();
		TimeRate[] distanceRatesArr = new TimeRate[1];

		for (TariffEntity tariffEntity : allTariffs) {
			if (tariffEntity.getWifi() != null) tariffJson.setWifi(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.getAnimalFriendly()  != null)
				tariffJson.setAnimalFriendly(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.getWifi() != null)
				tariffJson.setWifi(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.getAirConditioner() != null)
				tariffJson.setAirConditioner(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.getSmokingFriendly() != null)
				tariffJson.setSmokingFriendly(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.getSex() != null && tariffEntity.getSex().equalsIgnoreCase("male"))
				tariffJson.setMale(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.getSex() != null && tariffEntity.getSex().equalsIgnoreCase("female"))
				tariffJson.setFemale(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.getSex() != null && tariffEntity.getSex().equalsIgnoreCase("any"))
				tariffJson.setAny(tariffEntity.getMultiplier().toString());
			else if (tariffEntity.isPerHour() != null) {
				hourlyRates.add(new TimeRate(tariffEntity.getTimeStarts(),
						tariffEntity.getTimeEnds(), tariffEntity.getMultiplier().toString()));
			} else if (tariffEntity.isPerKilometer() != null) {
				distanceRates.add(new TimeRate(tariffEntity.getTimeStarts(),
						tariffEntity.getTimeEnds(), tariffEntity.getMultiplier().toString()));
			}
		}
		if (!hourlyRates.isEmpty()) {
			tariffJson.setHourlyRates(hourlyRates.toArray(hourlyRatesArr));
		}
		if (!distanceRates.isEmpty()) {
			tariffJson.setDistanceRates(distanceRates.toArray(distanceRatesArr));
		}
		return tariffJson.toString();
	}



	@POST
	@Path("getOrderTypeMultipliers")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response getOrderTypeMultipliers() {
		OrderTypeEntity orderTypeEntity1 = orderType.findByName("basic");
		OrderTypeEntity orderTypeEntity2 = orderType.findByName("cargo");
		OrderTypeEntity orderTypeEntity3 = orderType.findByName("sober driver");
		OrderTypeEntity orderTypeEntity4 = orderType.findByName("guest delivery");
		OrderTypeEntity orderTypeEntity5 = orderType.findByName("meet my guest");
		OrderTypeEntity orderTypeEntity6 = orderType.findByName("celebration taxi");



		StringBuilder sb = new StringBuilder();
		sb.append("{\"OrderTypeMultipliers\":[");

		sb.append("{\"basic\":\"")
				.append(orderTypeEntity1.getTariffMultiplier())
				.append("\",\"cargo\":\"")
				.append(orderTypeEntity2.getTariffMultiplier())
				.append("\",\"sober_driver\":\"")
				.append(orderTypeEntity3.getTariffMultiplier())
				.append("\",\"guest_delivery\":\"")
				.append(orderTypeEntity4.getTariffMultiplier())
				.append("\",\"meet_my_guest\":\"")
				.append(orderTypeEntity5.getTariffMultiplier())
				.append("\",\"celebration_taxi\":\"")
				.append(orderTypeEntity6.getTariffMultiplier())
				.append("\" },");
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]}");
		if (orderTypeEntity1 != null && orderTypeEntity2 != null && orderTypeEntity3 != null && orderTypeEntity4 != null && orderTypeEntity5 != null && orderTypeEntity6 != null) {
			return Response.status(200).entity(sb.toString()).build();
		} else {
			return Response.status(404).entity("Bad response.").build();
		}
	}
	@POST
	@Path("basicButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setOrderTypeBasic(String mul) {
		OrderTypeEntity orderTypeEntity = orderType.findByName("basic");
		orderTypeEntity.setTariffMultiplier(new BigDecimal(mul));
		orderType.update(orderTypeEntity);

	}
	@POST
	@Path("cargoButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setOrderTypeCargo(String mul) {
		OrderTypeEntity orderTypeEntity = orderType.findByName("cargo");
		orderTypeEntity.setTariffMultiplier(new BigDecimal(mul));
		orderType.update(orderTypeEntity);
	}
	@POST
	@Path("soberButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setOrderTypeSober(String mul) {
		OrderTypeEntity orderTypeEntity = orderType.findByName("sober driver");
		orderTypeEntity.setTariffMultiplier(new BigDecimal(mul));
		orderType.update(orderTypeEntity);
	}
	@POST
	@Path("guestButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setOrderTypeGuestDel(String mul) {
		OrderTypeEntity orderTypeEntity = orderType.findByName("guest delivery");
		orderTypeEntity.setTariffMultiplier(new BigDecimal(mul));
		orderType.update(orderTypeEntity);
	}
	@POST
	@Path("meetButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setOrderTypeMeetMyGuest(String mul) {
		OrderTypeEntity orderTypeEntity = orderType.findByName("meet my guest");
		orderTypeEntity.setTariffMultiplier(new BigDecimal(mul));
		orderType.update(orderTypeEntity);
	}
	@POST
	@Path("celebrationButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setOrderTypeCelebTaxi(String mul) {
		OrderTypeEntity orderTypeEntity = orderType.findByName("celebration taxi");
		orderTypeEntity.setTariffMultiplier(new BigDecimal(mul));
		orderType.update(orderTypeEntity);
	}

	@POST
	@Path("dayHourlyRateButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setDayHourlyRate(String mul) {
		 tariff.setDayHourlyMultiplier(new BigDecimal(mul));
	}

	@POST
	@Path("nightHourlyRateButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setNightHourlyRate(String mul) {
		tariff.setNightHourlyMultiplier(new BigDecimal(mul));
	}

	@POST
	@Path("dayDistanceRateButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setDayDistanceRate(String mul) {
		tariff.setDayDistanceMultiplier(new BigDecimal(mul));
	}

	@POST
	@Path("nightDistanceRateButton")
	@Consumes("text/plain")
	@Produces("text/plain")
	public void setNightDistanceRate(String mul) {
		tariff.setNightDistanceMultiplier(new BigDecimal(mul));
	}


}
