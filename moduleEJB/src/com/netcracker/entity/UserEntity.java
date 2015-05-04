package com.netcracker.entity;

/* 13:42 30.04.2015 by Viktor Taranenko */

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "public", catalog = "postgres")
public class UserEntity {
	private BigInteger id;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthday;
	private String sex;
	private Timestamp dateRegistered;
	private String email;
	private String phone;
	private String alternativePhone;
	private Boolean animalFriendly;
	private Boolean smokingFriendly;
	private boolean blocked;
	private Collection<FavouriteAddressEntity> favouriteAddressesById;
	private UserGroupEntity userGroupByGroupId;
	private Collection<UserDriverCategoryEntity> userDriverCategoriesById;
	private Collection<UserUserAccessLevelEntity> userUserAccessLevelsById;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Basic
	@Column(name = "password", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Basic
	@Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Basic
	@Column(name = "birthday", nullable = true, insertable = true, updatable = true)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Basic
	@Column(name = "sex", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Basic
	@Column(name = "date_registered", nullable = false, insertable = true, updatable = true)
	public Timestamp getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Timestamp dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	@Basic
	@Column(name = "email", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "phone", nullable = false, insertable = true, updatable = true, length = 2147483647)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Basic
	@Column(name = "alternative_phone", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getAlternativePhone() {
		return alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}

	@Basic
	@Column(name = "animal_friendly", nullable = true, insertable = true, updatable = true)
	public Boolean getAnimalFriendly() {
		return animalFriendly;
	}

	public void setAnimalFriendly(Boolean animalFriendly) {
		this.animalFriendly = animalFriendly;
	}

	@Basic
	@Column(name = "smoking_friendly", nullable = true, insertable = true, updatable = true)
	public Boolean getSmokingFriendly() {
		return smokingFriendly;
	}

	public void setSmokingFriendly(Boolean smokingFriendly) {
		this.smokingFriendly = smokingFriendly;
	}

	@Basic
	@Column(name = "blocked", nullable = false, insertable = true, updatable = true)
	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
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
	public String toString () {
		return firstName + " " + lastName + " " + email + " " + phone;
	}

	@OneToMany(mappedBy = "userByCustomerId")
	public Collection<FavouriteAddressEntity> getFavouriteAddressesById() {
		return favouriteAddressesById;
	}

	public void setFavouriteAddressesById(Collection<FavouriteAddressEntity> favouriteAddressesById) {
		this.favouriteAddressesById = favouriteAddressesById;
	}

	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	public UserGroupEntity getUserGroupByGroupId() {
		return userGroupByGroupId;
	}

	public void setUserGroupByGroupId(UserGroupEntity userGroupByGroupId) {
		this.userGroupByGroupId = userGroupByGroupId;
	}

	@OneToMany(mappedBy = "userByUserId")
	public Collection<UserDriverCategoryEntity> getUserDriverCategoriesById() {
		return userDriverCategoriesById;
	}

	public void setUserDriverCategoriesById(Collection<UserDriverCategoryEntity> userDriverCategoriesById) {
		this.userDriverCategoriesById = userDriverCategoriesById;
	}

	@OneToMany(mappedBy = "userByUserId")
	public Collection<UserUserAccessLevelEntity> getUserUserAccessLevelsById() {
		return userUserAccessLevelsById;
	}

	public void setUserUserAccessLevelsById(Collection<UserUserAccessLevelEntity> userUserAccessLevelsById) {
		this.userUserAccessLevelsById = userUserAccessLevelsById;
	}
}
