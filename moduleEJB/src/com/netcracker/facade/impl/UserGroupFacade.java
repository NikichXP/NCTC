package com.netcracker.facade.impl;



import com.netcracker.entity.UserGroupEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserGroupFacade extends AbstractFacade<UserGroupEntity> implements com.netcracker.facade.local_int.UserGroup {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UserGroupFacade() {
		super(UserGroupEntity.class);
	}

}
