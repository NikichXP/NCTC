package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.OrderStateEntity;
import com.netcracker.entity.PathEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Order {
	void create(OrderEntity entity);

	OrderEntity read(Object id);

	void update(OrderEntity entity);

	void delete(OrderEntity entity);

	List<OrderEntity> findAll();

	List<OrderEntity> findRange(int[] range);

	int count();

	List<OrderEntity> getOrdersByStateAndCustomerUuid(OrderStateEntity orderStateEntity, String customerUuid);

	List<OrderEntity> getOrdersByStateAndDriverUuid(OrderStateEntity orderStateEntity, String driverUuid);

	List<OrderEntity> getOrdersByState(OrderStateEntity orderStateEntity);

	List<Point> getFirstAndLastPoints(OrderEntity orderEntity);

	OrderEntity getByUUIDAndId(String orderId, String uuid);

}
