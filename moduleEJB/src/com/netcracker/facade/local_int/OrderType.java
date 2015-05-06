package com.netcracker.facade.local_int;

/* 0:22 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderTypeEntity;

import java.util.List;

public interface OrderType {
	public void create(OrderTypeEntity entity);

	public OrderTypeEntity read(Object id);

	public void update(OrderTypeEntity entity);

	public void delete(OrderTypeEntity entity);

	public List<OrderTypeEntity> findAll();

	public List<OrderTypeEntity> findRange(int[] range);

	public int count();
}
