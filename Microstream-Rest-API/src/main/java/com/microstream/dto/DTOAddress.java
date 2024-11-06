package com.microstream.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DTOAddress(String address, String address2, String zip, String city, String country)
{
	
}
