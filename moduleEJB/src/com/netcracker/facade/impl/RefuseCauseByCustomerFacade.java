package com.netcracker.facade.impl;



import com.netcracker.entity.RefuseCauseByCustomerEntity;
import com.netcracker.facade.local_int.RefuseCauseByCustomer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RefuseCauseByCustomerFacade extends AbstractFacade<RefuseCauseByCustomerEntity> implements RefuseCauseByCustomer {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RefuseCauseByCustomerFacade() {
		super(RefuseCauseByCustomerEntity.class);
	}

}
