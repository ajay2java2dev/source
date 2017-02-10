package net.mycorp.datasource.vo.customer.pk;

import net.mycorp.datasource.vo.customer.CustomerProfile;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by : Ajay Menon.
 * Date : 1/4/2017, 12:18 AM
 * Project Name : earthnet
 * Package Name : net.mycorp.datasource.vo.customer.pk
 */
public class CustomerProfilePK {
	@Id
	private String firstName;

	@Id
	private String lastName;

	@Id
	private String guid;

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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerProfilePK that = (CustomerProfilePK) o;

		if (!firstName.equals(that.firstName)) return false;
		if (!lastName.equals(that.lastName)) return false;
		return guid.equals(that.guid);
	}

	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + guid.hashCode();
		return result;
	}
}
