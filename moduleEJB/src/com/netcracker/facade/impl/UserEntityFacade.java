package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserEntityFacade extends AbstractFacade<UserEntity> implements User {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserEntityFacade() {
        super(UserEntity.class);
    }
    
}
