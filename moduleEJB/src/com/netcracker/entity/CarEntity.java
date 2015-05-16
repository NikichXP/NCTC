package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "car", schema = "public", catalog = "postgres")
public class CarEntity {
	@SequenceGenerator(
			name = "CAR_SEQUENCE_GENERATOR",
			sequenceName = "CAR_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity userEntity;

	@ManyToOne
	@JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
	private CarClassEntity carClassEntity;

	@ManyToOne
	@JoinColumn(name = "required_driver_category_id", referencedColumnName = "id", nullable = false)
	private DriverCategoryEntity driverCategoryEntity;

	@Column(name = "model", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String model;

	@Column(name = "seats_count", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger seatsCount;

	@Column(name = "licence_plate", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String licencePlate;

	@Column(name = "date_manufactured", nullable = false, insertable = true, updatable = true)
	private Date dateManufactured;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigInteger getSeatsCount() {
		return seatsCount;
	}

	public void setSeatsCount(BigInteger seatsCount) {
		this.seatsCount = seatsCount;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public Date getDateManufactured() {
		return dateManufactured;
	}

	public void setDateManufactured(Date dateManufactured) {
		this.dateManufactured = dateManufactured;
	}

	public CarClassEntity getCarClassEntity() {
		return carClassEntity;
	}

	public void setCarClassEntity(CarClassEntity carClassEntity) {
		this.carClassEntity = carClassEntity;
	}

	public DriverCategoryEntity getDriverCategoryEntity() {
		return driverCategoryEntity;
	}

	public void setDriverCategoryEntity(DriverCategoryEntity driverCategoryEntity) {
		this.driverCategoryEntity = driverCategoryEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CarEntity carEntity = (CarEntity) o;

		if (id != null ? !id.equals(carEntity.id) : carEntity.id != null) return false;
		if (model != null ? !model.equals(carEntity.model) : carEntity.model != null) return false;
		if (seatsCount != null ? !seatsCount.equals(carEntity.seatsCount) : carEntity.seatsCount != null) return false;
		if (licencePlate != null ? !licencePlate.equals(carEntity.licencePlate) : carEntity.licencePlate != null)
			return false;
		return !(dateManufactured != null ? !dateManufactured.equals(carEntity.dateManufactured) : carEntity.dateManufactured != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (model != null ? model.hashCode() : 0);
		result = 31 * result + (seatsCount != null ? seatsCount.hashCode() : 0);
		result = 31 * result + (licencePlate != null ? licencePlate.hashCode() : 0);
		result = 31 * result + (dateManufactured != null ? dateManufactured.hashCode() : 0);
		return result;
	}
}
