package com.netcracker.facade.local_int;

/* 0:12 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.CarEntity;

import java.util.List;

public interface Car {
	public void create(CarEntity entity);

	public CarEntity read(Object id);

	public void update(CarEntity entity);

	public void delete(CarEntity entity);

	public List<CarEntity> findAll();

	public List<CarEntity> findRange(int[] range);

	public int count();
}
