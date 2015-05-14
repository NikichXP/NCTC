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
import java.math.BigDecimal;
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
		firstPathEntity.setLength(new BigDecimal(orderJson.getTotalLength()));
		firstPathEntity.setPrice(new BigDecimal("999.99"));//TODO Replace with real Price

		firstPathEntity.setStartAddress(orderJson.getFromAddress());
		firstPathEntity.setStartX(new BigDecimal(orderJson.getFromX()));
		firstPathEntity.setStartY(new BigDecimal(orderJson.getFromY()));

		firstPathEntity.setEndAddress(orderJson.getToAddress()[0]);
		firstPathEntity.setEndX(new BigDecimal(orderJson.getToX()[0]));
		firstPathEntity.setEndY(new BigDecimal(orderJson.getToY()[0]));
		firstPathEntity.setOrderEntity(orderEntity);

		pathEntities.add(firstPathEntity);

		for (int i = 0; i < orderJson.getToAddress().length - 1; i++) {
			PathEntity pathEntity = new PathEntity();
			pathEntity.setLength(new BigDecimal(orderJson.getTotalLength()));
			pathEntity.setPrice(new BigDecimal("999.99"));//TODO Replace with real Price

			pathEntity.setStartAddress(orderJson.getToAddress()[i]);
			pathEntity.setStartX(new BigDecimal(orderJson.getToX()[i]));
			pathEntity.setStartY(new BigDecimal(orderJson.getToY()[i]));

			pathEntity.setEndAddress(orderJson.getToAddress()[i + 1]);
			pathEntity.setEndX(new BigDecimal(orderJson.getToX()[i + 1]));
			pathEntity.setEndY(new BigDecimal(orderJson.getToY()[i + 1]));
			pathEntity.setOrderEntity(orderEntity);

			pathEntities.add(pathEntity);

			pathEntities.get(i).setPathByNextPathId(pathEntity);
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

		BigDecimal totalPrice = new BigDecimal("0");
		BigDecimal totalLength = new BigDecimal("0");
		for (PathEntity pathEntity : pathEntities) {
			pathEntity.setCompleted(false);
			totalPrice = totalPrice.add(new BigDecimal(pathEntity.getPrice() + ""));
			totalLength = totalLength.add(new BigDecimal(pathEntity.getLength() + ""));
		}
		orderEntity.setFinalPrice(totalPrice);
		orderEntity.setTotalMultiplier(new BigDecimal("1"));//TODO Replace with real multiplier
		order.create(orderEntity);

		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString() + "Order id: " + orderEntity.getId()).build();
		}

	}
}
