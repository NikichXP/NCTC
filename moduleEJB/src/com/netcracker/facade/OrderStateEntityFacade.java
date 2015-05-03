package com.netcracker.facade;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderStateEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderStateEntityFacade extends AbstractFacade<OrderStateEntity> implements com.netcracker.facade.local_int.OrderState {
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
