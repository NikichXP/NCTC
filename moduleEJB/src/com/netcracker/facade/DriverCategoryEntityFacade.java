package com.netcracker.facade;

import com.netcracker.entity.DriverCategoryEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DriverCategoryEntityFacade extends AbstractFacade<DriverCategoryEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DriverCategoryEntityFacade() {
        super(DriverCategoryEntity.class);
    }
    
}
