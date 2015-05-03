package com.netcracker.facade.local_int;

/* 0:27 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.UserGroupEntity;

import java.util.List;

public interface UserGroup {
	public void create(UserGroupEntity entity);

	public UserGroupEntity read(Object id);

	public void update(UserGroupEntity entity);

	public void delete(UserGroupEntity entity);

	public List<UserGroupEntity> findAll();

	public List<UserGroupEntity> findRange(int[] range);

	public int count();
}
