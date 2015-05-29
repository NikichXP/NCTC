package com.netcracker.facade.impl;



import com.netcracker.entity.CarEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Stateless
public class CarFacade extends AbstractFacade<CarEntity> implements com.netcracker.facade.local_int.Car {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	public void delete(BigInteger bigInteger) {
		em.createNamedQuery("Car.deleteById").setParameter("id", bigInteger).executeUpdate();
	}

	@Override
	public boolean isLicenceUsed(String licencePlate) {
		return !em.createNamedQuery("Car.isLicenceUsed").setParameter("licence", licencePlate).getResultList().isEmpty();
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CarFacade() {
		super(CarEntity.class);
	}

}
