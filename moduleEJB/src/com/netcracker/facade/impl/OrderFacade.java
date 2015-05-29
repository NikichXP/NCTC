package com.netcracker.facade.impl;



import com.netcracker.classes.Point;
import com.netcracker.entity.OrderEntity;
import com.netcracker.entity.OrderStateEntity;
import com.netcracker.entity.PathEntity;
import com.netcracker.facade.local_int.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Stateless
public class OrderFacade extends AbstractFacade<OrderEntity> implements Order {
	@PersistenceContext(unitName = "TaxiPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OrderFacade() {
		super(OrderEntity.class);
	}

	@Override
	public List<OrderEntity> getOrdersByStateAndCustomerUuid(OrderStateEntity orderStateEntity, String customerUuid) {
		List results = em.createNamedQuery("Order.getOrdersByStateAndCustomerUuid")
				.setParameter("customerUuid", customerUuid)
				.setParameter("orderStateEntity", orderStateEntity)
				.getResultList();
		return results;
	}

	@Override
	public List<OrderEntity> sortByDateAndUUIDAndState(String uuid, OrderStateEntity orderStateEntity) {
		return em.createNamedQuery("Order.sortByDateAndUUIDAndState")
				.setParameter("uuid", uuid)
				.setParameter("orderStateEntity", orderStateEntity)
				.getResultList();

	}


	@Override
	public List<OrderEntity> sortByPriceAndUUIDAndState(String uuid, OrderStateEntity orderStateEntity) {
		return em.createNamedQuery("Order.sortByPriceAndUUIDAndStat")
				.setParameter("uuid", uuid)
				.setParameter("orderStateEntity", orderStateEntity)
				.getResultList();

	}

	@Override
	public List<OrderEntity> sortByLengthAndUUIDAndState(String uuid, OrderStateEntity orderStateEntity) {
		return em.createNamedQuery("Order.sortByLengthAndUUIDAndStat")
				.setParameter("uuid", uuid)
				.setParameter("orderStateEntity", orderStateEntity)
				.getResultList();

	}

	@Override
	public List<OrderEntity> getOrdersByStateAndDriverUuid(OrderStateEntity orderStateEntity, String driverUuid) {
		List results = em.createNamedQuery("Order.getOrdersByStateAndDriverUuid")
				.setParameter("driverUuid", driverUuid)
				.setParameter("orderStateEntity", orderStateEntity)
				.getResultList();
		return results;
	}

	@Override
	public OrderEntity getByUUIDAndId(String orderId, String uuid) {
		List result = em.createNamedQuery("Order.getByUUIDAndID")
				.setParameter("id", new BigInteger(orderId))
				.setParameter("uuid", uuid)
				.getResultList();
		if (result.isEmpty())
			return null;
		else
			return (OrderEntity) result.get(0);
	}

	@Override
	public OrderEntity getByDriverUUIDAndID(String orderId, String driverUuid) {
		List result = em.createNamedQuery("Order.getByDriverUUIDAndID")
				.setParameter("id", new BigInteger(orderId))
				.setParameter("uuid", driverUuid)
				.getResultList();
		if (result.isEmpty())
			return null;
		else
			return (OrderEntity) result.get(0);
	}

	@Override
	public OrderEntity getOrderByPublicToken(String publicToken) {
		List result = em.createNamedQuery("Order.getOrderByPublicToken")
				.setParameter("publicToken", publicToken).getResultList();
		if (result.isEmpty())
			return null;
		else
			return (OrderEntity) result.get(0);
	}

	@Override
	public List<OrderEntity> getOrdersByState(OrderStateEntity orderStateEntity) {
		List results = em.createNamedQuery("Order.getOrdersByState")
				.setParameter("orderStateEntity", orderStateEntity)
				.getResultList();
		return results;
	}

	@Override
	public List<Point> getFirstAndLastPoints(OrderEntity orderEntity) {
		List<Point> points = new ArrayList<>();
		Comparator<PathEntity> pathEntityComparator = new Comparator<PathEntity>() {
			@Override
			public int compare(PathEntity o1, PathEntity o2) {
				return o1.getId().compareTo(o2.getId());
			}
		};
		List<PathEntity> pathEntities = new ArrayList<>(orderEntity.getPathEntities());
		pathEntities.sort(pathEntityComparator);
		PathEntity firstPath = pathEntities.get(0);
		PathEntity lastPath = pathEntities.get(pathEntities.size() - 1);

		points.add(new Point(firstPath.getStartAddress(), firstPath.getStartX(), firstPath.getStartY()));
		points.add(new Point(lastPath.getEndAddress(), lastPath.getEndX(), lastPath.getEndY()));

		return points;
	}
}

