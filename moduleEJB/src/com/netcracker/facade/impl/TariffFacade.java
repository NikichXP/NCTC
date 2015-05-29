package com.netcracker.facade.impl;



import com.netcracker.entity.CarClassEntity;
import com.netcracker.entity.OrderTypeEntity;
import com.netcracker.entity.TariffEntity;
import com.netcracker.facade.local_int.Tariff;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Stateless
public class TariffFacade extends AbstractFacade<TariffEntity> implements Tariff {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TariffFacade() {
		super(TariffEntity.class);
	}

	@Override
	public void setDayHourlyMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setDayHourlyMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();

	}

	@Override
	public void setNightHourlyMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setNightHourlyMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();

	}

	@Override
	public void setDayDistanceMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setDayDistanceMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();

	}

	@Override
	public void setNightDistanceMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setNightDistanceMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();
	}

	@Override
	public void setSmokingFriendlyMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setSmokingFriendlyMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();
	}

	@Override
	public void setAnimalFriendlyMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setAnimalFriendlyMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();
	}

	@Override
	public void setAirConditionerMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setAirConditionerMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();
	}

	@Override
	public void setWifiMultiplier(BigDecimal multiplier) {
		em.createNamedQuery("Tariff.setWifiMultiplier")
				.setParameter("multiplier", multiplier).executeUpdate();
	}

	@Override
	public void setSexMultiplier(BigDecimal multiplier, String sex) {
		em.createNamedQuery("Tariff.setSexMultiplier")
				.setParameter("multiplier", multiplier)
				.setParameter("sex", sex).executeUpdate();
	}

	@Override
	public void setCarClassMultiplier(BigDecimal multiplier, CarClassEntity carClassEntity) {
		em.createNamedQuery("Tariff.setCarClassMultiplier")
				.setParameter("multiplier", multiplier)
				.setParameter("carClassEntity", carClassEntity).executeUpdate();
	}

	@Override
	public void setOrderTypeMultiplier(BigDecimal multiplier, OrderTypeEntity orderTypeEntity) {
		em.createNamedQuery("Tariff.setOrderTypeMultiplier")
				.setParameter("multiplier", multiplier)
				.setParameter("orderTypeEntity", orderTypeEntity).executeUpdate();
	}

}
