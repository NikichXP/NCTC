package com.netcracker.facade.local_int;

/* 0:27 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface UserGroup {
	public void create(UserGroup entity);

	public UserGroup read(Object id);

	public void update(UserGroup entity);

	public void delete(UserGroup entity);

	public List<UserGroup> findAll();

	public List<UserGroup> findRange(int[] range);

	public int count();
}
