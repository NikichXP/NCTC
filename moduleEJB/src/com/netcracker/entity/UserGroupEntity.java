package com.netcracker.entity;



import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "user_group", schema = "public", catalog = "postgres")
public class UserGroupEntity {
	@SequenceGenerator(
			name = "user_group_SEQUENCE_GENERATOR",
			sequenceName = "user_group_id_seq",
			allocationSize = 1, initialValue = 1
	)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_group_SEQUENCE_GENERATOR")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger id;

	@OneToMany(mappedBy = "userGroupEntities")
	private Collection<UserEntity> UserEntities;

	@Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
	private String name;

	@Column(name = "tariff_multiplier", nullable = false, insertable = true, updatable = true, precision = 0)
	private BigInteger tariffMultiplier;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getTariffMultiplier() {
		return tariffMultiplier;
	}

	public void setTariffMultiplier(BigInteger tariffMultiplier) {
		this.tariffMultiplier = tariffMultiplier;
	}

	public Collection<UserEntity> getUserEntities() {
		return UserEntities;
	}

	public void setUserEntities(Collection<UserEntity> usersById) {
		this.UserEntities = usersById;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserGroupEntity that = (UserGroupEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (tariffMultiplier != null ? !tariffMultiplier.equals(that.tariffMultiplier) : that.tariffMultiplier != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (tariffMultiplier != null ? tariffMultiplier.hashCode() : 0);
		return result;
	}
}
