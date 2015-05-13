package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "order_type", schema = "public", catalog = "postgres")
@NamedQueries({
		@NamedQuery(name = "OrderType.findByName", query = "SELECT f FROM OrderTypeEntity f WHERE upper(f.name) = upper(:name)")})
public class OrderTypeEntity {
	@SequenceGenerator(
			name = "ORDER_TYPE_SEQUENCE_GENERATOR",
			sequenceName = "ORDER_TYPE_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_TYPE_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;
	@Basic
	@Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String name;
	@OneToMany(mappedBy="orderTypeEntity")
	private Collection<OrderEntity> orderEntities;

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

	public Collection<OrderEntity> getOrderEntities() {
		return orderEntities;
	}

	public void setOrderEntities(Collection<OrderEntity> orderEntities) {
		this.orderEntities = orderEntities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderTypeEntity that = (OrderTypeEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
