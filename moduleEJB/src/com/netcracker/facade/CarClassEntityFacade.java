package com.netcracker.facade;

import com.netcracker.entity.CarClassEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CarClassEntityFacade extends AbstractFacade<CarClassEntity> {
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
