package com.netcracker.entity;

/* 16:12 29.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "favourite_address", schema = "public", catalog = "postgres")
public class FavouriteAddressEntity {
	private BigInteger id;
	private String address;
	private UserEntity userByCustomerId;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Basic
	@Column(name = "address", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FavouriteAddressEntity that = (FavouriteAddressEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (address != null ? !address.equals(that.address) : that.address != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
	public UserEntity getUserByCustomerId() {
		return userByCustomerId;
	}

	public void setUserByCustomerId(UserEntity userByCustomerId) {
		this.userByCustomerId = userByCustomerId;
	}
}
