package com.netcracker.facade.local_int;



import com.netcracker.entity.UserGroupEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserGroup {
	void create(UserGroupEntity entity);

	UserGroupEntity read(Object id);

	void update(UserGroupEntity entity);

	void delete(UserGroupEntity entity);

	List<UserGroupEntity> findAll();

	List<UserGroupEntity> findRange(int[] range);

	int count();
}
