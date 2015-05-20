package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "path", schema = "public", catalog = "postgres")
public class PathEntity {
	@SequenceGenerator(
			name = "PATH_SEQUENCE_GENERATOR",
			sequenceName = "PATH_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATH_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private OrderEntity orderEntity;

	@ManyToOne
	@JoinColumn(name = "next_path_id", referencedColumnName = "id")
	private PathEntity nextPathEntity;

	@OneToMany(mappedBy = "nextPathEntity")
	private Collection<PathEntity> nextPathEntities;

	@Column(name = "start_x", nullable = false, insertable = true, updatable = true, precision = 6)
	private BigDecimal startX;

	@Column(name = "start_y", nullable = false, insertable = true, updatable = true, precision = 6)
	private BigDecimal startY;

	@Column(name = "end_x", nullable = false, insertable = true, updatable = true, precision = 6)
	private BigDecimal endX;

	@Column(name = "end_y", nullable = false, insertable = true, updatable = true, precision = 6)
	private BigDecimal endY;

	@Column(name = "start_address", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String startAddress;

	@Column(name = "end_address", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String endAddress;

	@Column(name = "completed", nullable = false, insertable = true, updatable = true)
	private boolean completed;

	@Column(name = "length", nullable = false, insertable = true, updatable = true, precision = 3)
	private BigDecimal length;

	@Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 2)
	private BigDecimal price;

	public PathEntity() {
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigDecimal getStartX() {
		return startX;
	}

	public void setStartX(BigDecimal startX) {
		this.startX = startX;
	}

	public BigDecimal getStartY() {
		return startY;
	}

	public void setStartY(BigDecimal startY) {
		this.startY = startY;
	}

	public BigDecimal getEndX() {
		return endX;
	}

	public void setEndX(BigDecimal endX) {
		this.endX = endX;
	}

	public BigDecimal getEndY() {
		return endY;
	}

	public void setEndY(BigDecimal endY) {
		this.endY = endY;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public PathEntity getNextPathEntity() {
		return nextPathEntity;
	}

	public void setNextPathEntity(PathEntity nextPathEntity) {
		this.nextPathEntity = nextPathEntity;
	}

	public Collection<PathEntity> getNextPathEntities() {
		return nextPathEntities;
	}

	public void setNextPathEntities(Collection<PathEntity> nextPathEntities) {
		this.nextPathEntities = nextPathEntities;
	}

	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PathEntity that = (PathEntity) o;

		if (completed != that.completed) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (startX != null ? !startX.equals(that.startX) : that.startX != null) return false;
		if (startY != null ? !startY.equals(that.startY) : that.startY != null) return false;
		if (endX != null ? !endX.equals(that.endX) : that.endX != null) return false;
		if (endY != null ? !endY.equals(that.endY) : that.endY != null) return false;
		if (startAddress != null ? !startAddress.equals(that.startAddress) : that.startAddress != null) return false;
		if (endAddress != null ? !endAddress.equals(that.endAddress) : that.endAddress != null) return false;
		if (length != null ? !length.equals(that.length) : that.length != null) return false;
		if (price != null ? !price.equals(that.price) : that.price != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (startX != null ? startX.hashCode() : 0);
		result = 31 * result + (startY != null ? startY.hashCode() : 0);
		result = 31 * result + (endX != null ? endX.hashCode() : 0);
		result = 31 * result + (endY != null ? endY.hashCode() : 0);
		result = 31 * result + (startAddress != null ? startAddress.hashCode() : 0);
		result = 31 * result + (endAddress != null ? endAddress.hashCode() : 0);
		result = 31 * result + (completed ? 1 : 0);
		result = 31 * result + (length != null ? length.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		return result;
	}
}
