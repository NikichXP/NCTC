package com.netcracker.classes;

import com.netcracker.entity.CarEntity;
import com.netcracker.entity.UserEntity;

public class JSON {

	public CarJson getUserJson(CarEntity carEntity) {
		CarJson json = new CarJson();
		json.setId(carEntity.getId().toString());
		json.setModel(carEntity.getModel());
		json.setLicencePlate(carEntity.getLicencePlate());
		json.setClassId(carEntity.getCarClassEntity().getName());
		json.setAirConditioner(carEntity.getAirCondition().toString());
		json.setRequiredDriverCategory(carEntity.getDriverCategoryEntity().getName());
		json.setSeatCount(carEntity.getSeatsCount().toString());
		//json.setUserId(carEntity.getUserEntity().getId().toString());
		return json;
	}

	public UserJson getUserJson(UserEntity userEntity) {
		UserJson json = new UserJson();
		json.setFirstName(userEntity.getFirstName());
		json.setLastName(userEntity.getLastName());
		json.setPhone(userEntity.getPhone());
		json.setAlternativePhone(userEntity.getAlternativePhone());
		json.setId(userEntity.getId().toString());
		json.setEmail(userEntity.getEmail());
		json.setPass(userEntity.getPassword());
//        json.setUserSex(userEntity.getSex());
//        json.setAnimalFriendly(userEntity.getAnimalFriendly().toString());
//        json.setSmokingFriendly(userEntity.getSmokingFriendly().toString());
		json.setUuid(userEntity.getUuid());
		return json;
	}

}
