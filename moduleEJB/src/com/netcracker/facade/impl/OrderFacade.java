package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.OrderStateEntity;
import com.netcracker.facade.local_int.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderFacade extends AbstractFacade<OrderEntity> implements Order {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderFacade() {
        super(OrderEntity.class);
    }

    public List<OrderEntity> getOrdersByStateAndCustomerUuid(OrderStateEntity orderStateEntity, String customerUuid) {
        List results = em.createNamedQuery("Order.getOrdersByStateAndCustomerUuid")
                .setParameter("customerUuid", customerUuid)
                .setParameter("orderStateEntity", orderStateEntity)
                .getResultList();
        return results;
    }

}
