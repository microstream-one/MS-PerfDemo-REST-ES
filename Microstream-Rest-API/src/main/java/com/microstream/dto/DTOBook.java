package com.microstream.dto;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DTOBook(String isbn, String title, LocalDate publicationDate, 
	int	edition,int availableQuantity, double price, DTOAuthor author, DTOPublisher publisher)
{
	
}
