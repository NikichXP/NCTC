package com.netcracker.classes;

/* 15:43 17.05.2015 by Viktor Taranenko */

import java.util.Arrays;

public class TariffJson {
	private String orderTypeRate;
	private String animalFriendly;
	private String wifi;
	private String airConditioner;
	private String smokingFriendly;
	private String male;
	private String female;

	private TimeRate[] hourlyRates;//[from time][to time]
	private TimeRate[] distanceRates;

	public String getOrderTypeRate() {
		return orderTypeRate;
	}

	public void setOrderTypeRate(String orderTypeRate) {
		this.orderTypeRate = orderTypeRate;
	}

	public String getAnimalFriendly() {
		return animalFriendly;
	}

	public void setAnimalFriendly(String animalFriendly) {
		this.animalFriendly = animalFriendly;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(String airConditioner) {
		this.airConditioner = airConditioner;
	}

	public String getSmokingFriendly() {
		return smokingFriendly;
	}

	public void setSmokingFriendly(String smokingFriendly) {
		this.smokingFriendly = smokingFriendly;
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getFemale() {
		return female;
	}

	public void setFemale(String female) {
		this.female = female;
	}

	public TimeRate[] getHourlyRates() {
		return hourlyRates;
	}

	public void setHourlyRates(TimeRate[] hourlyRates) {
		this.hourlyRates = hourlyRates;
	}

	public TimeRate[] getDistanceRates() {
		return distanceRates;
	}

	public void setDistanceRates(TimeRate[] distanceRates) {
		this.distanceRates = distanceRates;
	}

	@Override
	public String toString() {
		return "{\"orderTypeRate\":" + (orderTypeRate == null ? "null" : "\"" + orderTypeRate + "\"") + ", " +
				"\"animalFriendly\":" + (animalFriendly == null ? "null" : "\"" + animalFriendly + "\"") + ", " +
				"\"wifi\":" + (wifi == null ? "null" : "\"" + wifi + "\"") + ", " +
				"\"airConditioner\":" + (airConditioner == null ? "null" : "\"" + airConditioner + "\"") + ", " +
				"\"smokingFriendly\":" + (smokingFriendly == null ? "null" : "\"" + smokingFriendly + "\"") + ", " +
				"\"male\":" + (male == null ? "null" : "\"" + male + "\"") + ", " +
				"\"female\":" + (female == null ? "null" : "\"" + female + "\"") + ", " +
				"\"hourlyRates\":" + Arrays.toString(hourlyRates) + ", " +
				"\"distanceRates\":" + Arrays.toString(distanceRates) +
				"}";
	}
}
