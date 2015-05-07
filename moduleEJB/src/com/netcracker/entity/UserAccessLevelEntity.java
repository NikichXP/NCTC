package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "user_access_level", schema = "public", catalog = "postgres")
public class UserAccessLevelEntity {
	private BigInteger id;
	private String name;
	private Collection<UserUserAccessLevelEntity> userUserAccessLevelsById;

	@SequenceGenerator(
			name = "user-user_access_level_SEQUENCE_GENERATOR",
			sequenceName = "user-user_access_level_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_access_level_SEQUENCE_GENERATOR")
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAccessLevelEntity that = (UserAccessLevelEntity) o;

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

	@OneToMany(mappedBy = "userAccessLevelByUserAccessLevelId")
	public Collection<UserUserAccessLevelEntity> getUserUserAccessLevelsById() {
		return userUserAccessLevelsById;
	}

	public void setUserUserAccessLevelsById(Collection<UserUserAccessLevelEntity> userUserAccessLevelsById) {
		this.userUserAccessLevelsById = userUserAccessLevelsById;
	}
}
