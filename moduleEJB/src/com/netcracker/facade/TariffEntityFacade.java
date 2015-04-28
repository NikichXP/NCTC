package com.netcracker.facade;

import com.netcracker.entity.TariffEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TariffEntityFacade extends AbstractFacade<TariffEntity> {
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
