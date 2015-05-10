package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user$driver_category", schema = "public", catalog = "postgres")
public class UserDriverCategoryEntity {
	@SequenceGenerator(
			name = "user-driver_category_SEQUENCE_GENERATOR",
			sequenceName = "user-driver_category_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user-driver_category_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "driver_category_id", referencedColumnName = "id", nullable = false)
	private DriverCategoryEntity driverCategoryByDriverCategoryId;

	public DriverCategoryEntity getDriverCategoryByDriverCategoryId() {
		return driverCategoryByDriverCategoryId;
	}

	public void setDriverCategoryByDriverCategoryId(DriverCategoryEntity driverCategoryByDriverCategoryId) {
		this.driverCategoryByDriverCategoryId = driverCategoryByDriverCategoryId;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserEntity userByUserId;

	public UserEntity getUserByUserId() {
		return userByUserId;
	}

	public void setUserByUserId(UserEntity userByUserId) {
		this.userByUserId = userByUserId;
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
}
