package com.netcracker.facade.local_int;

/* 0:00 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.CarClassEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CarClass {
	void create(CarClassEntity entity);

	CarClassEntity read(Object id);

	void update(CarClassEntity entity);

	void delete(CarClassEntity entity);

	List<CarClassEntity> findAll();

	List<CarClassEntity> findRange(int[] range);

	int count();

	CarClassEntity findByName(String name);
}
