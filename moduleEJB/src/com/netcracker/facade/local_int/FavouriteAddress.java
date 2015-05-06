package com.netcracker.facade.local_int;

/* 0:14 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.FavouriteAddressEntity;

import java.util.List;

public interface FavouriteAddress {
	public void create(FavouriteAddressEntity entity);

	public FavouriteAddressEntity read(Object id);

	public void update(FavouriteAddressEntity entity);

	public void delete(FavouriteAddressEntity entity);

	public List<FavouriteAddressEntity> findAll();

	public List<FavouriteAddressEntity> findRange(int[] range);

	public int count();
}
