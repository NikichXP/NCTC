package com.netcracker.entity;

/* 19:59 29.04.2015 by Viktor Taranenko */

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@javax.persistence.Table(name = "order", schema = "public", catalog = "postgres")
public class OrderEntity {
	private BigInteger id;

	@Id
	@javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	private String publicToken;

	@Basic
	@javax.persistence.Column(name = "public_token", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getPublicToken() {
		return publicToken;
	}

	public void setPublicToken(String publicToken) {
		this.publicToken = publicToken;
	}

	private String contactPhone;

	@Basic
	@javax.persistence.Column(name = "contact_phone", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	private String contactName;

	@Basic
	@javax.persistence.Column(name = "contact_name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	private Date timeCreated;

	@Basic
	@javax.persistence.Column(name = "time_created", nullable = false, insertable = true, updatable = true)
	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	private Date timeRequested;

	@Basic
	@javax.persistence.Column(name = "time_requested", nullable = false, insertable = true, updatable = true)
	public Date getTimeRequested() {
		return timeRequested;
	}

	public void setTimeRequested(Date timeRequested) {
		this.timeRequested = timeRequested;
	}

	private Date timeOfDriverArrival;

	@Basic
	@javax.persistence.Column(name = "time_of_driver_arrival", nullable = true, insertable = true, updatable = true)
	public Date getTimeOfDriverArrival() {
		return timeOfDriverArrival;
	}

	public void setTimeOfDriverArrival(Date timeOfDriverArrival) {
		this.timeOfDriverArrival = timeOfDriverArrival;
	}

	private Date timeStarted;

	@Basic
	@javax.persistence.Column(name = "time_started", nullable = true, insertable = true, updatable = true)
	public Date getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(Date timeStarted) {
		this.timeStarted = timeStarted;
	}

	private Date timeCompleted;

	@Basic
	@javax.persistence.Column(name = "time_completed", nullable = true, insertable = true, updatable = true)
	public Date getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = timeCompleted;
	}

	private BigInteger requestedSeatsCount;

	@Basic
	@javax.persistence.Column(name = "requested_seats_count", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getRequestedSeatsCount() {
		return requestedSeatsCount;
	}

	public void setRequestedSeatsCount(BigInteger requestedSeatsCount) {
		this.requestedSeatsCount = requestedSeatsCount;
	}

	private String driverSex;

	@Basic
	@javax.persistence.Column(name = "driver_sex", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getDriverSex() {
		return driverSex;
	}

	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}

	private Boolean smokingFriendly;

	@Basic
	@javax.persistence.Column(name = "smoking_friendly", nullable = true, insertable = true, updatable = true)
	public Boolean getSmokingFriendly() {
		return smokingFriendly;
	}

	public void setSmokingFriendly(Boolean smokingFriendly) {
		this.smokingFriendly = smokingFriendly;
	}

	private Boolean animalFriendly;

	@Basic
	@javax.persistence.Column(name = "animal_friendly", nullable = true, insertable = true, updatable = true)
	public Boolean getAnimalFriendly() {
		return animalFriendly;
	}

	public void setAnimalFriendly(Boolean animalFriendly) {
		this.animalFriendly = animalFriendly;
	}

	private Boolean wifi;

	@Basic
	@javax.persistence.Column(name = "wifi", nullable = true, insertable = true, updatable = true)
	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	private String customerPreCreateComment;

	@Basic
	@javax.persistence.Column(name = "customer_pre_create_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getCustomerPreCreateComment() {
		return customerPreCreateComment;
	}

	public void setCustomerPreCreateComment(String customerPreCreateComment) {
		this.customerPreCreateComment = customerPreCreateComment;
	}

	private String customerPostCompleteComment;

	@Basic
	@javax.persistence.Column(name = "customer_post_complete_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getCustomerPostCompleteComment() {
		return customerPostCompleteComment;
	}

	public void setCustomerPostCompleteComment(String customerPostCompleteComment) {
		this.customerPostCompleteComment = customerPostCompleteComment;
	}

	private String customerRefuseComment;

	@Basic
	@javax.persistence.Column(name = "customer_refuse_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getCustomerRefuseComment() {
		return customerRefuseComment;
	}

	public void setCustomerRefuseComment(String customerRefuseComment) {
		this.customerRefuseComment = customerRefuseComment;
	}

	private String driverRefuseComment;

	@Basic
	@javax.persistence.Column(name = "driver_refuse_comment", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getDriverRefuseComment() {
		return driverRefuseComment;
	}

	public void setDriverRefuseComment(String driverRefuseComment) {
		this.driverRefuseComment = driverRefuseComment;
	}

	private BigInteger totalLength;

	@Basic
	@javax.persistence.Column(name = "total_length", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(BigInteger totalLength) {
		this.totalLength = totalLength;
	}

	private BigInteger totalMultiplier;

	@Basic
	@javax.persistence.Column(name = "total_multiplier", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getTotalMultiplier() {
		return totalMultiplier;
	}

	public void setTotalMultiplier(BigInteger totalMultiplier) {
		this.totalMultiplier = totalMultiplier;
	}

	private BigInteger finalPrice;

	@Basic
	@javax.persistence.Column(name = "final_price", nullable = true, insertable = true, updatable = true, precision = 0)
	public BigInteger getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigInteger finalPrice) {
		this.finalPrice = finalPrice;
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
		if (finalPrice != null ? !finalPrice.equals(that.finalPrice) : that.finalPrice != null) return false;

		return true;
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