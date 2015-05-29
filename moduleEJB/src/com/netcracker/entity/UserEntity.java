package com.netcracker.entity;



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
		@NamedQuery(name = "User.findByUuid", query = "SELECT f FROM UserEntity f WHERE f.uuid = :uuid"),
		@NamedQuery(name = "User.getDrivers", query = "SELECT f.userEntity FROM UserUserAccessLevelEntity f " +
				"WHERE f.userAccessLevelEntity.name = 'driver'")
})
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

	@ManyToMany
	@JoinTable(
			name = "user$user_access_level",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "user_access_level_id", referencedColumnName = "id")})
	private Collection<UserAccessLevelEntity> userAccessLevelEntities;

	@ManyToMany
	@JoinTable(
			name = "user$driver_category",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "driver_category_id", referencedColumnName = "id")})
	private Collection<DriverCategoryEntity> driverCategoryEntities;

	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	private UserGroupEntity userGroupEntities;

	@OneToMany(mappedBy = "userEntity")
	private Collection<FavouriteAddressEntity> favouriteAddressEntities;

	@OneToMany(mappedBy = "userEntity")
	private Collection<UserDriverCategoryEntity> user$DriverCategoryEntities;

	@OneToMany(mappedBy = "customerUserEntity")
	private Collection<OrderEntity> orderEntitiesAsCustomer;

	@OneToMany(mappedBy = "driverUserEntity")
	private Collection<OrderEntity> orderEntitiesAsDriver;

	@OneToMany(mappedBy = "userEntity")
	private Collection<UserUserAccessLevelEntity> user$UserAccessLevelEntities;

	@OneToOne(mappedBy = "userEntity")
	private CarEntity carEntity;

	@Column(name = "uuid", nullable = true, insertable = true, updatable = false, length = 2147483647)
	private String uuid;

	@Column(name = "email", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String email;

	@Column(name = "password", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String password;

	@Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String firstName;

	@Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String lastName;

	@Column(name = "date_registered", nullable = false, insertable = true, updatable = true)
	private Timestamp dateRegistered;

	@Column(name = "phone", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String phone;

	@Column(name = "animal_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean animalFriendly;

	@Column(name = "smoking_friendly", nullable = true, insertable = true, updatable = true)
	private Boolean smokingFriendly;

	@Column(name = "confirmed", nullable = false, insertable = true, updatable = true)
	private Boolean confirmed = false;

	@Column(name = "blocked", nullable = false, insertable = true, updatable = true)
	private boolean blocked;

	@Column(name = "birthday", nullable = true, insertable = true, updatable = true)
	private Date birthday;

	@Column(name = "sex", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String sex;

	@Column(name = "alternative_phone", nullable = true, insertable = true, updatable = true, length = 2147483647)
	private String alternativePhone;

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

	public Collection<OrderEntity> getOrderEntitiesAsCustomer() {
		return orderEntitiesAsCustomer;
	}

	public void setOrderEntitiesAsCustomer(Collection<OrderEntity> orderEntities) {
		this.orderEntitiesAsCustomer = orderEntities;
	}

	public Collection<OrderEntity> getOrderEntitiesAsDriver() {
		return orderEntitiesAsDriver;
	}

	public void setOrderEntitiesAsDriver(Collection<OrderEntity> driverOrderEntities) {
		this.orderEntitiesAsDriver = driverOrderEntities;
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

	public Collection<FavouriteAddressEntity> getFavouriteAddressEntities() {
		return favouriteAddressEntities;
	}

	public void setFavouriteAddressEntities(Collection<FavouriteAddressEntity> favouriteAddressEntities) {
		this.favouriteAddressEntities = favouriteAddressEntities;
	}

	public UserGroupEntity getUserGroupEntities() {
		return userGroupEntities;
	}

	public void setUserGroupEntities(UserGroupEntity userGroupByGroupId) {
		this.userGroupEntities = userGroupByGroupId;
	}

	public Collection<UserDriverCategoryEntity> getUser$DriverCategoryEntities() {
		return user$DriverCategoryEntities;
	}

	public void setUser$DriverCategoryEntities(Collection<UserDriverCategoryEntity> user$DriverCategoryEntities) {
		this.user$DriverCategoryEntities = user$DriverCategoryEntities;
	}

	public Collection<UserUserAccessLevelEntity> getUser$UserAccessLevelEntities() {
		return user$UserAccessLevelEntities;
	}

	public void setUser$UserAccessLevelEntities(Collection<UserUserAccessLevelEntity> user$UserAccessLevelEntities) {
		this.user$UserAccessLevelEntities = user$UserAccessLevelEntities;
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
		if (dateRegistered != null ? !dateRegistered.equals(that.dateRegistered) : that.dateRegistered != null) {
			return false;
		}
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
		if (animalFriendly != null ? !animalFriendly.equals(that.animalFriendly) : that.animalFriendly != null) {
			return false;
		}
		if (smokingFriendly != null ? !smokingFriendly.equals(that.smokingFriendly) : that.smokingFriendly != null) {
			return false;
		}
		if (confirmed != null ? !confirmed.equals(that.confirmed) : that.confirmed != null) return false;
		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
		if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
		if (alternativePhone != null ? !alternativePhone.equals(that.alternativePhone) : that.alternativePhone != null)
			return false;
		return true;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (dateRegistered != null ? dateRegistered.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (animalFriendly != null ? animalFriendly.hashCode() : 0);
		result = 31 * result + (smokingFriendly != null ? smokingFriendly.hashCode() : 0);
		result = 31 * result + (confirmed != null ? confirmed.hashCode() : 0);
		result = 31 * result + (blocked ? 1 : 0);
		result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (alternativePhone != null ? alternativePhone.hashCode() : 0);
		return result;
	}
}
