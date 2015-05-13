package com.netcracker.facade.local_int;

/* 0:00 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.CarClassEntity;

import java.util.List;

public interface CarClass {
	public void create(CarClassEntity entity);

	public CarClassEntity read(Object id);

	public void update(CarClassEntity entity);

	public void delete(CarClassEntity entity);

	public List<CarClassEntity> findAll();

	public List<CarClassEntity> findRange(int[] range);

	public int count();

	CarClassEntity findByName(String name);
}
