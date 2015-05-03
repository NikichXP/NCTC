package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface Order {
	public void create(Order entity);

	public Order read(Object id);

	public void update(Order entity);

	public void delete(Order entity);

	public List<Order> findAll();

	public List<Order> findRange(int[] range);

	public int count();
}
