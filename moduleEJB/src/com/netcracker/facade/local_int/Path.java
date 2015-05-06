package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.PathEntity;

import java.util.List;

public interface Path {
	public void create(PathEntity entity);

	public PathEntity read(Object id);

	public void update(PathEntity entity);

	public void delete(PathEntity entity);

	public List<PathEntity> findAll();

	public List<PathEntity> findRange(int[] range);

	public int count();
}
