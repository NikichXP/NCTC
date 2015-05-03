package com.netcracker.facade.local_int;

/* 0:22 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface OrderType {
	public void create(OrderType entity);

	public OrderType read(Object id);

	public void update(OrderType entity);

	public void delete(OrderType entity);

	public List<OrderType> findAll();

	public List<OrderType> findRange(int[] range);

	public int count();
}
