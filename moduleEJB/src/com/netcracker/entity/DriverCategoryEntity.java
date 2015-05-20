package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "driver_category", schema = "public", catalog = "postgres")
@NamedQueries({
		@NamedQuery(name = "User.findByName", query = "SELECT f FROM DriverCategoryEntity f " +
				"WHERE UPPER(f.name) = UPPER(:name)")
})
public class DriverCategoryEntity {
	@SequenceGenerator(
			name = "DRIVER_CATEGORY_SEQUENCE_GENERATOR",
			sequenceName = "DRIVER_CATEGORY_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DRIVER_CATEGORY_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@OneToMany(mappedBy = "driverCategoryEntity")
	private Collection<CarEntity> carEntities;

	@OneToMany(mappedBy = "driverCategoryEntity")
	private Collection<UserDriverCategoryEntity> user$DriverCategoryEntities;

	@ManyToMany (mappedBy = "driverCategoryEntities")
	private Collection<UserEntity> userEntities;

	@Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String name;

	@Column(name = "description", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String description;

	@Column(name = "tariff_multiplier", nullable = false, insertable = true, updatable = true, precision = 3)
	private BigDecimal tariffMultiplier;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTariffMultiplier() {
		return tariffMultiplier;
	}

	public void setTariffMultiplier(BigDecimal tariffMultiplier) {
		this.tariffMultiplier = tariffMultiplier;
	}

	public Collection<CarEntity> getCarEntities() {
		return carEntities;
	}

	public void setCarEntities(Collection<CarEntity> carEntities) {
		this.carEntities = carEntities;
	}

	public Collection<UserDriverCategoryEntity> getUser$DriverCategoryEntities() {
		return user$DriverCategoryEntities;
	}

	public void setUser$DriverCategoryEntities(Collection<UserDriverCategoryEntity> user$DriverCategoryEntities) {
		this.user$DriverCategoryEntities = user$DriverCategoryEntities;
	}

	public Collection<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Collection<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DriverCategoryEntity that = (DriverCategoryEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (description != null ? !description.equals(that.description) : that.description != null) return false;
		return !(tariffMultiplier != null ? !tariffMultiplier.equals(that.tariffMultiplier) : that.tariffMultiplier != null);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (tariffMultiplier != null ? tariffMultiplier.hashCode() : 0);
		return result;
	}
}
