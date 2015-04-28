package com.netcracker.facade;

import com.netcracker.entity.PathEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PathEntityFacade extends AbstractFacade<PathEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PathEntityFacade() {
        super(PathEntity.class);
    }
    
}
