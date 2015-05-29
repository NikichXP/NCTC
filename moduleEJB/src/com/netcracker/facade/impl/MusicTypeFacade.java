package com.netcracker.facade.impl;



import com.netcracker.entity.MusicTypeEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

@Stateless
public class MusicTypeFacade extends AbstractFacade<MusicTypeEntity> implements com.netcracker.facade.local_int.MusicType {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MusicTypeFacade() {
		super(MusicTypeEntity.class);
	}

	@Override
	public MusicTypeEntity findByName(String name) {
		List results = em.createNamedQuery("MusicType.findByName").setParameter("name", name).getResultList();
		if (!results.isEmpty()) {
			return (MusicTypeEntity) results.get(0);
		}
		return null;
	}

	@Override
	public MusicTypeEntity findById(BigInteger id) {
		List results = em.createNamedQuery("MusicType.findById").setParameter("id", id).getResultList();
		if (!results.isEmpty()) {
			return (MusicTypeEntity) results.get(0);
		}
		return null;
	}
}
