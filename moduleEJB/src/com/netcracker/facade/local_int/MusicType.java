package com.netcracker.facade.local_int;

/* 0:15 04.05.2015 by Viktor Taranenko */

import java.util.List;

public interface MusicType {
	public void create(MusicType entity);

	public MusicType read(Object id);

	public void update(MusicType entity);

	public void delete(MusicType entity);

	public List<MusicType> findAll();

	public List<MusicType> findRange(int[] range);

	public int count();
}
