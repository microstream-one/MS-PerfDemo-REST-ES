package com.microstream.domain;

import io.micronaut.serde.annotation.Serdeable;


@Serdeable
public class Customer
{
	private final String	UUID	= java.util.UUID.randomUUID().toString();
	private String			mail;
	private String			firstname;
	private String			lastname;
	
	private Address			invoiceAddress;
	private Address			shipAddress;
	
	public String getMail()
	{
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	
	public Address getInvoiceAddress()
	{
		return invoiceAddress;
	}
	
	public void setInvoiceAddress(Address invoiceAddress)
	{
		this.invoiceAddress = invoiceAddress;
	}
	
	public Address getShipAddress()
	{
		return shipAddress;
	}
	
	public void setShipAddress(Address shipAddress)
	{
		this.shipAddress = shipAddress;
	}
	
	public String getUUID()
	{
		return UUID;
	}
	
}
