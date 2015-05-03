package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderEntity;

import java.util.List;

public interface Order {
	public void create(OrderEntity entity);

	public OrderEntity read(Object id);

	public void update(OrderEntity entity);

	public void delete(OrderEntity entity);

	public List<OrderEntity> findAll();

	public List<OrderEntity> findRange(int[] range);

	public int count();
}
