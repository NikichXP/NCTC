package com.netcracker.facade.local_int;

/* 0:13 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.DriverCategoryEntity;

import java.util.List;

public interface DriverCategory {
	public void create(DriverCategoryEntity entity);

	public DriverCategoryEntity read(Object id);

	public void update(DriverCategoryEntity entity);

	public void delete(DriverCategoryEntity entity);

	public List<DriverCategoryEntity> findAll();

	public List<DriverCategoryEntity> findRange(int[] range);

	public int count();
}
