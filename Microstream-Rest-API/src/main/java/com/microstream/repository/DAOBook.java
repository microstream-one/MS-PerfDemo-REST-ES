package com.microstream.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.store.storage.types.StorageManager;

import com.microstream.concurrent.ReadWriteLocked;
import com.microstream.domain.Book;
import com.microstream.domain.indices.BookIndices;
import com.microstream.storage.Root;

import io.micronaut.eclipsestore.RootProvider;
import jakarta.inject.Singleton;
import one.microstream.gigamap.BitmapIndices;
import one.microstream.gigamap.GigaMap;
import one.microstream.gigamap.GigaQuery;


@Singleton
public class DAOBook extends ReadWriteLocked
{
	public final RootProvider<Root>	rootProvider;
	private final StorageManager	manager;
	
	private int						LIST_LIMIT	= 1000;
	private int						storeCount	= 1000;
	
	DAOBook(final RootProvider<Root> rootProvider, final StorageManager manager)
	{
		this.rootProvider = rootProvider;
		this.manager = manager;
	}
	
	public Book getBookByISBN(String isbn)
	{
		return rootProvider.root().gigaBooks.query(BookIndices.ISBNIndex.is(isbn)).findFirst().orElse(null);
	}
	
	public List<Book> searchBooksByTitle(String title)
	{
		return this.read(() ->
		{
			GigaQuery<Book> items =
				rootProvider.root().gigaBooks.query(BookIndices.titleIndex.containsIgnoreCase(title));
			
			if(items.count() > 0)
			{
				return items.toList(LIST_LIMIT);
			}
			
			return new ArrayList<>();
		});
	}
	
	public synchronized void insert(Book book)
	{
		Root root = rootProvider.root();
		
		root.gigaBooks.add(book);
		
		this.write(() ->
		{
			root.gigaBooks.store();
		});
	}
	
	public synchronized void insertForPM(Book book)
	{
		Root root = rootProvider.root();
		
		root.gigaBooksForInsert.add(book);
		
		this.write(() ->
		{
			root.gigaBooksForInsert.store();
		});
	}
	
	public synchronized void insertBatch(List<Book> books)
	{
		Root root = rootProvider.root();
		
		this.write(() ->
		{
			root.gigaBooks.addAll(books);
			root.gigaBooks.store();
		});
	}
	
	public long countBooks()
	{
		// TODO Auto-generated method stub
		return rootProvider.root().gigaBooks.size();
	}
	
	public long countBooksInsertPM()
	{
		// TODO Auto-generated method stub
		return rootProvider.root().gigaBooksForInsert.size();
	}
	
	public void flushBooks()
	{
		Root root = rootProvider.root();
		
		this.write(() ->
		{
			root.gigaBooks = GigaMap.New();
			
			final BitmapIndices<Book> indices = root.gigaBooks.index().bitmap();
			indices.add(BookIndices.titleIndex);
			indices.add(BookIndices.ISBNIndex);
			indices.add(BookIndices.pubDateIndex);
			indices.add(BookIndices.authorFirstnameIndex);
			indices.add(BookIndices.authorLastnameIndex);
			indices.add(BookIndices.authorEmailIndex);
			indices.setIdentityIndices(BookIndices.ISBNIndex);
			
			manager.store(root);
		});
	}
	
	public void flushInsertPM()
	{
		Root root = rootProvider.root();
		
		this.write(() ->
		{
			root.gigaBooksForInsert = GigaMap.New();
			
			final BitmapIndices<Book> indices = root.gigaBooksForInsert.index().bitmap();
			indices.add(BookIndices.titleIndex);
			indices.add(BookIndices.ISBNIndex);
			indices.add(BookIndices.pubDateIndex);
			indices.add(BookIndices.authorFirstnameIndex);
			indices.add(BookIndices.authorLastnameIndex);
			indices.add(BookIndices.authorEmailIndex);
			indices.setIdentityIndices(BookIndices.ISBNIndex);
			
			manager.store(root);
		});
		
	}
}
