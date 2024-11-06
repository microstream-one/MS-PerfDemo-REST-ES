package com.microstream.domain;

import java.util.ArrayList;
import java.util.List;

import com.microstream.dto.DTOAddress;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Publisher
{
	private final String	UUID	= java.util.UUID.randomUUID().toString();
	private String			mail;
	private String			company;
	private List<Address>	addresses;
	
	public Publisher(String mail, String company, List<DTOAddress> addresses2)
	{
		super();
		this.mail = mail;
		this.company = company;
	
		List<Address> add = new ArrayList<>();
		addresses2.forEach(a -> {
			add.add(new Address(a));
		});
	}

	public String getMail()
	{
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	public String getCompany()
	{
		return company;
	}
	
	public void setCompany(String company)
	{
		this.company = company;
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
