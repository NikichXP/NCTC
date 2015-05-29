package com.netcracker.facade.local_int;



import com.netcracker.entity.RefuseCauseByCustomerEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RefuseCauseByCustomer {
	void create(RefuseCauseByCustomerEntity entity);

	RefuseCauseByCustomerEntity read(Object id);

	void update(RefuseCauseByCustomerEntity entity);

	void delete(RefuseCauseByCustomerEntity entity);

	List<RefuseCauseByCustomerEntity> findAll();

	List<RefuseCauseByCustomerEntity> findRange(int[] range);

	int count();
}
