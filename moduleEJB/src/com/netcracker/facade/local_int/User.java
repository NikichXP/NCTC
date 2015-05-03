package com.netcracker.facade.local_int;

/* 0:26 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface User {
	public void create(User entity);

	public User read(Object id);

	public void update(User entity);

	public void delete(User entity);

	public List<User> findAll();

	public List<User> findRange(int[] range);

	public int count();
}
