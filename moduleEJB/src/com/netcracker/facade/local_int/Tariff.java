package com.netcracker.facade.local_int;



import com.netcracker.entity.CarClassEntity;
import com.netcracker.entity.OrderTypeEntity;
import com.netcracker.entity.TariffEntity;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.List;

@Local
public interface Tariff {
	void create(TariffEntity entity);

	TariffEntity read(Object id);

	void update(TariffEntity entity);

	void delete(TariffEntity entity);

	List<TariffEntity> findAll();

	List<TariffEntity> findRange(int[] range);

	int count();

	void setDayHourlyMultiplier(BigDecimal multiplier);

	void setNightHourlyMultiplier(BigDecimal multiplier);

	void setDayDistanceMultiplier(BigDecimal multiplier);

	void setNightDistanceMultiplier(BigDecimal multiplier);

	void setSmokingFriendlyMultiplier(BigDecimal multiplier);

	void setAnimalFriendlyMultiplier(BigDecimal multiplier);

	void setAirConditionerMultiplier(BigDecimal multiplier);

	void setWifiMultiplier(BigDecimal multiplier);

	void setSexMultiplier(BigDecimal multiplier, String sex);

	void setCarClassMultiplier(BigDecimal multiplier, CarClassEntity carClassEntity);

	void setOrderTypeMultiplier(BigDecimal multiplier, OrderTypeEntity orderTypeEntity);
}
