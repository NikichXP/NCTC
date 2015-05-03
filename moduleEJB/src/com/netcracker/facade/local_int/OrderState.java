package com.netcracker.facade.local_int;

/* 0:21 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface OrderState {
	public void create(OrderState entity);

	public OrderState read(Object id);

	public void update(OrderState entity);

	public void delete(OrderState entity);

	public List<OrderState> findAll();

	public List<OrderState> findRange(int[] range);

	public int count();
}
