package com.netcracker.entity;

/* 16:13 29.04.2015 by Viktor Taranenko */

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user-user_access_level", schema = "public", catalog = "postgres")
public class UserUserAccessLevelEntity {
	private UserEntity userByUserId;
	private UserAccessLevelEntity userAccessLevelByUserAccessLevelId;

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
