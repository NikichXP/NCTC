package com.netcracker.facade.local_int;



import com.netcracker.entity.PathEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Path {
	void create(PathEntity entity);

	PathEntity read(Object id);

	void update(PathEntity entity);

	void delete(PathEntity entity);

	List<PathEntity> findAll();

	List<PathEntity> findRange(int[] range);

	int count();
}
