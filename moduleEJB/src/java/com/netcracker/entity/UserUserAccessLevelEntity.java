package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user-user_access_level", schema = "public", catalog = "postgres")
public class UserUserAccessLevelEntity {
	private BigInteger id;
	private UserEntity userByUserId;
	private UserAccessLevelEntity userAccessLevelByUserAccessLevelId;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserUserAccessLevelEntity that = (UserUserAccessLevelEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	public UserEntity getUserByUserId() {
		return userByUserId;
	}

	public void setUserByUserId(UserEntity userByUserId) {
		this.userByUserId = userByUserId;
	}

	@ManyToOne
	@JoinColumn(name = "user_access_level_id", referencedColumnName = "id", nullable = false)
	public UserAccessLevelEntity getUserAccessLevelByUserAccessLevelId() {
		return userAccessLevelByUserAccessLevelId;
	}

	public void setUserAccessLevelByUserAccessLevelId(UserAccessLevelEntity userAccessLevelByUserAccessLevelId) {
		this.userAccessLevelByUserAccessLevelId = userAccessLevelByUserAccessLevelId;
	}
}
