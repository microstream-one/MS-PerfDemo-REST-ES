
package com.microstream.storage;

import org.eclipse.store.gigamap.types.BitmapIndices;
import org.eclipse.store.gigamap.types.GigaMap;

import com.microstream.domain.Book;
import com.microstream.domain.indices.BookIndices;

import io.micronaut.serde.annotation.Serdeable;


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
		indices.add(BookIndices.ISBNIndex);
        BookIndices.registerLuceneIndex(gigaBooks);
		indices.setIdentityIndices(BookIndices.ISBNIndex);
		
		final BitmapIndices<Book> indicesForInsert = gigaBooksForInsert.index().bitmap();
		indicesForInsert.add(BookIndices.ISBNIndex);
        BookIndices.registerLuceneIndex(gigaBooks);
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
