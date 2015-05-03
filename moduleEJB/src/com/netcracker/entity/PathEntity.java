package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "path", schema = "public", catalog = "postgres")
public class PathEntity {
	private BigInteger id;
	private BigInteger startX;
	private BigInteger startY;
	private BigInteger endX;
	private BigInteger endY;
	private String startAddress;
	private String endAddress;
	private boolean completed;
	private BigInteger length;
	private BigInteger price;
	private PathEntity pathByNextPathId;
	private Collection<PathEntity> pathsById;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Basic
	@Column(name = "start_x", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getStartX() {
		return startX;
	}

	public void setStartX(BigInteger startX) {
		this.startX = startX;
	}

	@Basic
	@Column(name = "start_y", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getStartY() {
		return startY;
	}

	public void setStartY(BigInteger startY) {
		this.startY = startY;
	}

	@Basic
	@Column(name = "end_x", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getEndX() {
		return endX;
	}

	public void setEndX(BigInteger endX) {
		this.endX = endX;
	}

	@Basic
	@Column(name = "end_y", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getEndY() {
		return endY;
	}

	public void setEndY(BigInteger endY) {
		this.endY = endY;
	}

	@Basic
	@Column(name = "start_address", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	@Basic
	@Column(name = "end_address", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	@Basic
	@Column(name = "completed", nullable = false, insertable = true, updatable = true)
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Basic
	@Column(name = "length", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getLength() {
		return length;
	}

	public void setLength(BigInteger length) {
		this.length = length;
	}

	@Basic
	@Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
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

	@ManyToOne
	@JoinColumn(name = "next_path_id", referencedColumnName = "id")
	public PathEntity getPathByNextPathId() {
		return pathByNextPathId;
	}

	public void setPathByNextPathId(PathEntity pathByNextPathId) {
		this.pathByNextPathId = pathByNextPathId;
	}

	@OneToMany(mappedBy = "pathByNextPathId")
	public Collection<PathEntity> getPathsById() {
		return pathsById;
	}

	public void setPathsById(Collection<PathEntity> pathsById) {
		this.pathsById = pathsById;
	}
}
