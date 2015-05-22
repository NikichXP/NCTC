package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "order", schema = "public", catalog = "postgres")
@NamedQueries({
		@NamedQuery(name = "Order.getOrdersByStateAndCustomerUuid", query = "SELECT f FROM OrderEntity f " +
				"WHERE f.orderStateEntity = :orderStateEntity AND f.customerUserEntity.uuid = :customerUuid"),
		@NamedQuery(name = "Order.getOrdersByState", query = "SELECT f FROM OrderEntity f " +
				"WHERE f.orderStateEntity = :orderStateEntity"),
		@NamedQuery(name = "Order.getOrdersByStateAndDriverUuid", query = "SELECT f FROM OrderEntity f " +
				"WHERE f.orderStateEntity = :orderStateEntity AND f.driverUserEntity.uuid = :driverUuid"),
		@NamedQuery(name = "Order.getByUUIDAndID", query = "SELECT f FROM OrderEntity f " +
				"WHERE f.id = :id AND f.customerUserEntity.uuid = :uuid"),
		@NamedQuery(name = "Order.getByDriverUUIDAndID", query = "SELECT f FROM OrderEntity f " +
				"WHERE f.id = :id AND f.driverUserEntity.uuid = :uuid")
})
public class OrderEntity {
	@SequenceGenerator(
			name = "ORDER_SEQUENCE_GENERATOR",
			sequenceName = "ORDER_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@OneToMany(mappedBy="orderEntity", cascade = CascadeType.ALL)
	private Collection<PathEntity> pathEntities;

	@ManyToOne
	@JoinColumn(name = "customer_refuse_cause_id", referencedColumnName = "id")
	private RefuseCauseByCustomerEntity refuseCauseByCustomerEntity;

	@ManyToOne
	@JoinColumn(name = "driver_refuse_cause_id", referencedColumnName = "id")
	private RefuseCauseByDriverEntity refuseCauseByDriverEntity;

	@ManyToOne
	@JoinColumn(name = "car_class_id", referencedColumnName = "id")
	private CarClassEntity carClassEntity;

	@ManyToOne
	@JoinColumn(name = "music_type_id", referencedColumnName = "id")
	private MusicTypeEntity musicTypeEntity;

	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private OrderTypeEntity orderTypeEntity;

	@ManyToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private OrderStateEntity orderStateEntity;

	@ManyToOne
	@JoinColumn(name = "customer_user_id", referencedColumnName = "id")
	private UserEntity customerUserEntity;

	@ManyToOne
	@JoinColumn(name = "driver_user_id", referencedColumnName = "id")
	private UserEntity driverUserEntity;

	@Column(name = "public_token", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String publicToken;

	@Column(name = "contact_phone", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String contactPhone;

	@Column(name = "contact_name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String contactName;

	@Column(name = "time_created", nullable = false, insertable = true, updatable = true)
	private Timestamp timeCreated;

	@Column(name = "time_requested", nullable = false, insertable = true, updatable = true)
	private Timestamp timeRequested;

	@Column(name = "time_of_driver_arrival", nullable = true, insertable = true, updatable = true)
	private Timestamp timeOfDriverArrival;

	@Column(name = "time_started", nullable = true, insertable = true, updatable = true)
	private Timestamp timeStarted;

	@Column(name = "time_completed", nullable = true, insertable = true, updatable = true)
	private Timestamp timeCompleted;

	@Column(name = "requested_seats_count", nullable = true, insertable = true, updatable = true, precision = 0)
	private BigInteger requestedSeatsCount;

	@Column(name = "driver_sex", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String driverSex;

	@Column(name = "smoking_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean smokingFriendly;

	@Column(name = "air_conditioner", nullable = true, insertable = true, updatable = true)
	private Boolean airConditioner;

	@Column(name = "animal_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean animalFriendly;

	@Column(name = "wifi", nullable = true, insertable = true, updatable = true)
	private Boolean wifi;

	@Column(name = "customer_pre_create_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String customerPreCreateComment;

	@Column(name = "customer_post_complete_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String customerPostCompleteComment;

	@Column(name = "customer_refuse_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String customerRefuseComment;

	@Column(name = "driver_refuse_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String driverRefuseComment;

	@Column(name = "total_length", nullable = true, insertable = true, updatable = true, precision = 3)
	private BigDecimal totalLength;

	@Column(name = "total_multiplier", nullable = true, insertable = true, updatable = true, precision = 3)
	private BigDecimal totalMultiplier;

	@Column(name = "final_price", nullable = true, insertable = true, updatable = true, precision = 2)
	private BigDecimal finalPrice;

	public CarClassEntity getCarClassEntity() {
		return carClassEntity;
	}

	public void setCarClassEntity(CarClassEntity carClassEntity) {
		this.carClassEntity = carClassEntity;
	}

	public RefuseCauseByCustomerEntity getRefuseCauseByCustomerEntity() {
		return refuseCauseByCustomerEntity;
	}

	public void setRefuseCauseByCustomerEntity(RefuseCauseByCustomerEntity refuseCauseByCustomerEntity) {
		this.refuseCauseByCustomerEntity = refuseCauseByCustomerEntity;
	}

	public RefuseCauseByDriverEntity getRefuseCauseByDriverEntity() {
		return refuseCauseByDriverEntity;
	}

	public void setRefuseCauseByDriverEntity(RefuseCauseByDriverEntity refuseCauseByDriverEntity) {
		this.refuseCauseByDriverEntity = refuseCauseByDriverEntity;
	}

	public MusicTypeEntity getMusicTypeEntity() {
		return musicTypeEntity;
	}

	public void setMusicTypeEntity(MusicTypeEntity musicTypeEntity) {
		this.musicTypeEntity = musicTypeEntity;
	}

	public OrderTypeEntity getOrderTypeEntity() {
		return orderTypeEntity;
	}

	public void setOrderTypeEntity(OrderTypeEntity orderTypeEntity) {
		this.orderTypeEntity = orderTypeEntity;
	}

	public OrderStateEntity getOrderStateEntity() {
		return orderStateEntity;
	}

	public void setOrderStateEntity(OrderStateEntity orderStateEntity) {
		this.orderStateEntity = orderStateEntity;
	}

	public UserEntity getCustomerUserEntity() {
		return customerUserEntity;
	}

	public void setCustomerUserEntity(UserEntity customerUserEntity) {
		this.customerUserEntity = customerUserEntity;
	}

	public UserEntity getDriverUserEntity() {
		return driverUserEntity;
	}

	public void setDriverUserEntity(UserEntity driverUserEntity) {
		this.driverUserEntity = driverUserEntity;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getPublicToken() {
		return publicToken;
	}

	public void setPublicToken(String publicToken) {
		this.publicToken = publicToken;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Timestamp getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Timestamp getTimeRequested() {
		return timeRequested;
	}

	public void setTimeRequested(Timestamp timeRequested) {
		this.timeRequested = timeRequested;
	}

	public Timestamp getTimeOfDriverArrival() {
		return timeOfDriverArrival;
	}

	public void setTimeOfDriverArrival(Timestamp timeOfDriverArrival) {
		this.timeOfDriverArrival = timeOfDriverArrival;
	}

	public Timestamp getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(Timestamp timeStarted) {
		this.timeStarted = timeStarted;
	}

	public Timestamp getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Timestamp timeCompleted) {
		this.timeCompleted = timeCompleted;
	}

	public BigInteger getRequestedSeatsCount() {
		return requestedSeatsCount;
	}

	public void setRequestedSeatsCount(BigInteger requestedSeatsCount) {
		this.requestedSeatsCount = requestedSeatsCount;
	}

	public String getDriverSex() {
		return driverSex;
	}

	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}

	public Boolean getSmokingFriendly() {
		return smokingFriendly;
	}

	public void setSmokingFriendly(Boolean smokingFriendly) {
		this.smokingFriendly = smokingFriendly;
	}

	public Boolean getAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(Boolean airConditioner) {
		this.airConditioner = airConditioner;
	}

	public Boolean getAnimalFriendly() {
		return animalFriendly;
	}

	public void setAnimalFriendly(Boolean animalFriendly) {
		this.animalFriendly = animalFriendly;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public String getCustomerPreCreateComment() {
		return customerPreCreateComment;
	}

	public void setCustomerPreCreateComment(String customerPreCreateComment) {
		this.customerPreCreateComment = customerPreCreateComment;
	}

	public String getCustomerPostCompleteComment() {
		return customerPostCompleteComment;
	}

	public void setCustomerPostCompleteComment(String customerPostCompleteComment) {
		this.customerPostCompleteComment = customerPostCompleteComment;
	}

	public String getCustomerRefuseComment() {
		return customerRefuseComment;
	}

	public void setCustomerRefuseComment(String customerRefuseComment) {
		this.customerRefuseComment = customerRefuseComment;
	}

	public String getDriverRefuseComment() {
		return driverRefuseComment;
	}

	public void setDriverRefuseComment(String driverRefuseComment) {
		this.driverRefuseComment = driverRefuseComment;
	}

	public BigDecimal getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(BigDecimal totalLength) {
		this.totalLength = totalLength;
	}

	public BigDecimal getTotalMultiplier() {
		return totalMultiplier;
	}

	public void setTotalMultiplier(BigDecimal totalMultiplier) {
		this.totalMultiplier = totalMultiplier;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Collection<PathEntity> getPathEntities() {
		return pathEntities;
	}

	public void setPathEntities(Collection<PathEntity> pathEntities) {
		this.pathEntities = pathEntities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderEntity that = (OrderEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (publicToken != null ? !publicToken.equals(that.publicToken) : that.publicToken != null) return false;
		if (contactPhone != null ? !contactPhone.equals(that.contactPhone) : that.contactPhone != null) return false;
		if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
		if (timeCreated != null ? !timeCreated.equals(that.timeCreated) : that.timeCreated != null) return false;
		if (timeRequested != null ? !timeRequested.equals(that.timeRequested) : that.timeRequested != null)
			return false;
		if (timeOfDriverArrival != null ? !timeOfDriverArrival.equals(that.timeOfDriverArrival) : that.timeOfDriverArrival != null)
			return false;
		if (timeStarted != null ? !timeStarted.equals(that.timeStarted) : that.timeStarted != null) return false;
		if (timeCompleted != null ? !timeCompleted.equals(that.timeCompleted) : that.timeCompleted != null)
			return false;
		if (requestedSeatsCount != null ? !requestedSeatsCount.equals(that.requestedSeatsCount) : that.requestedSeatsCount != null)
			return false;
		if (driverSex != null ? !driverSex.equals(that.driverSex) : that.driverSex != null) return false;
		if (smokingFriendly != null ? !smokingFriendly.equals(that.smokingFriendly) : that.smokingFriendly != null)
			return false;
		if (airConditioner != null ? !airConditioner.equals(that.airConditioner) : that.airConditioner != null)
			return false;
		if (animalFriendly != null ? !animalFriendly.equals(that.animalFriendly) : that.animalFriendly != null)
			return false;
		if (wifi != null ? !wifi.equals(that.wifi) : that.wifi != null) return false;
		if (customerPreCreateComment != null ? !customerPreCreateComment.equals(that.customerPreCreateComment) : that.customerPreCreateComment != null)
			return false;
		if (customerPostCompleteComment != null ? !customerPostCompleteComment.equals(that.customerPostCompleteComment) : that.customerPostCompleteComment != null)
			return false;
		if (customerRefuseComment != null ? !customerRefuseComment.equals(that.customerRefuseComment) : that.customerRefuseComment != null)
			return false;
		if (driverRefuseComment != null ? !driverRefuseComment.equals(that.driverRefuseComment) : that.driverRefuseComment != null)
			return false;
		if (totalLength != null ? !totalLength.equals(that.totalLength) : that.totalLength != null) return false;
		if (totalMultiplier != null ? !totalMultiplier.equals(that.totalMultiplier) : that.totalMultiplier != null)
			return false;
		return !(finalPrice != null ? !finalPrice.equals(that.finalPrice) : that.finalPrice != null);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (publicToken != null ? publicToken.hashCode() : 0);
		result = 31 * result + (contactPhone != null ? contactPhone.hashCode() : 0);
		result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
		result = 31 * result + (timeCreated != null ? timeCreated.hashCode() : 0);
		result = 31 * result + (timeRequested != null ? timeRequested.hashCode() : 0);
		result = 31 * result + (timeOfDriverArrival != null ? timeOfDriverArrival.hashCode() : 0);
		result = 31 * result + (timeStarted != null ? timeStarted.hashCode() : 0);
		result = 31 * result + (timeCompleted != null ? timeCompleted.hashCode() : 0);
		result = 31 * result + (requestedSeatsCount != null ? requestedSeatsCount.hashCode() : 0);
		result = 31 * result + (driverSex != null ? driverSex.hashCode() : 0);
		result = 31 * result + (smokingFriendly != null ? smokingFriendly.hashCode() : 0);
		result = 31 * result + (airConditioner != null ? airConditioner.hashCode() : 0);
		result = 31 * result + (animalFriendly != null ? animalFriendly.hashCode() : 0);
		result = 31 * result + (wifi != null ? wifi.hashCode() : 0);
		result = 31 * result + (customerPreCreateComment != null ? customerPreCreateComment.hashCode() : 0);
		result = 31 * result + (customerPostCompleteComment != null ? customerPostCompleteComment.hashCode() : 0);
		result = 31 * result + (customerRefuseComment != null ? customerRefuseComment.hashCode() : 0);
		result = 31 * result + (driverRefuseComment != null ? driverRefuseComment.hashCode() : 0);
		result = 31 * result + (totalLength != null ? totalLength.hashCode() : 0);
		result = 31 * result + (totalMultiplier != null ? totalMultiplier.hashCode() : 0);
		result = 31 * result + (finalPrice != null ? finalPrice.hashCode() : 0);
		return result;
	}
}
