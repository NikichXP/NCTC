package com.netcracker.facade.local_int;

/* 0:14 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface FavouriteAddress {
	public void create(FavouriteAddress entity);

	public FavouriteAddress read(Object id);

	public void update(FavouriteAddress entity);

	public void delete(FavouriteAddress entity);

	public List<FavouriteAddress> findAll();

	public List<FavouriteAddress> findRange(int[] range);

	public int count();
}
