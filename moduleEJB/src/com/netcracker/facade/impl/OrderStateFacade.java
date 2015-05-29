package com.netcracker.facade.impl;



import com.netcracker.entity.OrderStateEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderStateFacade extends AbstractFacade<OrderStateEntity> implements com.netcracker.facade.local_int.OrderState {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OrderStateFacade() {
		super(OrderStateEntity.class);
	}

	@Override
	public OrderStateEntity findByName(String name) {
		List results = em.createNamedQuery("OrderState.findByName").setParameter("name", name).getResultList();
		if (!results.isEmpty()) {
			return (OrderStateEntity) results.get(0);
		}
		return null;
	}
}
