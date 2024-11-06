package com.microstream.dto;

import java.util.List;

import com.microstream.domain.Address;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DTOAuthor(String mail, String firstname, String lastname, List<DTOAddress> addresses)
{
	
}
