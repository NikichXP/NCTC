package com.netcracker.facade.local_int;

/* 0:13 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface DriverCategory {
	public void create(DriverCategory entity);

	public DriverCategory read(Object id);

	public void update(DriverCategory entity);

	public void delete(DriverCategory entity);

	public List<DriverCategory> findAll();

	public List<DriverCategory> findRange(int[] range);

	public int count();
}
