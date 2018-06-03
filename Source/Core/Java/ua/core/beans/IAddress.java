package ua.core.beans;


public interface IAddress {
	
	public String	getStreet1();
	public String	getStreet2();
	public String	getCity();
	public String	getState();
	public String	getPostCode();
	public String	getCountry();
	public String	getCountryCode();
	
	public void		setStreet1		(String street1);
	public void		setStreet2		(String street2);
	public void		setCity			(String city);
	public void		setState		(String state);
	public void		setPostCode 	(String postCode);
	public void		setCountry		(String country);
	public void		setCountryCode	(String countryCode);
}
