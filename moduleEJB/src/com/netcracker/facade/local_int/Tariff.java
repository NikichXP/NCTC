package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.TariffEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Tariff {
	void create(TariffEntity entity);

	TariffEntity read(Object id);

	void update(TariffEntity entity);

	void delete(TariffEntity entity);

	List<TariffEntity> findAll();

	List<TariffEntity> findRange(int[] range);

	int count();
}
