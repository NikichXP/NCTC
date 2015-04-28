package com.netcracker.facade;

import com.netcracker.entity.FavouriteAddressEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FavouriteAddressEntityFacade extends AbstractFacade<FavouriteAddressEntity> {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavouriteAddressEntityFacade() {
        super(FavouriteAddressEntity.class);
    }
    
}
