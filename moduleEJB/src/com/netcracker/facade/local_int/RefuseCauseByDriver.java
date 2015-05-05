package com.netcracker.facade.local_int;

/* 0:16 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.RefuseCauseByDriverEntity;

import java.util.List;

public interface RefuseCauseByDriver {
	public void create(RefuseCauseByDriverEntity entity);

	public RefuseCauseByDriverEntity read(Object id);

	public void update(RefuseCauseByDriverEntity entity);

	public void delete(RefuseCauseByDriverEntity entity);

	public List<RefuseCauseByDriverEntity> findAll();

	public List<RefuseCauseByDriverEntity> findRange(int[] range);

	public int count();
}
