package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "car_class", schema = "public", catalog = "postgres")
public class CarClassEntity {
	private BigInteger id;
	private String name;
	private BigInteger tariffMultiplier;
	private Collection<CarEntity> carsById;

	@SequenceGenerator(
			name = "CAR_CLASS_SEQUENCE_GENERATOR",
			sequenceName = "CAR_CLASS_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_CLASS_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "tariff_multiplier", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getTariffMultiplier() {
		return tariffMultiplier;
	}

	public void setTariffMultiplier(BigInteger tariffMultiplier) {
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

	@OneToMany(mappedBy = "carClassByClassId")
	public Collection<CarEntity> getCarsById() {
		return carsById;
	}

	public void setCarsById(Collection<CarEntity> carsById) {
		this.carsById = carsById;
	}
}
