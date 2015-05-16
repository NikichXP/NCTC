package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "favourite_address", schema = "public", catalog = "postgres")
public class FavouriteAddressEntity {
	@SequenceGenerator(
			name = "FAVOURITE_ADDRESS_SEQUENCE_GENERATOR",
			sequenceName = "FAVOURITE_ADDRESS_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAVOURITE_ADDRESS_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@Column(name = "address", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String address;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
	private UserEntity userEntity;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
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

}
