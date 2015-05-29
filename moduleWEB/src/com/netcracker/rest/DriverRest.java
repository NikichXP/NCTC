package com.netcracker.rest;

import com.netcracker.classes.OrderJson;
import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.OrderStateEntity;
import com.netcracker.facade.local_int.Order;
import com.netcracker.facade.local_int.OrderState;
import com.netcracker.facade.local_int.User;
import com.netcracker.service.Mail;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Path("driver")
public class DriverRest {

	@EJB
	private Order order;

	@EJB
	private OrderState orderState;

	@EJB
	private User user;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private static String url = "http://178.151.17.247/nctc/";

	@POST
	@Path("getQueuedOrders")
	public Response getQueuedOrders() {
		List<OrderEntity> list = order.getOrdersByState(orderState.findByName("queued"));
		Collections.sort(list, (o1, o2) -> o2.getTimeCreated().toString().compareTo(o1.getTimeCreated().toString()));
		StringBuilder sb = new StringBuilder();
		sb.append("{\"orders\":[");
		for (OrderEntity orderEntity : list) {
			List<Point> points = order.getFirstAndLastPoints(orderEntity);
			sb.append("{\"startOrder\":\"")
					.append(points.get(0).getAddress())
					.append("\",\"endOrder\":\"")
					.append(points.get(1).getAddress())
					.append("\",\"dateOrderCreate\":\"")
					.append(orderEntity.getTimeCreated().toString())
					.append("\",\"id\":\"")
					.append(orderEntity.getId())
					.append("\",\"distance\":\"")
					.append(orderEntity.getTotalLength())
					.append("\",\"price\":\"")
					.append(orderEntity.getFinalPrice())
					.append("\" },");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]}");
		if (!list.isEmpty()) {
			return Response.status(200).entity(sb.toString()).build();
		} else {
			return Response.status(404).entity("Bad response.").build();
		}
	}

	@POST
	@Path("getAssignedOrders")
	@Consumes("text/plain")
	public Response getAssignedOrders(String uuid) {
		List<OrderEntity> list = order.getOrdersByStateAndDriverUuid(orderState.findByName("assigned"), uuid);
		Collections.sort(list, (o1, o2) -> o2.getTimeCreated().toString().compareTo(o1.getTimeCreated().toString()));
		StringBuilder sb = new StringBuilder();
		sb.append("{\"orders\":[");
		for (OrderEntity orderEntity : list) {
			List<Point> points = order.getFirstAndLastPoints(orderEntity);
			sb.append("{\"startOrder\":\"")
					.append(points.get(0).getAddress())
					.append("\",\"endOrder\":\"")
					.append(points.get(1).getAddress())
					.append("\",\"dateOrderCreate\":\"")
					.append(orderEntity.getTimeCreated().toString())
					.append("\",\"id\":\"")
					.append(orderEntity.getId())
					.append("\",\"distance\":\"")
					.append(orderEntity.getTotalLength())
					.append("\",\"price\":\"")
					.append(orderEntity.getFinalPrice())
					.append("\" },");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]}");
		if (!list.isEmpty()) {
			return Response.status(200).entity(sb.toString()).build();
		} else {
			return Response.status(204).entity("Bad response.").build();
		}
	}

	@POST
	@Path("historyByLength")
	@Consumes("text/plain")
	public Response getOrderHistoryByLength(String uuid) {
		List<OrderEntity> list = order.getOrdersByStateAndDriverUuid(orderState.findByName("completed"), uuid);
		list.addAll(order.getOrdersByStateAndDriverUuid(orderState.findByName("refused"), uuid));
		Collections.sort(list, (o1, o2) -> o2.getTotalLength().compareTo(o1.getTotalLength()));
		StringBuilder sb = new StringBuilder();
		sb.append("{\"orderHistory\":[");
		for (OrderEntity orderEntity : list) {
			List<Point> points = order.getFirstAndLastPoints(orderEntity);
			sb.append("{\"startOrder\":\"")
					.append(points.get(0).getAddress())
					.append("\",\"endOrder\":\"")
					.append(points.get(1).getAddress())
					.append("\",\"dateOrderCreate\":\"")
					.append(orderEntity.getTimeCreated().toString())
					.append("\",\"id\":\"")
					.append(orderEntity.getId())
					.append("\",\"distance\":\"")
					.append(orderEntity.getTotalLength())
					.append("\",\"price\":\"")
					.append(orderEntity.getFinalPrice())
					.append("\" },");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]}");

		if (!list.isEmpty()) {
			return Response.status(200).entity(sb.toString()).build();
		} else {
			return Response.status(404).entity("Bad response.").build();
		}
	}

	@POST
	@Path("historyByDate")
	@Consumes("text/plain")
	public Response getOrderHistoryByDate(String uuid) {
		List<OrderEntity> list = order.getOrdersByStateAndDriverUuid(orderState.findByName("completed"), uuid);
		list.addAll(order.getOrdersByStateAndDriverUuid(orderState.findByName("refused"), uuid));
		Collections.sort(list, (o1, o2) -> o2.getTimeCreated().toString().compareTo(o1.getTimeCreated().toString()));
		StringBuilder sb = new StringBuilder();
		sb.append("{\"orderHistory\":[");
		for (OrderEntity orderEntity : list) {
			List<Point> points = order.getFirstAndLastPoints(orderEntity);
			sb.append("{\"startOrder\":\"")
					.append(points.get(0).getAddress())
					.append("\",\"endOrder\":\"")
					.append(points.get(1).getAddress())
					.append("\",\"dateOrderCreate\":\"")
					.append(orderEntity.getTimeCreated().toString())
					.append("\",\"id\":\"")
					.append(orderEntity.getId())
					.append("\",\"distance\":\"")
					.append(orderEntity.getTotalLength())
					.append("\",\"price\":\"")
					.append(orderEntity.getFinalPrice())
					.append("\" },");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]}");

		if (!list.isEmpty()) {
			return Response.status(200).entity(sb.toString()).build();
		} else {
			return Response.status(404).entity("Bad response.").build();
		}
	}

	@POST
	@Path("historyByPrice")
	@Consumes("text/plain")
	public Response getOrderHistoryByPrice(String uuid) {
		List<OrderEntity> list = order.getOrdersByStateAndDriverUuid(orderState.findByName("completed"), uuid);
		list.addAll(order.getOrdersByStateAndDriverUuid(orderState.findByName("refused"), uuid));
		Collections.sort(list, (o1, o2) -> o2.getFinalPrice().compareTo(o1.getFinalPrice()));
		StringBuilder sb = new StringBuilder();
		sb.append("{\"orderHistory\":[");
		for (OrderEntity orderEntity : list) {
			List<Point> points = order.getFirstAndLastPoints(orderEntity);
			sb.append("{\"startOrder\":\"")
					.append(points.get(0).getAddress())
					.append("\",\"endOrder\":\"")
					.append(points.get(1).getAddress())
					.append("\",\"dateOrderCreate\":\"")
					.append(orderEntity.getTimeCreated().toString())
					.append("\",\"id\":\"")
					.append(orderEntity.getId())
					.append("\",\"distance\":\"")
					.append(orderEntity.getTotalLength())
					.append("\",\"price\":\"")
					.append(orderEntity.getFinalPrice())
					.append("\" },");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]}");

		if (!list.isEmpty()) {
			return Response.status(200).entity(sb.toString()).build();
		} else {
			return Response.status(404).entity("Bad response.").build();
		}
	}

	@POST
	@Path("assign")
	@Consumes("application/json")
	public Response getOrderAssign(OrderJson orderJson) {
		OrderEntity orderEntity = order.read(new BigInteger(orderJson.getId()));
		orderEntity.setDriverUserEntity(user.findByUuid(orderJson.getDriverUserUuid()));
		try {
			orderEntity.setTimeOfDriverArrival(new Timestamp(
					simpleDateFormat.parse(orderJson.getTimeOfDriverArrival()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		orderEntity.setOrderStateEntity(orderState.findByName("assigned"));
		order.update(orderEntity);
		StringBuilder msg = new StringBuilder();

		msg.append("You order status changed.").append("Status: ").append(orderEntity.getOrderStateEntity().getName())
				.append("\n");
		msg.append("Your tracking number: ").append(url)
				.append("viewOrderByPublicToken.html?publicToken=")
				.append(orderEntity.getPublicToken());
		String mail = user.findByUuid(orderEntity.getCustomerUserEntity().getUuid()).getEmail();
		Mail.sendMail(mail, "Taxi Service: order status changed ", msg.toString());

		if (orderEntity != null) {
			return Response.status(200).entity("You assigned on this Order.").build();
		} else {
			return Response.status(404).entity("Bad response. You didn't assign on this Order.").build();
		}
	}

	@POST
	@Path("inProgress")
	@Consumes("application/json")
	public Response getOrderInProgress(OrderJson orderJson) {
		OrderEntity orderEntity = order.read(new BigInteger(orderJson.getId()));
		if (orderEntity.getOrderStateEntity().getName() == orderState.findByName("assigned").getName()) {

			OrderStateEntity orderStateEntity = orderState.findByName("in progress");
			List<OrderEntity> orderEntities = order.getOrdersByStateAndDriverUuid(orderStateEntity, orderJson.getDriverUserUuid());
			if (orderEntities.isEmpty()) {
				orderEntity.setOrderStateEntity(orderState.findByName("in progress"));
				orderEntity.setTimeStarted(new Timestamp(new Date().getTime()));
				order.update(orderEntity);
				return Response.status(200).entity("You started this order.").build();
			} else return Response.status(201).entity("You have \"in progress\" order already.").build();
		} else {
			if (orderEntity != null) {
				return Response.status(201).entity("Customer refused or updated this order.").build();
			} else {
				return Response.status(404).entity("Bad response. Order not found in DB.").build();
			}
		}
	}
}