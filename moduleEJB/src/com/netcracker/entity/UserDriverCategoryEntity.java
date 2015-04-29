package com.netcracker.entity;

/* 16:38 29.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user-driver_category", schema = "public", catalog = "postgres")
public class UserDriverCategoryEntity {
	private BigInteger id;
	private DriverCategoryEntity driverCategoryByDriverCategoryId;
	private UserEntity userByUserId;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserDriverCategoryEntity that = (UserDriverCategoryEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

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
