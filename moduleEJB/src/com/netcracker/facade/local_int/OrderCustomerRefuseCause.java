package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface OrderCustomerRefuseCause {
	public void create(OrderCustomerRefuseCause entity);

	public OrderCustomerRefuseCause read(Object id);

	public void update(OrderCustomerRefuseCause entity);

	public void delete(OrderCustomerRefuseCause entity);

	public List<OrderCustomerRefuseCause> findAll();

	public List<OrderCustomerRefuseCause> findRange(int[] range);

	public int count();
}
