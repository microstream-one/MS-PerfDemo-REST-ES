
package com.microstream.storage;

import java.util.Arrays;

import com.microstream.domain.Book;
import com.microstream.domain.Books;
import com.microstream.domain.indices.BookIndices;

import io.micronaut.serde.annotation.Serdeable;
import one.microstream.gigamap.BitmapIndices;
import one.microstream.gigamap.GigaMap;


/**
 * MicroStream data root. Create your data model from here.
 *
 * @see <a href="https://manual.docs.microstream.one/">Reference Manual</a>
 */
@Serdeable
public class Root
{
	public GigaMap<Book>	gigaBooks	= GigaMap.New();
	public Books			books		= new Books();
	
	public Root()
	{
		super();
		
		final BitmapIndices<Book> indices = gigaBooks.index().bitmap();
		indices.add(BookIndices.titleIndex);
		indices.add(BookIndices.ISBNIndex);
		indices.add(BookIndices.pubDateIndex);
		indices.add(BookIndices.authorFirstnameIndex);
		indices.add(BookIndices.authorLastnameIndex);
		indices.add(BookIndices.authorEmailIndex);
		indices.setIdentityIndices(Arrays.asList(BookIndices.ISBNIndex));
	}
		
	public GigaMap<Book> getGigaBooks()
	{
		return gigaBooks;
	}
	
	public void setGigaBooks(GigaMap<Book> gigaBooks)
	{
		this.gigaBooks = gigaBooks;
	}

	public Books getBooks()
	{
		return books;
	}

	public void setBooks(Books books)
	{
		this.books = books;
	}
	
	
	
}
