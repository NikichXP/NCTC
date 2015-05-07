package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "tariff", schema = "public", catalog = "postgres")
public class TariffEntity {
	private BigInteger id;
	private BigInteger perHour;
	private BigInteger perKilometer;
	private Timestamp timeStarts;
	private Timestamp timeEnds;
	private Boolean animalFriendly;
	private Boolean wifi;
	private String sex;
	private BigInteger fromSeatsCount;
	private BigInteger toSeatsCount;
	private BigInteger multiplier;

	@SequenceGenerator(
			name = "tariff_SEQUENCE_GENERATOR",
			sequenceName = "tariff_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tariff_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Basic
	@Column(name = "per_hour", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getPerHour() {
		return perHour;
	}

	public void setPerHour(BigInteger perHour) {
		this.perHour = perHour;
	}

	@Basic
	@Column(name = "per_kilometer", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getPerKilometer() {
		return perKilometer;
	}

	public void setPerKilometer(BigInteger perKilometer) {
		this.perKilometer = perKilometer;
	}

	@Basic
	@Column(name = "time_starts", nullable = true, insertable = true, updatable = true)
	public Timestamp getTimeStarts() {
		return timeStarts;
	}

	public void setTimeStarts(Timestamp timeStarts) {
		this.timeStarts = timeStarts;
	}

	@Basic
	@Column(name = "time_ends", nullable = true, insertable = true, updatable = true)
	public Timestamp getTimeEnds() {
		return timeEnds;
	}

	public void setTimeEnds(Timestamp timeEnds) {
		this.timeEnds = timeEnds;
	}

	@Basic
	@Column(name = "animal_friendly", nullable = true, insertable = true, updatable = true)
	public Boolean getAnimalFriendly() {
		return animalFriendly;
	}

	public void setAnimalFriendly(Boolean animalFriendly) {
		this.animalFriendly = animalFriendly;
	}

	@Basic
	@Column(name = "wifi", nullable = true, insertable = true, updatable = true)
	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	@Basic
	@Column(name = "sex", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Basic
	@Column(name = "from_seats_count", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getFromSeatsCount() {
		return fromSeatsCount;
	}

	public void setFromSeatsCount(BigInteger fromSeatsCount) {
		this.fromSeatsCount = fromSeatsCount;
	}

	@Basic
	@Column(name = "to_seats_count", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getToSeatsCount() {
		return toSeatsCount;
	}

	public void setToSeatsCount(BigInteger toSeatsCount) {
		this.toSeatsCount = toSeatsCount;
	}

	@Basic
	@Column(name = "multiplier", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigInteger multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TariffEntity that = (TariffEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (perHour != null ? !perHour.equals(that.perHour) : that.perHour != null) return false;
		if (perKilometer != null ? !perKilometer.equals(that.perKilometer) : that.perKilometer != null) return false;
		if (timeStarts != null ? !timeStarts.equals(that.timeStarts) : that.timeStarts != null) return false;
		if (timeEnds != null ? !timeEnds.equals(that.timeEnds) : that.timeEnds != null) return false;
		if (animalFriendly != null ? !animalFriendly.equals(that.animalFriendly) : that.animalFriendly != null)
			return false;
		if (wifi != null ? !wifi.equals(that.wifi) : that.wifi != null) return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
		if (fromSeatsCount != null ? !fromSeatsCount.equals(that.fromSeatsCount) : that.fromSeatsCount != null)
			return false;
		if (toSeatsCount != null ? !toSeatsCount.equals(that.toSeatsCount) : that.toSeatsCount != null) return false;
		if (multiplier != null ? !multiplier.equals(that.multiplier) : that.multiplier != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (perHour != null ? perHour.hashCode() : 0);
		result = 31 * result + (perKilometer != null ? perKilometer.hashCode() : 0);
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
