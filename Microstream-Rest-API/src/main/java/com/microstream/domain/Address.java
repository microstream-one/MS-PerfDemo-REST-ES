package com.microstream.domain;

import com.microstream.dto.DTOAddress;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Address
{
	private final String	UUID	= java.util.UUID.randomUUID().toString();
	private String			address;
	private String			address2;
	private String			zip;
	private String			city;
	private String			country;
	
	public Address(String address, String address2, String zip, String city, String country)
	{
		super();
		this.address = address;
		this.address2 = address2;
		this.zip = zip;
		this.city = city;
		this.country = country;
	}
	
	public Address(DTOAddress a) {
		this.address = a.address();
		this.address2 = a.address2();
		this.city = a.city();
		this.country = a.country();
		this.zip = a.zip();
	}

	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getAddress2()
	{
		return address2;
	}
	
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getUUID()
	{
		return UUID;
	}
	
}
