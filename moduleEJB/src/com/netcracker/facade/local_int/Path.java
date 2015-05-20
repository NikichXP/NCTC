package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.PathEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Path {
	void create(PathEntity entity);

	PathEntity read(Object id);

	void update(PathEntity entity);

	void delete(PathEntity entity);

	List<PathEntity> findAll();

	List<PathEntity> findRange(int[] range);

	List<PathEntity> getByOrderEntitie(OrderEntity orderEntity);

	int count();
}
