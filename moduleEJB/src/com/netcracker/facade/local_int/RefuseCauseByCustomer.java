package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.RefuseCauseByCustomerEntity;

import java.util.List;

public interface RefuseCauseByCustomer {
	public void create(RefuseCauseByCustomerEntity entity);

	public RefuseCauseByCustomerEntity read(Object id);

	public void update(RefuseCauseByCustomerEntity entity);

	public void delete(RefuseCauseByCustomerEntity entity);

	public List<RefuseCauseByCustomerEntity> findAll();

	public List<RefuseCauseByCustomerEntity> findRange(int[] range);

	public int count();
}
