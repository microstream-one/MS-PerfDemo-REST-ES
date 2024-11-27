package com.microstream.controller;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import com.microstream.domain.Book;
import com.microstream.dto.DTOBook;
import com.microstream.repository.DAOBookLazy;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Controller("/lazybooks")
public class LazyBookController
{
	private final DAOBookLazy dao;
	
	public LazyBookController(DAOBookLazy dao)
	{
		this.dao = dao;
	}
	
	@Get("/search/{searchTerm}")
	HttpResponse<List<Book>> searchBook(@NonNull @NotBlank @PathVariable String searchTerm)
	{
		return HttpResponse.ok(dao.booksByTitle(searchTerm));
	}
	
	@Get("/{isbn}")
	Book getBookByISBN(@NonNull @NotBlank @PathVariable String isbn)
	{
		return dao.booksByISBN(isbn);
	}
	
	@ExecuteOn(BLOCKING)
	@Post
	HttpResponse<String> create(@NonNull @NotNull @Valid @Body DTOBook dto)
	{
		dao.insert(new Book(dto));
		return HttpResponse.ok("Successfully created");
	}
}
