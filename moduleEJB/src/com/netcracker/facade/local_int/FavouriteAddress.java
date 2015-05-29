package com.netcracker.facade.local_int;



import com.netcracker.entity.FavouriteAddressEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface FavouriteAddress {
	void create(FavouriteAddressEntity entity);

	FavouriteAddressEntity read(Object id);

	void update(FavouriteAddressEntity entity);

	void delete(FavouriteAddressEntity entity);

	List<FavouriteAddressEntity> findAll();

	List<FavouriteAddressEntity> findRange(int[] range);

	int count();
}
