package com.microstream.controller;

import java.util.List;

import com.microstream.domain.Book;
import com.microstream.dto.DTOBook;
import com.microstream.repository.DAOBook;

import io.micrometer.observation.annotation.Observed;
import io.micronaut.core.annotation.Blocking;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


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
	
	@Get("/page/{limit}")
	List<Book> pageAllBooks(@NonNull @NotBlank @PathVariable int limit)
	{
		return dao.pageBooks(limit);
	}
	
	@Get("/search/{searchTerm}")
	List<Book> searchBook(@NonNull @NotBlank @PathVariable String searchTerm)
	{
		return dao.searchBooksTitle(searchTerm);
	}
	
	@Get("/search/author/{mail}")
	List<Book> searchBookOfAuthor(@NonNull @NotBlank @PathVariable String mail)
	{
		return dao.searchAuthorsBooks(mail);
	}
	
	@Get("/search/author/firstname/{name}")
	List<Book> searchBookOfAuthorFirstname(@NonNull @NotBlank @PathVariable String name)
	{
		return dao.searchAuthorsBooksFirstnameOnly(name);
	}
	
	@Get("/search/author/lastname/{name}")
	List<Book> searchBookOfAuthorLastname(@NonNull @NotBlank @PathVariable String name)
	{
		return dao.searchAuthorsBooksLastnameOnly(name);
	}
	
	@Get("/{isbn}")
	Book getBook(@NonNull @NotBlank @PathVariable String isbn)
	{
		return dao.getBookISBN(isbn);
	}
	
	@Put
	HttpResponse<String> create(@NonNull @Valid @Body DTOBook dto)
	{
		dao.insert(new Book(dto));
		return HttpResponse.ok("Successfully created");
	}
}
