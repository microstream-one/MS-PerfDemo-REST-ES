package com.microstream.repository;

import java.util.List;

import com.microstream.domain.Book;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;

public class DAOBookLazy
{

	public List<Book> booksByTitle(@NonNull @NotBlank String searchTerm)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Book booksByISBN(@NonNull @NotBlank String isbn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Book book)
	{
		// TODO Auto-generated method stub
		
	}
	
}
