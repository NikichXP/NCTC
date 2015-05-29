package com.netcracker.facade.local_int;



import com.netcracker.entity.RefuseCauseByDriverEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RefuseCauseByDriver {
	void create(RefuseCauseByDriverEntity entity);

	RefuseCauseByDriverEntity read(Object id);

	void update(RefuseCauseByDriverEntity entity);

	void delete(RefuseCauseByDriverEntity entity);

	List<RefuseCauseByDriverEntity> findAll();

	List<RefuseCauseByDriverEntity> findRange(int[] range);

	int count();
}
