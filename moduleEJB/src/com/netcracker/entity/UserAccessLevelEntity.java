package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "user_access_level", schema = "public", catalog = "postgres")
public class UserAccessLevelEntity {
	@SequenceGenerator(
			name = "user-user_access_level_SEQUENCE_GENERATOR",
			sequenceName = "user-user_access_level_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_access_level_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@OneToMany(mappedBy = "userAccessLevelEntity")
	private Collection<User$UserAccessLevelEntity> user$UserAccessLevelEntities;

	@ManyToMany (mappedBy = "userAccessLevelEntities")
	private Collection<UserEntity> userEntities;

	@Basic
	@Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String name;

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

	public Collection<User$UserAccessLevelEntity> getUser$UserAccessLevelEntities() {
		return user$UserAccessLevelEntities;
	}

	public void setUser$UserAccessLevelEntities(Collection<User$UserAccessLevelEntity> user$UserAccessLevelEntities) {
		this.user$UserAccessLevelEntities = user$UserAccessLevelEntities;
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

		UserAccessLevelEntity that = (UserAccessLevelEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		return !(name != null ? !name.equals(that.name) : that.name != null);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
