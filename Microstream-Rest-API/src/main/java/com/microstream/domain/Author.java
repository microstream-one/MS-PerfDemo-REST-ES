package com.microstream.domain;

import java.util.ArrayList;
import java.util.List;

import com.microstream.dto.DTOAddress;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Author
{
	private final String	UUID	= java.util.UUID.randomUUID().toString();
	private String			mail;
	private String			firstname;
	private String			lastname;
	private List<Address> 	addresses;
	
	public Author(String mail, String firstname, String lastname, List<DTOAddress> addresses)
	{
		super();
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
		
		List<Address> add = new ArrayList<>();
		addresses.forEach(a -> {
			add.add(new Address(a));
		});
		
		this.addresses = add;
	}
	
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
	
	public String getUUID()
	{
		return UUID;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
}
