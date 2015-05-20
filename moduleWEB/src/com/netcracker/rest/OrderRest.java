package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.PathEntity;
import com.netcracker.facade.local_int.*;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Juger on 05.05.2015.
 */

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

		orderEntity.setTotalMultiplier(new BigDecimal(orderJson.getTotalMultiplier()));

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

		firstPathEntity.setStartAddress(orderJson.getFromAddress());
		firstPathEntity.setStartX(new BigDecimal(orderJson.getFromX()));
		firstPathEntity.setStartY(new BigDecimal(orderJson.getFromY()));

		firstPathEntity.setEndAddress(orderJson.getToAddress()[0]);
		firstPathEntity.setEndX(new BigDecimal(orderJson.getToX()[0]));
		firstPathEntity.setEndY(new BigDecimal(orderJson.getToY()[0]));

		firstPathEntity.setLength(new BigDecimal(orderJson.getDistance()[0]));
		firstPathEntity.setPrice(orderEntity.getTotalMultiplier().multiply(firstPathEntity.getLength()));
		firstPathEntity.setOrderEntity(orderEntity);

		pathEntities.add(firstPathEntity);

		for (int i = 0; i < orderJson.getToAddress().length - 1; i++) {
			PathEntity pathEntity = new PathEntity();
			pathEntity.setStartAddress(orderJson.getToAddress()[i]);
			pathEntity.setStartX(new BigDecimal(orderJson.getToX()[i]));
			pathEntity.setStartY(new BigDecimal(orderJson.getToY()[i]));

			pathEntity.setEndAddress(orderJson.getToAddress()[i + 1]);
			pathEntity.setEndX(new BigDecimal(orderJson.getToX()[i + 1]));
			pathEntity.setEndY(new BigDecimal(orderJson.getToY()[i + 1]));

			pathEntity.setLength(new BigDecimal(orderJson.getDistance()[i + 1]));
			pathEntity.setPrice(orderEntity.getTotalMultiplier().multiply(pathEntity.getLength()));
			pathEntity.setOrderEntity(orderEntity);

			pathEntities.add(pathEntity);

			pathEntities.get(i).setNextPathEntity(pathEntity);
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

		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalLength = new BigDecimal(orderJson.getTotalLength());

		for (PathEntity pathEntity : pathEntities) {
			pathEntity.setCompleted(false);
			totalPrice = totalPrice.add(pathEntity.getPrice());
			totalLength = totalLength.add(pathEntity.getPrice());
		}
		orderEntity.setFinalPrice(totalPrice);
		order.create(orderEntity);

		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString() + "Order id: " + orderEntity.getId()).build();
		}

	}

	@POST
	@Path("edite")
	@Consumes("application/json")
	public Response editOrder(OrderJson orderJson){
		OrderEntity orderEntity = order.read(new BigInteger(orderJson.getId()));
		orderEntity.setCustomerUserEntity(user.findByUuid(orderJson.getCustomerUserUuid()));
		orderEntity.setPublicToken(UUID.randomUUID().toString().substring(0, 8));
		orderEntity.setContactName(orderJson.getContactName());
		orderEntity.setContactPhone(orderJson.getContactPhone().replace("+", "").replace(" ", ""));
		orderEntity.setRequestedSeatsCount(new BigInteger(orderJson.getRequestedSeatsCount()));

		orderEntity.setOrderTypeEntity(orderType.findByName("basic"));
		orderEntity.setOrderStateEntity(orderState.findByName("queued"));

		orderEntity.setTotalMultiplier(new BigDecimal(orderJson.getTotalMultiplier()));

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

		List<PathEntity> pathEntities = path.getByOrderEntitie(orderEntity);
		for (PathEntity pathE: pathEntities){
			path.delete(pathE);
		}

		PathEntity firstPathEntity = new PathEntity();

		firstPathEntity.setStartAddress(orderJson.getFromAddress());
		firstPathEntity.setStartX(new BigDecimal(orderJson.getFromX()));
		firstPathEntity.setStartY(new BigDecimal(orderJson.getFromY()));

		firstPathEntity.setEndAddress(orderJson.getToAddress()[0]);
		firstPathEntity.setEndX(new BigDecimal(orderJson.getToX()[0]));
		firstPathEntity.setEndY(new BigDecimal(orderJson.getToY()[0]));

		firstPathEntity.setLength(new BigDecimal(orderJson.getDistance()[0]));
		firstPathEntity.setPrice(orderEntity.getTotalMultiplier().multiply(firstPathEntity.getLength()));
		firstPathEntity.setOrderEntity(orderEntity);

		for (int i = 0; i < orderJson.getToAddress().length - 1; i++) {
			PathEntity pathEntity = new PathEntity();
			pathEntity.setStartAddress(orderJson.getToAddress()[i]);
			pathEntity.setStartX(new BigDecimal(orderJson.getToX()[i]));
			pathEntity.setStartY(new BigDecimal(orderJson.getToY()[i]));

			pathEntity.setEndAddress(orderJson.getToAddress()[i + 1]);
			pathEntity.setEndX(new BigDecimal(orderJson.getToX()[i + 1]));
			pathEntity.setEndY(new BigDecimal(orderJson.getToY()[i + 1]));

			pathEntity.setLength(new BigDecimal(orderJson.getDistance()[i + 1]));
			pathEntity.setPrice(orderEntity.getTotalMultiplier().multiply(pathEntity.getLength()));
			pathEntity.setOrderEntity(orderEntity);

			pathEntities.add(pathEntity);

			pathEntities.get(i).setNextPathEntity(pathEntity);
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

		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalLength = new BigDecimal(orderJson.getTotalLength());

		for (PathEntity pathEntity : pathEntities) {
			pathEntity.setCompleted(false);
			totalPrice = totalPrice.add(pathEntity.getPrice());
			totalLength = totalLength.add(pathEntity.getPrice());
		}
		orderEntity.setFinalPrice(totalPrice);
		order.update(orderEntity);

		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString() + "Order id: " + orderEntity.getId() + " updated").build();
		}

	}


}
