package com.netcracker.facade.local_int;

/* 0:26 04.05.2015 by Viktor Taranenko */

import com.netcracker.entity.UserEntity;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;

@Local
public interface User {
	void create(UserEntity entity);

	UserEntity read(Object id);

	void update(UserEntity entity);

	void delete(UserEntity entity);

	void delete(BigInteger id);

	List<UserEntity> findAll();

	List<UserEntity> findRange(int[] range);

	List<UserEntity> getDrivers();

	int count();

	boolean isEmailUsed(String name);

	boolean isPhoneUsed(String phone);

	UserEntity findByEmail(String email);

	UserEntity findByPhone(String phone);

	UserEntity findByUuid(String uuid);

	UserEntity loginByEmail(String email, String password);

	UserEntity loginByPhone(String phone, String password);
}
