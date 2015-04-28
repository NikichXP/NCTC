package com.netcracker.facade;

import com.netcracker.entity.UserGroupEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserGroupEntityFacade extends AbstractFacade<UserGroupEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserGroupEntityFacade() {
        super(UserGroupEntity.class);
    }
    
}
