package com.netcracker.facade;

import com.netcracker.entity.OrderCustomerRefuseCauseEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderCustomerRefuseCauseEntityFacade extends AbstractFacade<OrderCustomerRefuseCauseEntity> {
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
