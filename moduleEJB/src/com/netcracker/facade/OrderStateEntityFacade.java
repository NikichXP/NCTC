package com.netcracker.facade;

import com.netcracker.entity.OrderStateEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderStateEntityFacade extends AbstractFacade<OrderStateEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderStateEntityFacade() {
        super(OrderStateEntity.class);
    }
    
}
