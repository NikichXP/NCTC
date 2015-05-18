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

    /**
     * @param email user email
     * @return <b>true</b> if user with this email already exists
     */
    @Override
    public boolean isEmailUsed(String email) {
        List results = em.createNamedQuery("User.findByEmailIgnoreCase").setParameter("email", email).getResultList();
        return !results.isEmpty();
    }

    /**
     * @param phone user phone number
     * @return <b>true</b> if user with this phone number already exists
     */
    @Override
    public boolean isPhoneUsed(String phone) {
        List results = em.createNamedQuery("User.findByPhone").setParameter("phone", phone).getResultList();
        return !results.isEmpty();
    }

    @Override
    public List<UserEntity> getDrivers() {
        return em.createNamedQuery("User.getDrivers").getResultList();
    }

    /**
     * @param email unique user email
     * @return <b>UserEntity</b> object or <b>null</b> if not found
     */
    @Override
    public UserEntity findByEmail(String email) {
        List results = em.createNamedQuery("User.findByEmailIgnoreCase").setParameter("email", email).getResultList();
        if (!results.isEmpty()) {
            return (UserEntity)results.get(0);
        }
        return null;
    }

    /**
     * @param phone unique user phone
     * @return <b>UserEntity</b> object or <b>null</b> if not found
     */
    @Override
    public UserEntity findByPhone(String phone) {
        List results = em.createNamedQuery("User.findByPhone").setParameter("phone", phone).getResultList();
        if (!results.isEmpty()) {
            return (UserEntity)results.get(0);
        }
        return null;
    }

    /**
     * @param uuid
     * @return <b>UserEntity</b> object or <b>null</b> if not found
     */
    @Override
    public UserEntity findByUuid(String uuid) {
        List results = em.createNamedQuery("User.findByUuid").setParameter("uuid", uuid).getResultList();
        if (!results.isEmpty()) {
            return (UserEntity)results.get(0);
        }
        return null;
    }

    /**
     * @param email unique user email
     * @param password user password
     * @return <b>UserEntity object</b> or <b>null</b> if not found
     */
    @Override
    public UserEntity loginByEmail(String email, String password) {
        List results = em.createNamedQuery("User.findByEmailIgnoreCaseAndPassword")
                .setParameter("email", email).setParameter("password", password).getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return (UserEntity)results.get(0);
    }

    /**
     * @param phone unique user phone
     * @param password user password
     * @return <b>UserEntity</b> object or <b>null</b> if not found
     */
    @Override
    public UserEntity loginByPhone(String phone, String password) {
        List results = em.createNamedQuery("User.findByPhoneAndPassword")
                .setParameter("phone", phone).setParameter("password", password).getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return (UserEntity)results.get(0);
    }
}
