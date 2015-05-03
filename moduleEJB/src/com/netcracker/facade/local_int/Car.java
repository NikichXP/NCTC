package com.netcracker.facade.local_int;

/* 0:12 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface Car {
	public void create(Car entity);

	public Car read(Object id);

	public void update(Car entity);

	public void delete(Car entity);

	public List<Car> findAll();

	public List<Car> findRange(int[] range);

	public int count();
}
