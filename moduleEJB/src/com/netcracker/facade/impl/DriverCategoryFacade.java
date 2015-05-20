package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.DriverCategoryEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DriverCategoryFacade extends AbstractFacade<DriverCategoryEntity> implements com.netcracker.facade.local_int.DriverCategory {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DriverCategoryFacade() {
        super(DriverCategoryEntity.class);
    }

    @Override
    public DriverCategoryEntity findByName(String requiredDriverCategory) {
        List<DriverCategoryEntity> result = em.createNamedQuery("User.findByName")
                .setParameter("name", requiredDriverCategory)
                .getResultList();
        return result.get(0);
    }
}
