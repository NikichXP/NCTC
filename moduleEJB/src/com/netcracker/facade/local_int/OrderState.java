package com.netcracker.facade.local_int;

/* 0:21 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderStateEntity;

import java.util.List;

public interface OrderState {
	public void create(OrderStateEntity entity);

	public OrderStateEntity read(Object id);

	public void update(OrderStateEntity entity);

	public void delete(OrderStateEntity entity);

	public List<OrderStateEntity> findAll();

	public List<OrderStateEntity> findRange(int[] range);

	public int count();
}
