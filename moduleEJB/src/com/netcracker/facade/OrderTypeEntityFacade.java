package com.netcracker.facade;

import com.netcracker.entity.OrderTypeEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderTypeEntityFacade extends AbstractFacade<OrderTypeEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderTypeEntityFacade() {
        super(OrderTypeEntity.class);
    }
    
}
