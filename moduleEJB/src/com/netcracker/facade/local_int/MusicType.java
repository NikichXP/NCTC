package com.netcracker.facade.local_int;

/* 0:15 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.MusicTypeEntity;

import java.math.BigInteger;
import java.util.List;

public interface MusicType {
	public void create(MusicTypeEntity entity);

	public MusicTypeEntity read(Object id);

	public void update(MusicTypeEntity entity);

	public void delete(MusicTypeEntity entity);

	public List<MusicTypeEntity> findAll();

	public List<MusicTypeEntity> findRange(int[] range);

	public int count();

	MusicTypeEntity findByName(String name);
	MusicTypeEntity findById(BigInteger id);
}
