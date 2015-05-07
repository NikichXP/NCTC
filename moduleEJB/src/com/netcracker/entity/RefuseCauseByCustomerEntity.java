package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "order_customer_refuse_cause", schema = "public", catalog = "postgres")
public class RefuseCauseByCustomerEntity {
	private BigInteger id;
	private String message;

	@SequenceGenerator(
			name = "customer_refuse_cause_SEQUENCE_GENERATOR",
			sequenceName = "customer_refuse_cause_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_refuse_cause_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Basic
	@Column(name = "message", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RefuseCauseByCustomerEntity that = (RefuseCauseByCustomerEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (message != null ? !message.equals(that.message) : that.message != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		return result;
	}
}
