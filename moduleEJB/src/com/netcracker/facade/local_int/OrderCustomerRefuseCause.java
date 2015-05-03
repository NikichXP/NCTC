package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderCustomerRefuseCauseEntity;

import java.util.List;

public interface OrderCustomerRefuseCause {
	public void create(OrderCustomerRefuseCauseEntity entity);

	public OrderCustomerRefuseCauseEntity read(Object id);

	public void update(OrderCustomerRefuseCauseEntity entity);

	public void delete(OrderCustomerRefuseCauseEntity entity);

	public List<OrderCustomerRefuseCauseEntity> findAll();

	public List<OrderCustomerRefuseCauseEntity> findRange(int[] range);

	public int count();
}
