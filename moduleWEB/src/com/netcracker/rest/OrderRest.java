package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.OrderStateEntity;
import com.netcracker.entity.PathEntity;
import com.netcracker.facade.local_int.*;
import com.netcracker.rest.utils.SecuritySettings;
import com.netcracker.service.Mail;

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
	@javax.ws.rs.Path("getOrderInProgressByDriverUUID")
	public Response isDriverOfOrder(String driverUuid) {
		OrderStateEntity orderStateEntity = orderState.findByName("in progress");
		List<OrderEntity> orderEntities = order.getOrdersByStateAndDriverUuid(orderStateEntity, driverUuid);
		return (orderEntities.isEmpty() ?
				Response.status(404).entity(false).build() :
				Response.status(201).entity(orderEntities.get(0).getId()).build());
	}

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
		BigDecimal totalLength = new BigDecimal(0);

		for (PathEntity pathEntity : pathEntities) {
			pathEntity.setCompleted(false);
			totalPrice = totalPrice.add(pathEntity.getPrice());
			totalLength = totalLength.add(pathEntity.getLength());
		}
		orderEntity.setFinalPrice(totalPrice);
		orderEntity.setTotalLength(totalLength);
		order.create(orderEntity);

		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString() + "Order id: " + orderEntity.getId()).build();
		}
	}

	@POST
	@javax.ws.rs.Path("updateInProgress")
	@Consumes("application/json")
	public Response updateInProgress(OrderJson orderJson) {
		OrderEntity orderEntity = order.getByDriverUUIDAndID(orderJson.getId(), orderJson.getDriverUserUuid());

        List<PathEntity> pathEntities = new LinkedList<>(orderEntity.getPathEntities());
        pathEntities.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
        for (int i = pathEntities.size() - 1; i >= 0; i--) {
            path.delete(pathEntities.get(i));
        }

        pathEntities = new LinkedList<>();

        PathEntity firstPathEntity = new PathEntity();

        firstPathEntity.setCompleted(false);
        firstPathEntity.setStartAddress(orderJson.getFromAddress());
        firstPathEntity.setStartX(new BigDecimal(orderJson.getFromX()));
        firstPathEntity.setStartY(new BigDecimal(orderJson.getFromY()));

        firstPathEntity.setEndAddress(orderJson.getToAddress()[0]);
        firstPathEntity.setEndX(new BigDecimal(orderJson.getToX()[0]));
        firstPathEntity.setEndY(new BigDecimal(orderJson.getToY()[0]));

        firstPathEntity.setLength(new BigDecimal(orderJson.getDistance()[0]));
        firstPathEntity.setPrice(orderEntity.getTotalMultiplier().multiply(firstPathEntity.getLength()));
        firstPathEntity.setOrderEntity(orderEntity);

        path.create(firstPathEntity);

        pathEntities.add(firstPathEntity);

        for (int i = 0; i < orderJson.getToAddress().length - 1; i++) {
            PathEntity pathEntity = new PathEntity();
            pathEntity.setCompleted(false);
            if (i < Integer.parseInt(orderJson.getPathsCompleted())) {
                pathEntities.get(i).setCompleted(true);
                path.update(pathEntities.get(i));
            }

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

            path.create(pathEntity);

            pathEntities.get(i).setNextPathEntity(pathEntity);

            path.update(pathEntities.get(i));
        }

		orderEntity.setPathEntities(pathEntities);

		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalLength = new BigDecimal(0);

		for (PathEntity pathEntity : pathEntities) {
			totalPrice = totalPrice.add(pathEntity.getPrice());
			totalLength = totalLength.add(pathEntity.getLength());
		}
		orderEntity.setFinalPrice(totalPrice);
		orderEntity.setTotalLength(totalLength);
		order.update(orderEntity);

		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString()).build();
		}
	}

	@POST
	@javax.ws.rs.Path("createWithoutRegistration")
	@Consumes("application/json")
	public Response createOrderWithoutRegistration(OrderJson orderJson) {
		OrderEntity orderEntity = new OrderEntity();

		orderEntity.setCustomerUserEntity(user.findByUuid("344b72d1-5726-4b4f-9f03-78c6b1fdaea7"));
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
		BigDecimal totalLength = new BigDecimal(0);

		for (PathEntity pathEntity : pathEntities) {
			pathEntity.setCompleted(false);
			totalPrice = totalPrice.add(pathEntity.getPrice());
			totalLength = totalLength.add(pathEntity.getLength());
		}
		orderEntity.setFinalPrice(totalPrice);
		orderEntity.setTotalLength(totalLength);
		order.create(orderEntity);
		String msg = "http://localhost:8081/moduleWEB_war_archive/api/order/viewOrderWithoutRegistration?publicToken="
				+ orderEntity.getPublicToken();
		Mail.sendMail(orderJson.getEmail(), "Taxi Service: View order ", msg);
//				"http://178.151.17.247/nctc/api/order/viewOrderWithoutRegistration?publicToken="
//						+ orderEntity.getPublicToken());
		if (orderJson == null) {
			return Response.status(404).entity("OrderJson is null.").build();
		} else {
			return Response.status(201).entity(orderJson.toString() + "Order id: " + orderEntity.getId()).build();
		}
	}
	@GET
	@javax.ws.rs.Path("viewOrderWithoutRegistration")
	public Response viewOrderWithoutRegistration(@QueryParam("publicToken") String publicToken){
		OrderEntity orderEntity = order.getOrderByPublicToken(publicToken);
		return Response.status(200).entity(orderEntity.getContactName() +' '+ orderEntity.getPublicToken()).build();
		//TODO for Alexander )))
//		return Response.status(201).entity("" +
//				"<script>" +
//				"alert('Email confirmed');" +
//				"document.location.href = \"http://localhost:8080/moduleWEB_war_archive/customer.jsp\";" +
//				"</script>").build(); //TODO change to real url
	}
	@GET
	@javax.ws.rs.Path("view")
	public Response viewOrder(@QueryParam("id") String orderId) {
		OrderEntity orderEntity = order.read(new BigInteger(orderId));

		if (orderEntity == null) {
			return Response.status(404).entity("No such order in DB.").build();
		}

		OrderJson orderJson = new OrderJson();

		orderJson.setId(orderEntity.getId().toString());
		orderJson.setContactName(orderEntity.getContactName());
		orderJson.setContactPhone(orderEntity.getContactPhone());
		orderJson.setRequestedSeatsCount(orderEntity.getRequestedSeatsCount().toString());

		orderJson.setType(orderEntity.getOrderTypeEntity().getName());
		orderJson.setState(orderEntity.getOrderStateEntity().getName());

		orderJson.setPublicToken(orderEntity.getPublicToken());
		orderJson.setCustomerUserId(orderEntity.getCustomerUserEntity().getId().toString());
		orderJson.setCustomerUserUuid(orderEntity.getCustomerUserEntity().getUuid());
		orderJson.setDriverUserId(orderEntity.getDriverUserEntity().getId().toString());

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
		List<String> pathIds = new LinkedList<>();
		List<String> isPathCompleted = new LinkedList<>();

		for (PathEntity pathEntity : pathEntities) {
			endAddresses.add(pathEntity.getEndAddress());
			endXs.add(pathEntity.getEndX().toString());
			endYs.add(pathEntity.getEndY().toString());
			distances.add(pathEntity.getLength().toString());
			pathIds.add(pathEntity.getId().toString());
			isPathCompleted.add(String.valueOf(pathEntity.isCompleted()));
		}

		orderJson.setToAddress(endAddresses.toArray(new String[endAddresses.size()]));
		orderJson.setToX(endXs.toArray(new String[endXs.size()]));
		orderJson.setToY(endYs.toArray(new String[endYs.size()]));
		orderJson.setDistance(distances.toArray(new String[distances.size()]));
		orderJson.setPathId(pathIds.toArray(new String[pathIds.size()]));
		orderJson.setPathId(pathIds.toArray(new String[pathIds.size()]));
		orderJson.setPathCompleted(isPathCompleted.toArray(new String[isPathCompleted.size()]));

		orderJson.setSex(orderEntity.getDriverSex());
		orderJson.setCarClass(orderEntity.getCarClassEntity().getName());
		orderJson.setMusicType(orderEntity.getMusicTypeEntity().getName());
		orderJson.setSmokingFriendly(orderEntity.getSmokingFriendly());
		orderJson.setAnimalFriendly(orderEntity.getAnimalFriendly());
		orderJson.setWifi(orderEntity.getWifi());
		orderJson.setAirConditioner(orderEntity.getAirConditioner());

		orderJson.setCustomerPreCreateComment(orderEntity.getCustomerPreCreateComment());
		if(orderEntity.getCustomerPostCompleteComment() != null) {
			orderJson.setCustomerPostCompleteComment(orderEntity.getCustomerPostCompleteComment());
		} else orderJson.setCustomerPostCompleteComment("");
		if(orderEntity.getRefuseCauseByCustomerEntity() != null) {
			orderJson.setCustomerRefuseCause(orderEntity.getRefuseCauseByCustomerEntity().getMessage());
		} else orderJson.setCustomerRefuseCause("");
		if(orderEntity.getRefuseCauseByDriverEntity() != null) {
			orderJson.setDriverRefuseCause(orderEntity.getRefuseCauseByDriverEntity().getMessage());
		} else orderJson.setDriverRefuseCause("");
		if(orderEntity.getCustomerRefuseComment() != null) {
			orderJson.setCustomerRefuseComment(orderEntity.getCustomerRefuseComment());
		} else orderJson.setCustomerRefuseComment("");
		if(orderEntity.getDriverRefuseComment() != null) {
			orderJson.setDriverRefuseComment(orderEntity.getDriverRefuseComment());
		} else orderJson.setDriverRefuseComment("");

		if(orderEntity.getTotalLength() != null && orderEntity.getTotalMultiplier() != null) {
			orderJson.setTotalLength(orderEntity.getTotalLength().toString());
			orderJson.setTotalMultiplier(orderEntity.getTotalMultiplier().toString());
			orderJson.setTotalPrice(orderEntity.getTotalMultiplier().multiply(orderEntity.getTotalLength()).toString());
		} else {
			orderJson.setTotalLength("");
			orderJson.setTotalMultiplier("");
			orderJson.setTotalPrice("");
		}

		return Response.status(201).entity(orderJson.toString()).build();
	}

	@GET
	@javax.ws.rs.Path("viewQueuedOrder")
	public Response viewQueuedOrders(@QueryParam("id") String orderId) {
		OrderEntity orderEntity = order.read(new BigInteger(orderId));

		if (orderEntity == null) {
			return Response.status(404).entity("No such order in DB.").build();
		}

		OrderJson orderJson = new OrderJson();

		orderJson.setId(orderEntity.getId().toString());
		orderJson.setContactName(orderEntity.getContactName());
		orderJson.setContactPhone(orderEntity.getContactPhone());
		orderJson.setRequestedSeatsCount(orderEntity.getRequestedSeatsCount().toString());

		orderJson.setType(orderEntity.getOrderTypeEntity().getName());
		orderJson.setState(orderEntity.getOrderStateEntity().getName());

		orderJson.setPublicToken(orderEntity.getPublicToken());
		orderJson.setCustomerUserId(orderEntity.getCustomerUserEntity().getId().toString());
		orderJson.setCustomerUserUuid(orderEntity.getCustomerUserEntity().getUuid());
		//orderJson.setDriverUserId(orderEntity.getDriverUserEntity().getId().toString());

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
		List<String> pathIds = new LinkedList<>();
		List<String> isPathCompleted = new LinkedList<>();

		for (PathEntity pathEntity : pathEntities) {
			endAddresses.add(pathEntity.getEndAddress());
			endXs.add(pathEntity.getEndX().toString());
			endYs.add(pathEntity.getEndY().toString());
			distances.add(pathEntity.getLength().toString());
			pathIds.add(pathEntity.getId().toString());
			isPathCompleted.add(String.valueOf(pathEntity.isCompleted()));
		}

		orderJson.setToAddress(endAddresses.toArray(new String[endAddresses.size()]));
		orderJson.setToX(endXs.toArray(new String[endXs.size()]));
		orderJson.setToY(endYs.toArray(new String[endYs.size()]));
		orderJson.setDistance(distances.toArray(new String[distances.size()]));
		orderJson.setPathId(pathIds.toArray(new String[pathIds.size()]));
		orderJson.setPathId(pathIds.toArray(new String[pathIds.size()]));
		orderJson.setPathCompleted(isPathCompleted.toArray(new String[isPathCompleted.size()]));

		orderJson.setSex(orderEntity.getDriverSex());
		orderJson.setCarClass(orderEntity.getCarClassEntity().getName());
		orderJson.setMusicType(orderEntity.getMusicTypeEntity().getName());
		orderJson.setSmokingFriendly(orderEntity.getSmokingFriendly());
		orderJson.setAnimalFriendly(orderEntity.getAnimalFriendly());
		orderJson.setWifi(orderEntity.getWifi());
		orderJson.setAirConditioner(orderEntity.getAirConditioner());

		orderJson.setCustomerPreCreateComment(orderEntity.getCustomerPreCreateComment());
		if(orderEntity.getCustomerPostCompleteComment() != null) {
			orderJson.setCustomerPostCompleteComment(orderEntity.getCustomerPostCompleteComment());
		} else orderJson.setCustomerPostCompleteComment("");
		if(orderEntity.getRefuseCauseByCustomerEntity() != null) {
			orderJson.setCustomerRefuseCause(orderEntity.getRefuseCauseByCustomerEntity().getMessage());
		} else orderJson.setCustomerRefuseCause("");
		if(orderEntity.getRefuseCauseByDriverEntity() != null) {
			orderJson.setDriverRefuseCause(orderEntity.getRefuseCauseByDriverEntity().getMessage());
		} else orderJson.setDriverRefuseCause("");
		if(orderEntity.getCustomerRefuseComment() != null) {
			orderJson.setCustomerRefuseComment(orderEntity.getCustomerRefuseComment());
		} else orderJson.setCustomerRefuseComment("");
		if(orderEntity.getDriverRefuseComment() != null) {
			orderJson.setDriverRefuseComment(orderEntity.getDriverRefuseComment());
		} else orderJson.setDriverRefuseComment("");

		if(orderEntity.getTotalLength() != null && orderEntity.getTotalMultiplier() != null) {
			orderJson.setTotalLength(orderEntity.getTotalLength().toString());
			orderJson.setTotalMultiplier(orderEntity.getTotalMultiplier().toString());
			orderJson.setTotalPrice(orderEntity.getTotalMultiplier().multiply(orderEntity.getTotalLength()).toString());
		} else {
			orderJson.setTotalLength("");
			orderJson.setTotalMultiplier("");
			orderJson.setTotalPrice("");
		}

		return Response.status(201).entity(orderJson.toString()).build();
	}
}