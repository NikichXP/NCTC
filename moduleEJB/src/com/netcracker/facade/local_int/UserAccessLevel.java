package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.UserAccessLevelEntity;

import java.util.List;

public interface UserAccessLevel {
	public void create(UserAccessLevelEntity entity);

	public UserAccessLevelEntity read(Object id);

	public void update(UserAccessLevelEntity entity);

	public void delete(UserAccessLevelEntity entity);

	public List<UserAccessLevelEntity> findAll();

	public List<UserAccessLevelEntity> findRange(int[] range);

	public int count();
}
