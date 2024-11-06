package com.microstream.dto;

import java.util.List;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DTOPublisher(String mail, String company, List<DTOAddress> addresses)
{
	
}
