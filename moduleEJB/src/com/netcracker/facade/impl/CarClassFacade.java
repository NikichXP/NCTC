package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.CarClassEntity;
import com.netcracker.facade.local_int.CarClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarClassFacade extends AbstractFacade<CarClassEntity> implements CarClass {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarClassFacade() {
        super(CarClassEntity.class);
    }

    @Override
    public CarClassEntity findByName(String name) {
        List results = em.createNamedQuery("CarClass.findByName").setParameter("name", name).getResultList();
        if (!results.isEmpty()) {
            return (CarClassEntity)results.get(0);
        }
        return null;
    }
    
}
