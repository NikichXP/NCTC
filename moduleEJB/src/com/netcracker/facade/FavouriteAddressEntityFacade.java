package com.netcracker.facade;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.FavouriteAddressEntity;
import com.netcracker.facade.local_int.FavouriteAddress;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FavouriteAddressEntityFacade extends AbstractFacade<FavouriteAddressEntity> implements FavouriteAddress {
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
