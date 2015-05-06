package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.TariffEntity;

import java.util.List;

public interface Tariff {
	public void create(TariffEntity entity);

	public TariffEntity read(Object id);

	public void update(TariffEntity entity);

	public void delete(TariffEntity entity);

	public List<TariffEntity> findAll();

	public List<TariffEntity> findRange(int[] range);

	public int count();
}
