package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderDriverRefuseCauseEntity;

import java.util.List;

public interface OrderDriverRefuseCause {
	public void create(OrderDriverRefuseCauseEntity entity);

	public OrderDriverRefuseCauseEntity read(Object id);

	public void update(OrderDriverRefuseCauseEntity entity);

	public void delete(OrderDriverRefuseCauseEntity entity);

	public List<OrderDriverRefuseCauseEntity> findAll();

	public List<OrderDriverRefuseCauseEntity> findRange(int[] range);

	public int count();
}
