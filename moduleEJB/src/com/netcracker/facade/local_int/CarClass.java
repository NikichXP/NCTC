package com.netcracker.facade.local_int;

/* 0:00 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface CarClass {
	public void create(CarClass entity);

	public CarClass read(Object id);

	public void update(CarClass entity);

	public void delete(CarClass entity);

	public List<CarClass> findAll();

	public List<CarClass> findRange(int[] range);

	public int count();
}
