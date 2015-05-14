package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "music_type", schema = "public", catalog = "postgres")
@NamedQueries({
		@NamedQuery(name = "MusicType.findByName", query = "SELECT f FROM MusicTypeEntity f WHERE upper(f.name) = upper(:name)"),
		@NamedQuery(name = "MusicType.findById", query = "SELECT f FROM MusicTypeEntity f WHERE f.id = :id")})


public class MusicTypeEntity {
	@SequenceGenerator(
			name = "MUSIC_TYPE_SEQUENCE_GENERATOR",
			sequenceName = "MUSIC_TYPE_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUSIC_TYPE_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;
	@OneToMany(mappedBy="musicTypeEntity")
	private Collection<OrderEntity> orderEntities;
	@Basic
	@Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String name;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MusicTypeEntity that = (MusicTypeEntity) o;

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
