package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface Path {
	public void create(Path entity);

	public Path read(Object id);

	public void update(Path entity);

	public void delete(Path entity);

	public List<Path> findAll();

	public List<Path> findRange(int[] range);

	public int count();
}
