package com.microstream.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.microstream.domain.Book;
import com.microstream.dto.DTOBook;
import com.microstream.repository.DAOBook;

import io.micrometer.observation.annotation.Observed;
import io.micronaut.core.annotation.Blocking;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import one.microstream.gigamap.GigaMap;


@Observed
@Controller("/books")
public class BookController
{
	private final DAOBook dao;
	
	public BookController(DAOBook dao)
	{
		this.dao = dao;
	}
	
	@Get("/count")
	HttpResponse<Long> countBooks()
	{
		return HttpResponse.ok(dao.countBooks());
	}
	
	@Get("/search/{searchTerm}")
	List<Book> searchBook(@NonNull @NotBlank @PathVariable String searchTerm)
	{
		return dao.searchBooksByTitle(searchTerm);
	}
	
	@Get("/{isbn}")
	Book getBook(@NonNull @NotBlank @PathVariable String isbn)
	{
		return dao.getBookByISBN(isbn);
	}
	
	@Put
	HttpResponse<String> create(@NonNull @Valid @Body DTOBook dto)
	{
		dao.insert(new Book(dto));
		
		return HttpResponse.ok("Successfully created");
	}
	
	@Put("/batch")
	HttpResponse<String> createBatch(@NotEmpty @Body List<@Valid @NonNull DTOBook> dto)
	{
		List<Book> collect = dto.stream().map(bdto -> new Book(bdto)).collect(Collectors.toList());
		
		dao.insertBatch(collect);
		
		return HttpResponse.ok("Successfully created");
	}
	
	@Delete("/flushdatabase")
	HttpResponse<String> flushDatabase()
	{
		dao.flushBooks();
		
		return HttpResponse.ok("Data successfully deleted"); 
	}
}
