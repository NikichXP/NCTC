package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "car_class", schema = "public", catalog = "postgres")
@NamedQueries({
		@NamedQuery(name = "CarClass.findByName", query = "SELECT f FROM CarClassEntity f WHERE upper(f.name) = upper(:name)")})
public class CarClassEntity {
	@SequenceGenerator(
			name = "CAR_CLASS_SEQUENCE_GENERATOR",
			sequenceName = "CAR_CLASS_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_CLASS_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;
	@OneToMany (mappedBy = "carClassEntity")
	private Collection<OrderEntity> orderEntities;
	@Basic
	@Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String name;
	@Basic
	@Column(name = "tariff_multiplier", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigDecimal tariffMultiplier;
	@OneToMany(mappedBy = "carClassEntity")
	private Collection<CarEntity> carsById;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Collection<OrderEntity> getOrderEntities() {
		return orderEntities;
	}

	public void setOrderEntities(Collection<OrderEntity> orderEntities) {
		this.orderEntities = orderEntities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTariffMultiplier() {
		return tariffMultiplier;
	}

	public void setTariffMultiplier(BigDecimal tariffMultiplier) {
		this.tariffMultiplier = tariffMultiplier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CarClassEntity that = (CarClassEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (tariffMultiplier != null ? !tariffMultiplier.equals(that.tariffMultiplier) : that.tariffMultiplier != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (tariffMultiplier != null ? tariffMultiplier.hashCode() : 0);
		return result;
	}

	public Collection<CarEntity> getCarsById() {
		return carsById;
	}

	public void setCarsById(Collection<CarEntity> carsById) {
		this.carsById = carsById;
	}
}
