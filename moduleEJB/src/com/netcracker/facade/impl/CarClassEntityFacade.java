package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.CarClassEntity;
import com.netcracker.facade.local_int.CarClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CarClassEntityFacade extends AbstractFacade<CarClassEntity> implements CarClass {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarClassEntityFacade() {
        super(CarClassEntity.class);
    }
    
}
