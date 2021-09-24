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


@Controller("/lazybooks")
public class LazyBooksControler
{
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index()
	{
		try
		{
			System.gc();
			List<Book> books = MicroStream.root().getLazyBooks().get();
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(books);
			// System.out.println(LocalDateTime.now() + " [Rest API - LAZY Books called]");
			return json;
		}
		catch(JsonProcessingException e)
		{
			e.printStackTrace();
			return "404";
		}
		
	}
}
