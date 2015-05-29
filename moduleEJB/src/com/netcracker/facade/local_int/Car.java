package com.netcracker.facade.local_int;



import com.netcracker.entity.CarEntity;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;

@Local
public interface Car {
	void create(CarEntity entity);

	CarEntity read(Object id);

	void update(CarEntity entity);

	void delete(CarEntity entity);

	List<CarEntity> findAll();

	List<CarEntity> findRange(int[] range);

	int count();

	void delete(BigInteger bigInteger);

	boolean isLicenceUsed(String licencePlate);
}
