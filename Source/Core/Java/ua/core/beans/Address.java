package ua.core.beans;


public class Address implements IAddress {
	
	private String	street1			= null;
	private	String	street2			= null;
	private String	city			= null;
	private String	state			= null;
	private String	postCode		= null;
	private String	country			= null;
	private String	countryCode		= null;
	
	public Address () {  }

	public Address (String street1, String street2, String city, String state, String postCode, String country, String countryCode) {

		super ();
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
		this.country = country;
		this.countryCode = countryCode;
	}

	
	public String getStreet1 () {
	
		return street1;
	}

	
	public void setStreet1 (String street1) {
	
		this.street1 = street1;
	}

	
	public String getStreet2 () {
	
		return street2;
	}

	
	public void setStreet2 (String street2) {
	
		this.street2 = street2;
	}

	
	public String getCity () {
	
		return city;
	}

	
	public void setCity (String city) {
	
		this.city = city;
	}

	
	public String getState () {
	
		return state;
	}

	
	public void setState (String state) {
	
		this.state = state;
	}

	
	public String getPostCode () {
	
		return postCode;
	}

	
	public void setPostCode (String postCode) {
	
		this.postCode = postCode;
	}

	
	public String getCountry () {
	
		return country;
	}

	
	public void setCountry (String country) {
	
		this.country = country;
	}

	
	public String getCountryCode () {
	
		return countryCode;
	}

	
	public void setCountryCode (String countryCode) {
	
		this.countryCode = countryCode;
	}


	public int hashCode () {

		final int prime = 31;
		int result = 1;
		result = prime * result + ( (city == null) ? 0 : city.hashCode ());
		result = prime * result + ( (country == null) ? 0 : country.hashCode ());
		result = prime * result + ( (countryCode == null) ? 0 : countryCode.hashCode ());
		result = prime * result + ( (postCode == null) ? 0 : postCode.hashCode ());
		result = prime * result + ( (state == null) ? 0 : state.hashCode ());
		result = prime * result + ( (street1 == null) ? 0 : street1.hashCode ());
		result = prime * result + ( (street2 == null) ? 0 : street2.hashCode ());
		return result;
	}

	
	public boolean equals (Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass () != obj.getClass ())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		}
		else if (!city.equals (other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		}
		else if (!country.equals (other.country))
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		}
		else if (!countryCode.equals (other.countryCode))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		}
		else if (!postCode.equals (other.postCode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		}
		else if (!state.equals (other.state))
			return false;
		if (street1 == null) {
			if (other.street1 != null)
				return false;
		}
		else if (!street1.equals (other.street1))
			return false;
		if (street2 == null) {
			if (other.street2 != null)
				return false;
		}
		else if (!street2.equals (other.street2))
			return false;
		return true;
	}

}
