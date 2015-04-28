package com.netcracker.facade;

import com.netcracker.entity.CarEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CarEntityFacade extends AbstractFacade<CarEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarEntityFacade() {
        super(CarEntity.class);
    }
    
}
