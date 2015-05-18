package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "tariff", schema = "public", catalog = "postgres")
public class TariffEntity {
	@SequenceGenerator(
			name = "tariff_SEQUENCE_GENERATOR",
			sequenceName = "tariff_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tariff_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 3)
	private BigInteger id;

	@Column(name = "per_hour", nullable = true, insertable = true, updatable = true, precision = 3)
	private Boolean perHour;

	@Column(name = "per_kilometer", nullable = true, insertable = true, updatable = true, precision = 3)
	private Boolean perKilometer;

	@Column(name = "time_starts", nullable = true, insertable = true, updatable = true)
	private String timeStarts;

	@Column(name = "time_ends", nullable = true, insertable = true, updatable = true)
	private String timeEnds;

	@Column(name = "animal_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean animalFriendly;

	@Column(name = "air_conditioner", nullable = true, insertable = true, updatable = true)
	private Boolean airConditioner;

	@Column(name = "wifi", nullable = true, insertable = true, updatable = true)
	private Boolean wifi;

	@Column(name = "smoking_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean smokingFriendly;

	@Column(name = "sex", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String sex;

	@Column(name = "from_seats_count", nullable = true, insertable = true, updatable = true, precision = 0)
	private BigInteger fromSeatsCount;

	@Column(name = "to_seats_count", nullable = true, insertable = true, updatable = true, precision = 0)
	private BigInteger toSeatsCount;

	@Column(name = "multiplier", nullable = false, insertable = true, updatable = true, precision = 3)
	private BigDecimal multiplier;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Boolean isPerHour() {
		return perHour;
	}

	public void setPerHour(Boolean perHour) {
		this.perHour = perHour;
	}

	public Boolean isPerKilometer() {
		return perKilometer;
	}

	public void setPerKilometer(Boolean perKilometer) {
		this.perKilometer = perKilometer;
	}

	public String getTimeStarts() {
		return timeStarts;
	}

	public void setTimeStarts(String timeStarts) {
		this.timeStarts = timeStarts;
	}

	public String getTimeEnds() {
		return timeEnds;
	}

	public void setTimeEnds(String timeEnds) {
		this.timeEnds = timeEnds;
	}

	public Boolean getAnimalFriendly() {
		return animalFriendly;
	}

	public Boolean getPerHour() {
		return perHour;
	}

	public Boolean getPerKilometer() {
		return perKilometer;
	}

	public Boolean getAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(Boolean airConditioner) {
		this.airConditioner = airConditioner;
	}

	public void setAnimalFriendly(Boolean animalFriendly) {
		this.animalFriendly = animalFriendly;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Boolean getSmokingFriendly() {
		return smokingFriendly;
	}

	public void setSmokingFriendly(Boolean smokingFriendly) {
		this.smokingFriendly = smokingFriendly;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BigInteger getFromSeatsCount() {
		return fromSeatsCount;
	}

	public void setFromSeatsCount(BigInteger fromSeatsCount) {
		this.fromSeatsCount = fromSeatsCount;
	}

	public BigInteger getToSeatsCount() {
		return toSeatsCount;
	}

	public void setToSeatsCount(BigInteger toSeatsCount) {
		this.toSeatsCount = toSeatsCount;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TariffEntity that = (TariffEntity) o;

		if (perHour != that.perHour) return false;
		if (perKilometer != that.perKilometer) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (timeStarts != null ? !timeStarts.equals(that.timeStarts) : that.timeStarts != null) return false;
		if (timeEnds != null ? !timeEnds.equals(that.timeEnds) : that.timeEnds != null) return false;
		if (animalFriendly != null ? !animalFriendly.equals(that.animalFriendly) : that.animalFriendly != null)
			return false;
		if (wifi != null ? !wifi.equals(that.wifi) : that.wifi != null) return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
		if (fromSeatsCount != null ? !fromSeatsCount.equals(that.fromSeatsCount) : that.fromSeatsCount != null)
			return false;
		if (toSeatsCount != null ? !toSeatsCount.equals(that.toSeatsCount) : that.toSeatsCount != null) return false;
		return !(multiplier != null ? !multiplier.equals(that.multiplier) : that.multiplier != null);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (perHour ? 1 : 0);
		result = 31 * result + (perKilometer ? 1 : 0);
		result = 31 * result + (timeStarts != null ? timeStarts.hashCode() : 0);
		result = 31 * result + (timeEnds != null ? timeEnds.hashCode() : 0);
		result = 31 * result + (animalFriendly != null ? animalFriendly.hashCode() : 0);
		result = 31 * result + (wifi != null ? wifi.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (fromSeatsCount != null ? fromSeatsCount.hashCode() : 0);
		result = 31 * result + (toSeatsCount != null ? toSeatsCount.hashCode() : 0);
		result = 31 * result + (multiplier != null ? multiplier.hashCode() : 0);
		return result;
	}
}
