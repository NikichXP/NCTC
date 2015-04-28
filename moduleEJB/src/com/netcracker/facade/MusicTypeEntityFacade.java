package com.netcracker.facade;

import com.netcracker.entity.MusicTypeEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MusicTypeEntityFacade extends AbstractFacade<MusicTypeEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MusicTypeEntityFacade() {
        super(MusicTypeEntity.class);
    }
    
}
