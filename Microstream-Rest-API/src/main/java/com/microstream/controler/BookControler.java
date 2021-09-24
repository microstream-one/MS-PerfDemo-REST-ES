package com.microstream.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microstream.bookstore.data.Book;
import com.microstream.storage.MicroStream;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;


@Controller("/book")
public class BookControler
{
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index(String isbn)
	{
		try
		{

			Book book =
				MicroStream.root().getBooks().stream().filter(b -> b.isbn13().equals(isbn)).findFirst().orElse(null);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(book);
			return json;
		}
		catch(JsonProcessingException e)
		{
			e.printStackTrace();
			return "404";
		}

	}
}
