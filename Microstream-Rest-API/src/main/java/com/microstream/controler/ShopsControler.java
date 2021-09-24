package com.microstream.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microstream.bookstore.data.Book;
import com.microstream.bookstore.data.Shop;
import com.microstream.storage.MicroStream;
import com.microstream.transport.BookInShop;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;


@Controller("/bookinshops")
public class ShopsControler
{
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public String index()
	{
		try
		{
			List<Book> books = MicroStream.root().getBooks();
			List<Shop> shops = MicroStream.root().getShops();

			List<BookInShop> result = new ArrayList<>();

			books.forEach(book ->
			{
				BookInShop transport = new BookInShop();
				transport.setBook(book);
				transport.setShops(
					shops.stream().filter(s -> s.inventory().books().contains(book)).collect(Collectors.toList()));

				result.add(transport);
			});

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(result);
			// System.out.println(LocalDateTime.now() + " [Rest API - bookinshops called]");
			return json;
		}
		catch(JsonProcessingException e)
		{
			e.printStackTrace();
			return "404";
		}

	}
}
