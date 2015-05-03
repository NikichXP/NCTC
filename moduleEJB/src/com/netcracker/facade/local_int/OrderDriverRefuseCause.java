package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface OrderDriverRefuseCause {
	public void create(OrderDriverRefuseCause entity);

	public OrderDriverRefuseCause read(Object id);

	public void update(OrderDriverRefuseCause entity);

	public void delete(OrderDriverRefuseCause entity);

	public List<OrderDriverRefuseCause> findAll();

	public List<OrderDriverRefuseCause> findRange(int[] range);

	public int count();
}
