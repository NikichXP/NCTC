package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderTypeEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderTypeFacade extends AbstractFacade<OrderTypeEntity> implements com.netcracker.facade.local_int.OrderType {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderTypeFacade() {
        super(OrderTypeEntity.class);
    }

    @Override
    public OrderTypeEntity findByName(String name) {
        List results = em.createNamedQuery("OrderType.findByName").setParameter("name", name).getResultList();
        if (!results.isEmpty()) {
            return (OrderTypeEntity)results.get(0);
        }
        return null;
    }
    
}
