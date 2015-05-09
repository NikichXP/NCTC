package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "public", catalog = "postgres")
@NamedQueries({
		@NamedQuery(name = "User.findByEmailIgnoreCase", query = "SELECT f FROM UserEntity f " +
				"WHERE UPPER(f.email) = UPPER(:email)"),
		@NamedQuery(name = "User.findByPhone", query = "SELECT f FROM UserEntity f WHERE f.phone = :phone"),
		@NamedQuery(name = "User.deleteById", query = "DELETE FROM UserEntity f WHERE f.id = :id"),
		@NamedQuery(name = "User.findByEmailIgnoreCaseAndPassword", query = "SELECT f FROM UserEntity f " +
				"WHERE UPPER(f.email) = UPPER(:email) AND f.password = :password"),
		@NamedQuery(name = "User.findByPhoneAndPassword", query = "SELECT f FROM UserEntity f " +
				"WHERE f.phone = :phone AND f.password = :password"),
		@NamedQuery(name = "User.findByUuid", query = "SELECT f FROM UserEntity f WHERE f.uuid = :uuid")})
public class UserEntity {
	@SequenceGenerator(
			name = "USER_SEQUENCE_GENERATOR",
			sequenceName = "USER_ID_SEQ",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;
	@Basic
	@Column(name = "sex", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String sex;
	@Basic
	@Column(name = "date_registered", nullable = false, insertable = true, updatable = true)
	private Timestamp dateRegistered;
	@Basic
	@Column(name = "email", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String email;
	@Basic
	@Column(name = "phone", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String phone;
	@Basic
	@Column(name = "animal_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean animalFriendly;
	@Basic
	@Column(name = "smoking_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean smokingFriendly;
	@Basic
	@Column(name = "confirmed", nullable = false, insertable = true, updatable = true)
	private Boolean confirmed;
	@Basic
	@Column(name = "blocked", nullable = false, insertable = true, updatable = true)
	private boolean blocked;
	@Basic
	@Column(name = "uuid", nullable = true, insertable = true, updatable = false, length = 2147483647)
	private String uuid;
	@OneToMany(mappedBy = "userByCustomerId")
	private Collection<FavouriteAddressEntity> favouriteAddressesById;
	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	private UserGroupEntity userGroupByGroupId;
	@OneToMany(mappedBy = "userByUserId")
	private Collection<UserDriverCategoryEntity> userDriverCategoriesById;
	@OneToMany(mappedBy = "userByUserId")
	private Collection<UserUserAccessLevelEntity> userUserAccessLevelsById;
	@OneToMany(mappedBy = "customerUserEntity")
	private Collection<OrderEntity> customerOrderEntities;
	@OneToMany(mappedBy = "driverUserEntity")
	private Collection<OrderEntity> driverOrderEntities;
	@OneToOne(mappedBy = "userEntity")
	private CarEntity carEntity;
	@Basic
	@Column(name = "password", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String password;
	@Basic
	@Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String firstName;
	@Basic
	@Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String lastName;
	@Basic
	@Column(name = "birthday", nullable = true, insertable = true, updatable = true)
	private Date birthday;
	@Basic
	@Column(name = "alternative_phone", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String alternativePhone;
	@ManyToMany
	@JoinTable(
			name = "user-user_access_level",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "user_access_level_id", referencedColumnName = "id")})
	private Collection<UserAccessLevelEntity> userAccessLevelEntities;
	@ManyToMany
	@JoinTable(
			name = "user-driver_category",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "driver_category_id", referencedColumnName = "id")})
	private Collection<DriverCategoryEntity> driverCategoryEntities;

	public Collection<DriverCategoryEntity> getDriverCategoryEntities() {
		return driverCategoryEntities;
	}

	public void setDriverCategoryEntities(Collection<DriverCategoryEntity> driverCategoryEntities) {
		this.driverCategoryEntities = driverCategoryEntities;
	}

	public Collection<UserAccessLevelEntity> getUserAccessLevelEntities() {
		return userAccessLevelEntities;
	}

	public void setUserAccessLevelEntities(Collection<UserAccessLevelEntity> userAccessLevelEntities) {
		this.userAccessLevelEntities = userAccessLevelEntities;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Collection<OrderEntity> getCustomerOrderEntities() {
		return customerOrderEntities;
	}

	public void setCustomerOrderEntities(Collection<OrderEntity> orderEntities) {
		this.customerOrderEntities = orderEntities;
	}

	public Collection<OrderEntity> getDriverOrderEntities() {
		return driverOrderEntities;
	}

	public void setDriverOrderEntities(Collection<OrderEntity> driverOrderEntities) {
		this.driverOrderEntities = driverOrderEntities;
	}

	public CarEntity getCarEntity() {
		return carEntity;
	}

	public void setCarEntity(CarEntity carEntity) {
		this.carEntity = carEntity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Timestamp dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAlternativePhone() {
		return alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}

	public Boolean getAnimalFriendly() {
		return animalFriendly;
	}

	public void setAnimalFriendly(Boolean animalFriendly) {
		this.animalFriendly = animalFriendly;
	}

	public Boolean getSmokingFriendly() {
		return smokingFriendly;
	}

	public void setSmokingFriendly(Boolean smokingFriendly) {
		this.smokingFriendly = smokingFriendly;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Collection<FavouriteAddressEntity> getFavouriteAddressesById() {
		return favouriteAddressesById;
	}

	public void setFavouriteAddressesById(Collection<FavouriteAddressEntity> favouriteAddressesById) {
		this.favouriteAddressesById = favouriteAddressesById;
	}

	public UserGroupEntity getUserGroupByGroupId() {
		return userGroupByGroupId;
	}

	public void setUserGroupByGroupId(UserGroupEntity userGroupByGroupId) {
		this.userGroupByGroupId = userGroupByGroupId;
	}

	public Collection<UserDriverCategoryEntity> getUserDriverCategoriesById() {
		return userDriverCategoriesById;
	}

	public void setUserDriverCategoriesById(Collection<UserDriverCategoryEntity> userDriverCategoriesById) {
		this.userDriverCategoriesById = userDriverCategoriesById;
	}

	public Collection<UserUserAccessLevelEntity> getUserUserAccessLevelsById() {
		return userUserAccessLevelsById;
	}

	public void setUserUserAccessLevelsById(Collection<UserUserAccessLevelEntity> userUserAccessLevelsById) {
		this.userUserAccessLevelsById = userUserAccessLevelsById;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserEntity that = (UserEntity) o;

		if (blocked != that.blocked) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
		if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
		if (dateRegistered != null ? !dateRegistered.equals(that.dateRegistered) : that.dateRegistered != null)
			return false;
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
		if (alternativePhone != null ? !alternativePhone.equals(that.alternativePhone) : that.alternativePhone != null)
			return false;
		if (animalFriendly != null ? !animalFriendly.equals(that.animalFriendly) : that.animalFriendly != null)
			return false;
		if (smokingFriendly != null ? !smokingFriendly.equals(that.smokingFriendly) : that.smokingFriendly != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (dateRegistered != null ? dateRegistered.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (alternativePhone != null ? alternativePhone.hashCode() : 0);
		result = 31 * result + (animalFriendly != null ? animalFriendly.hashCode() : 0);
		result = 31 * result + (smokingFriendly != null ? smokingFriendly.hashCode() : 0);
		result = 31 * result + (blocked ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + email + " " + phone;
	}
}
