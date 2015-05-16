package com.netcracker.facade.local_int;

/* 0:15 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.MusicTypeEntity;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;

@Local
public interface MusicType {
	void create(MusicTypeEntity entity);

	MusicTypeEntity read(Object id);

	void update(MusicTypeEntity entity);

	void delete(MusicTypeEntity entity);

	List<MusicTypeEntity> findAll();

	List<MusicTypeEntity> findRange(int[] range);

	int count();

	MusicTypeEntity findByName(String name);

	MusicTypeEntity findById(BigInteger id);
}
