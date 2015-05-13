package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.entity.*;
import com.netcracker.facade.local_int.*;
import com.netcracker.rest.utils.SecuritySettings;
import com.netcracker.service.Mail;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Juger on 05.05.2015.
 */

@Stateless
@Path("order")
public class OrderRest {
	@EJB
	private User user;
	@EJB
	private Order order;
	@EJB
	private MusicType musicType;
	@EJB
	private OrderState orderState;
	@EJB
	private OrderType orderType;
	@EJB
	private CarClass carClass;
	@EJB
	private com.netcracker.facade.local_int.Path path;

	@POST
	@Path("create")
	@Consumes("application/json")
	public Response createOrder(OrderJson orderJson) {
		OrderEntity orderEntity = new OrderEntity();

		orderEntity.setCustomerUserEntity(user.findByUuid(orderJson.getCustomerUserUuid()));
		orderEntity.setPublicToken(UUID.randomUUID().toString().substring(0, 8));
		orderEntity.setContactName(orderJson.getContactName());
		orderEntity.setContactPhone(orderJson.getContactPhone().replace("+", "").replace(" ", ""));
		orderEntity.setRequestedSeatsCount(new BigInteger(orderJson.getRequestedSeatsCount()));

		orderEntity.setOrderTypeEntity(orderType.findByName("basic"));
		orderEntity.setOrderStateEntity(orderState.findByName("queued"));

		orderEntity.setTimeCreated(new Timestamp(new Date().getTime()));
		if (!orderJson.isAsSoonAsPossible()) {
			try {
				orderEntity.setTimeRequested(new Timestamp(
						new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(orderJson.getTimeRequested()
						).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			orderEntity.setTimeRequested(new Timestamp(new Date().getTime()));
		}

		List<PathEntity> pathEntities = new LinkedList<>();

		PathEntity firstPathEntity = new PathEntity();
		firstPathEntity.setLength(new BigInteger("999"));//TODO Replace with real Length
		firstPathEntity.setPrice(new BigInteger("99999"));//TODO Replace with real Price

		firstPathEntity.setStartAddress(orderJson.getFromAddress());
		firstPathEntity.setStartX(new BigInteger(orderJson.getFromX()));
		firstPathEntity.setStartY(new BigInteger(orderJson.getFromY()));

		firstPathEntity.setEndAddress(orderJson.getToAddress()[0]);
		firstPathEntity.setEndX(new BigInteger(orderJson.getToX()[0]));
		firstPathEntity.setEndY(new BigInteger(orderJson.getToY()[0]));
		firstPathEntity.setOrderEntity(orderEntity);

		pathEntities.add(firstPathEntity);

		for (int i = 1; i < orderJson.getToAddress().length - 1; i++) {
			PathEntity pathEntity = new PathEntity();
			pathEntity.setLength(new BigInteger("999"));//TODO Replace with real Length
			pathEntity.setPrice(new BigInteger("99999"));//TODO Replace with real Price

			pathEntity.setStartAddress(orderJson.getToAddress()[i]);
			pathEntity.setStartX(new BigInteger(orderJson.getToX()[i]));
			pathEntity.setStartY(new BigInteger(orderJson.getToY()[i]));

			pathEntity.setEndAddress(orderJson.getToAddress()[i + 1]);
			pathEntity.setEndX(new BigInteger(orderJson.getToX()[i + 1]));
			pathEntity.setEndY(new BigInteger(orderJson.getToY()[i + 1]));

			pathEntities.add(pathEntity);
			pathEntities.get(i - 1).setPathByNextPathId(pathEntity);
		}

		orderEntity.setPathEntities(pathEntities);
		orderEntity.setDriverSex(orderJson.getSex());
		orderEntity.setCarClassEntity(carClass.findByName(orderJson.getCarClass()));
		orderEntity.setMusicTypeEntity(musicType.findByName(orderJson.getMusicType()));
		orderEntity.setSmokingFriendly(orderJson.isSmokingFriendly());
		orderEntity.setAnimalFriendly(orderJson.isAnimalFriendly());
		orderEntity.setWifi(orderJson.isWifi());
		orderEntity.setAirConditioner(orderJson.isAirConditioner());

		orderEntity.setCustomerPreCreateComment(orderJson.getCustomerPreCreateComment());

		BigInteger totalPrice = new BigInteger("0");
		BigInteger totalLength = new BigInteger("0");
		for (PathEntity pathEntity : pathEntities) {
			pathEntity.setCompleted(false);
			totalPrice = totalPrice.add(new BigInteger(pathEntity.getPrice() + ""));
			totalLength = totalLength.add(new BigInteger(pathEntity.getLength() + ""));
		}
		orderEntity.setFinalPrice(totalPrice);
		orderEntity.setTotalMultiplier(new BigInteger("1"));//TODO Replace with real multiplier
		order.create(orderEntity);

		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString() + "Order id: " + orderEntity.getId()).build();
		}

	}
}
