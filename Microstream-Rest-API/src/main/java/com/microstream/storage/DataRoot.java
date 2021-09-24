
package com.microstream.storage;

import java.util.ArrayList;
import java.util.List;

import com.microstream.bookstore.data.Book;
import com.microstream.bookstore.data.Shop;

import one.microstream.reference.Lazy;


/**
 * MicroStream data root. Create your data model from here.
 *
 * @see <a href="https://manual.docs.microstream.one/">Reference Manual</a>
 */
public class DataRoot
{
	
	public Lazy<List<Book>> lazyBooks = Lazy.Reference(new ArrayList<Book>());
	public List<Book> books = new ArrayList<>();
	public List<Shop> shops = new ArrayList<>();
	
	public DataRoot()
	{
		super();
	}
	
	public Lazy<List<Book>> getLazyBooks()
	{
		if(this.lazyBooks == null)
		{
			this.lazyBooks = Lazy.Reference(new ArrayList<Book>());
		}
		return this.lazyBooks;
	}
	
	public void setLazyBooks(Lazy<List<Book>> lazyBooks)
	{
		this.lazyBooks = lazyBooks;
	}
	
	public List<Book> getBooks()
	{
		if(this.books == null)
		{
			this.books = new ArrayList<>();
		}
		return this.books;
	}
	
	public void setBooks(List<Book> books)
	{
		this.books = books;
	}
	
	public List<Shop> getShops()
	{
		return this.shops;
	}
	
	public void setShops(List<Shop> shops)
	{
		this.shops = shops;
	}
	
}
