package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user$user_access_level", schema = "public", catalog = "postgres")
public class UserUserAccessLevelEntity {
	@SequenceGenerator(
			name = "user-user_access_level_SEQUENCE_GENERATOR",
			sequenceName = "user-user_access_level_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user-user_access_level_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserEntity userByUserId;

	public UserEntity getUserByUserId() {
		return userByUserId;
	}

	public void setUserByUserId(UserEntity userByUserId) {
		this.userByUserId = userByUserId;
	}

	@ManyToOne
	@JoinColumn(name = "user_access_level_id", referencedColumnName = "id", nullable = false)
	private UserAccessLevelEntity userAccessLevelByUserAccessLevelId;

	public UserAccessLevelEntity getUserAccessLevelByUserAccessLevelId() {
		return userAccessLevelByUserAccessLevelId;
	}

	public void setUserAccessLevelByUserAccessLevelId(UserAccessLevelEntity userAccessLevelByUserAccessLevelId) {
		this.userAccessLevelByUserAccessLevelId = userAccessLevelByUserAccessLevelId;
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
}
