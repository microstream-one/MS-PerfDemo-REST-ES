package com.microstream.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DTOAuthor(String mail, String firstname, String lastname)
{
	
}
