package com.netcracker.facade.local_int;

/* 0:25 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface Tariff {
	public void create(Tariff entity);

	public Tariff read(Object id);

	public void update(Tariff entity);

	public void delete(Tariff entity);

	public List<Tariff> findAll();

	public List<Tariff> findRange(int[] range);

	public int count();
}
