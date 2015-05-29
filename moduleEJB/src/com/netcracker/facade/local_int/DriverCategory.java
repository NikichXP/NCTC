package com.netcracker.facade.local_int;



import com.netcracker.entity.DriverCategoryEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DriverCategory {
	void create(DriverCategoryEntity entity);

	DriverCategoryEntity read(Object id);

	void update(DriverCategoryEntity entity);

	void delete(DriverCategoryEntity entity);

	List<DriverCategoryEntity> findAll();

	List<DriverCategoryEntity> findRange(int[] range);

	int count();

	DriverCategoryEntity findByName(String requiredDriverCategory);
}
