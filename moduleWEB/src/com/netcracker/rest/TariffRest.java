package com.netcracker.rest;

import com.netcracker.classes.TariffJson;
import com.netcracker.classes.TimeRate;
import com.netcracker.entity.OrderTypeEntity;
import com.netcracker.entity.TariffEntity;
import com.netcracker.facade.local_int.OrderType;
import com.netcracker.facade.local_int.Tariff;

import javax.ejb.EJB;
import javax.ws.rs.*;
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
}
