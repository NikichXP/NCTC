package com.netcracker.facade.impl;



import com.netcracker.entity.PathEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PathFacade extends AbstractFacade<PathEntity> implements com.netcracker.facade.local_int.Path {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PathFacade() {
		super(PathEntity.class);
	}

}
