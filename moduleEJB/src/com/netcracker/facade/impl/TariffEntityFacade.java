package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.TariffEntity;
import com.netcracker.facade.local_int.Tariff;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TariffEntityFacade extends AbstractFacade<TariffEntity> implements Tariff {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TariffEntityFacade() {
        super(TariffEntity.class);
    }
    
}
