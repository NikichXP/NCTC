package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.PathEntity;
import com.netcracker.facade.local_int.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
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

@javax.ws.rs.Path("order")
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

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	@POST
	@javax.ws.rs.Path("create")
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
						simpleDateFormat.parse(orderJson.getTimeRequested()
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

	@GET
	@javax.ws.rs.Path("view")
	public Response viewOrder(@QueryParam("id") String orderId) {
		OrderEntity orderEntity = order.read(new BigInteger(orderId));
		OrderJson orderJson = new OrderJson();

		orderJson.setId(orderEntity.getId().toString());
		orderJson.setContactName(orderEntity.getContactName());
		orderJson.setContactPhone(orderEntity.getContactPhone());

		orderJson.setType(orderEntity.getOrderTypeEntity().getName());
		orderJson.setState(orderEntity.getOrderStateEntity().getName());

		orderJson.setTimeCreated(
				simpleDateFormat.format(
						new Date(orderEntity.getTimeCreated().getTime())));
		orderJson.setTimeRequested(
				simpleDateFormat.format(
						new Date(orderEntity.getTimeRequested().getTime())));
		if (orderEntity.getTimeOfDriverArrival() != null) {
			orderJson.setTimeOfDriverArrival(
					simpleDateFormat.format(
							new Date(orderEntity.getTimeOfDriverArrival().getTime())));
		}
		if (orderEntity.getTimeStarted() != null) {
			orderJson.setTimeStarted(
					simpleDateFormat.format(
							new Date(orderEntity.getTimeStarted().getTime())));
		}
		if (orderEntity.getTimeCompleted() != null) {
			orderJson.setTimeCompleted(
					simpleDateFormat.format(
							new Date(orderEntity.getTimeCompleted().getTime())));
		}

		List<PathEntity> pathEntities = new LinkedList<>(orderEntity.getPathEntities());
		pathEntities.sort(new Comparator<PathEntity>() {
			@Override
			public int compare(PathEntity o1, PathEntity o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		orderJson.setFromAddress(pathEntities.get(0).getStartAddress());
		orderJson.setFromX(pathEntities.get(0).getStartX().toString());
		orderJson.setFromY(pathEntities.get(0).getStartY().toString());

		List<String> endAddresses = new LinkedList<>();
		List<String> endXs = new LinkedList<>();
		List<String> endYs = new LinkedList<>();
		List<String> distances = new LinkedList<>();

		for (PathEntity pathEntity : pathEntities) {
			endAddresses.add(pathEntity.getEndAddress());
			endXs.add(pathEntity.getEndX().toString());
			endYs.add(pathEntity.getEndY().toString());
			distances.add(pathEntity.getLength().toString());
		}

		orderJson.setSex(orderEntity.getDriverSex());
		orderJson.setCarClass(orderEntity.getCarClassEntity().getName());
		orderJson.setMusicType(orderEntity.getMusicTypeEntity().getName());
		orderJson.setSmokingFriendly(orderEntity.getSmokingFriendly());
		orderJson.setAnimalFriendly(orderEntity.getAnimalFriendly());
		orderJson.setWifi(orderEntity.getWifi());
		orderJson.setAirConditioner(orderEntity.getAirConditioner());

		orderJson.setCustomerPreCreateComment(orderEntity.getCustomerPreCreateComment());
		orderJson.setCustomerPostCompleteComment(orderEntity.getCustomerPostCompleteComment());
		if(orderEntity.getRefuseCauseByCustomerEntity() != null)
			orderJson.setCustomerRefuseCause(orderEntity.getRefuseCauseByCustomerEntity().getMessage());
		if(orderEntity.getRefuseCauseByDriverEntity() != null)
		orderJson.setDriverRefuseCause(orderEntity.getRefuseCauseByDriverEntity().getMessage());
		orderJson.setCustomerRefuseComment(orderEntity.getCustomerRefuseComment());
		orderJson.setDriverRefuseComment(orderEntity.getDriverRefuseComment());

		if (orderEntity.getTotalLength() != null) {
			orderJson.setTotalLength(orderEntity.getTotalLength().toString());
			orderJson.setTotalMultiplier(orderEntity.getTotalMultiplier().toString());
			orderJson.setTotalPrice(orderEntity.getTotalMultiplier().multiply(orderEntity.getTotalLength()).toString());
		}

		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString()).build();
		}
	}

	@POST
	@javax.ws.rs.Path("edit")
	@Consumes("application/json")
	public Response editOrder(OrderJson orderJson) {
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
						simpleDateFormat.parse(orderJson.getTimeRequested()
						).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			orderEntity.setTimeRequested(new Timestamp(new Date().getTime()));
		}

		List<PathEntity> pathEntities = new ArrayList<>(orderEntity.getPathEntities());
		for (PathEntity pathE : pathEntities) {
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
