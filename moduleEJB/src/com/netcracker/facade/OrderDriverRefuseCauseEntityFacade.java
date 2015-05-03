package com.netcracker.facade;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderDriverRefuseCauseEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderDriverRefuseCauseEntityFacade extends AbstractFacade<OrderDriverRefuseCauseEntity> implements com.netcracker.facade.local_int.OrderDriverRefuseCause {
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
