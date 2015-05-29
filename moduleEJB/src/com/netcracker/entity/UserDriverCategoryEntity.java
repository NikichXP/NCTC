package com.netcracker.entity;



import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user$driver_category", schema = "public", catalog = "postgres")
public class UserDriverCategoryEntity {
	@SequenceGenerator(
			name = "user-driver_category_SEQUENCE_GENERATOR",
			sequenceName = "user-driver_category_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user-driver_category_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@ManyToOne
	@JoinColumn(name = "driver_category_id", referencedColumnName = "id", nullable = false)
	private DriverCategoryEntity driverCategoryEntity;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserEntity userEntity;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public DriverCategoryEntity getDriverCategoryEntity() {
		return driverCategoryEntity;
	}

	public void setDriverCategoryEntity(DriverCategoryEntity driverCategoryEntity) {
		this.driverCategoryEntity = driverCategoryEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userByUserId) {
		this.userEntity = userByUserId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserDriverCategoryEntity that = (UserDriverCategoryEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		return result;
	}
}
