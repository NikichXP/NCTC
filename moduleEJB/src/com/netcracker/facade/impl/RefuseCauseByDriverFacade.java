package com.netcracker.facade.impl;



import com.netcracker.entity.RefuseCauseByDriverEntity;
import com.netcracker.facade.local_int.RefuseCauseByDriver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RefuseCauseByDriverFacade extends AbstractFacade<RefuseCauseByDriverEntity> implements RefuseCauseByDriver {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RefuseCauseByDriverFacade() {
		super(RefuseCauseByDriverEntity.class);
	}

}
