package net.mycorp.vo.customer;

import net.mycorp.vo.customer.pk.CustomerProfilePK;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AjayMenon on 12/29/2016.
 */
@Entity
@Table(name = "CUSTOMER_PROFILE")
@IdClass(CustomerProfilePK.class)
public class CustomerProfile {

	@Id
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Id
	@Column(name = "LAST_NAME")
	private String lastName;

	@Id
	@Column(name = "GUID")
	private String guid;

	@OneToMany(mappedBy = "customerProfile",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<PhoneNumber> phoneNumnber;

	@OneToMany(mappedBy = "customerProfile",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Address> customerAddress;

	@OneToMany(mappedBy = "customerProfile",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<PersonalDetail> customerPersonalDetails;

	//customer profile
	public CustomerProfile(String firstName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	//getter and setter
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerProfile that = (CustomerProfile) o;

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
