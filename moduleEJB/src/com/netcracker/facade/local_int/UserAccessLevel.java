package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface UserAccessLevel {
	public void create(UserAccessLevel entity);

	public UserAccessLevel read(Object id);

	public void update(UserAccessLevel entity);

	public void delete(UserAccessLevel entity);

	public List<UserAccessLevel> findAll();

	public List<UserAccessLevel> findRange(int[] range);

	public int count();
}
