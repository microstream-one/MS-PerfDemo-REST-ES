package com.microstream.controler;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microstream.bookstore.data.Book;
import com.microstream.storage.MicroStream;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;


@Controller("/avgprice")
public class PriceControler
{
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index()
	{
		try
		{
			List<Book> books = MicroStream.root().getBooks();
			Double price = 0.0;
			for(Book book : books)
			{
				price = price + book.retailPrice().getNumber().doubleValue();
			}
			price = price / books.size();
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(price);
			// System.out.println(LocalDateTime.now() + " [Rest API - AVG Price called]");
			return json;
		}
		catch(JsonProcessingException e)
		{
			e.printStackTrace();
			return "404";
		}

	}
}
