package com.netcracker.facade.impl;

 /* 18:42 28.04.2015 by Viktor Taranenko */

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserFacade extends AbstractFacade<UserEntity> implements User {
    @PersistenceContext(unitName = "TaxiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(UserEntity.class);
    }

    @Override
    public boolean isEmailUsed(String email) {
        List results = em.createNamedQuery("User.findByEmailIgnoreCase").setParameter("email", email).getResultList();
        return !results.isEmpty();
    }

    @Override
    public UserEntity loginByEmail(String email, String password) {
        List results = em.createNamedQuery("User.findByEmailIgnoreCaseAndPassword")
                .setParameter("email", email).setParameter("password", password).getResultList();
        System.out.println(results.size());

        if (results.isEmpty()) {
            System.out.println("Returned null");
            return null;
        }
        return (UserEntity)results.get(0);
    }


    @Override
    public UserEntity loginByPhone(String phone, String password) {
        List results = em.createNamedQuery("User.findByPhoneAndPassword")
                .setParameter("phone", phone).setParameter("password", password).getResultList();
        System.out.println(results.size());

        if (results.isEmpty()) {
            System.out.println("Returned null");
            return null;
        }
        return (UserEntity)results.get(0);
    }
}
