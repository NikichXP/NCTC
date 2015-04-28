package com.netcracker.facade;

import com.netcracker.entity.OrderDriverRefuseCauseEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderDriverRefuseCauseEntityFacade extends AbstractFacade<OrderDriverRefuseCauseEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDriverRefuseCauseEntityFacade() {
        super(OrderDriverRefuseCauseEntity.class);
    }
    
}
