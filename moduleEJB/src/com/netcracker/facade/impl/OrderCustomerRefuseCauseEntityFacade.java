package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderCustomerRefuseCauseEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderCustomerRefuseCauseEntityFacade extends AbstractFacade<OrderCustomerRefuseCauseEntity> implements com.netcracker.facade.local_int.OrderCustomerRefuseCause {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderCustomerRefuseCauseEntityFacade() {
        super(OrderCustomerRefuseCauseEntity.class);
    }
    
}
