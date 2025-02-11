
package com.microstream.storage;

import com.microstream.domain.Book;
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
	public GigaMap<Book>	gigaBooks			= GigaMap.New();
	public GigaMap<Book>	gigaBooksForInsert	= GigaMap.New();
	
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
		indices.setIdentityIndices(BookIndices.ISBNIndex);
		
		final BitmapIndices<Book> indicesForInsert = gigaBooksForInsert.index().bitmap();
		indicesForInsert.add(BookIndices.titleIndex);
		indicesForInsert.add(BookIndices.ISBNIndex);
		indicesForInsert.add(BookIndices.pubDateIndex);
		indicesForInsert.add(BookIndices.authorFirstnameIndex);
		indicesForInsert.add(BookIndices.authorLastnameIndex);
		indicesForInsert.add(BookIndices.authorEmailIndex);
		indicesForInsert.setIdentityIndices(BookIndices.ISBNIndex);
	}
	
	public GigaMap<Book> getGigaBooks()
	{
		return gigaBooks;
	}
	
	public void setGigaBooks(GigaMap<Book> gigaBooks)
	{
		this.gigaBooks = gigaBooks;
	}
	
	public GigaMap<Book> getGigaBooksForInsert()
	{
		return gigaBooksForInsert;
	}
	
	public void setGigaBooksForInsert(GigaMap<Book> gigaBooksForInsert)
	{
		this.gigaBooksForInsert = gigaBooksForInsert;
	}
	
}
