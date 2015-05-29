package com.netcracker.facade.local_int;



import com.netcracker.entity.OrderStateEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderState {
	void create(OrderStateEntity entity);

	OrderStateEntity read(Object id);

	void update(OrderStateEntity entity);

	void delete(OrderStateEntity entity);

	List<OrderStateEntity> findAll();

	List<OrderStateEntity> findRange(int[] range);

	int count();

	OrderStateEntity findByName(String name);
}
