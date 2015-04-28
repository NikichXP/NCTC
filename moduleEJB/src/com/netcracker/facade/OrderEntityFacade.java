package com.netcracker.facade;

import com.netcracker.entity.OrderEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderEntityFacade extends AbstractFacade<OrderEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderEntityFacade() {
        super(OrderEntity.class);
    }
    
}
