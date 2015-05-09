package com.netcracker.facade.local_int;

/* 0:26 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.UserEntity;

import java.util.List;

public interface User {
	public void create(UserEntity entity);

	public UserEntity read(Object id);

	public void update(UserEntity entity);

	public void delete(UserEntity entity);

	public List<UserEntity> findAll();

	public List<UserEntity> findRange(int[] range);

	public int count();

	boolean isEmailUsed(String name);

	boolean isPhoneUsed(String phone);

	UserEntity findByEmail(String email);

	UserEntity findByPhone(String phone);

	UserEntity findByUuid(String uuid);

	UserEntity loginByEmail(String email, String password);

	UserEntity loginByPhone(String phone, String password);
}
