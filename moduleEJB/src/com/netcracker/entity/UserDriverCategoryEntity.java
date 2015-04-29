package com.netcracker.entity;

/* 16:13 29.04.2015 by Viktor Taranenko */

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user-driver_category", schema = "public", catalog = "postgres")
public class UserDriverCategoryEntity {
	private DriverCategoryEntity driverCategoryByDriverCategoryId;
	private UserEntity userByUserId;

	@ManyToOne
	@JoinColumn(name = "driver_category_id", referencedColumnName = "id", nullable = false)
	public DriverCategoryEntity getDriverCategoryByDriverCategoryId() {
		return driverCategoryByDriverCategoryId;
	}

	public void setDriverCategoryByDriverCategoryId(DriverCategoryEntity driverCategoryByDriverCategoryId) {
		this.driverCategoryByDriverCategoryId = driverCategoryByDriverCategoryId;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	public UserEntity getUserByUserId() {
		return userByUserId;
	}

	public void setUserByUserId(UserEntity userByUserId) {
		this.userByUserId = userByUserId;
	}
}
