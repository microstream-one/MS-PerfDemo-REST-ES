package com.microstream.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.serializer.collections.Empty;
import org.eclipse.store.storage.types.StorageManager;

import com.microstream.concurrent.ReadWriteLocked;
import com.microstream.domain.Book;
import com.microstream.domain.indices.BookIndices;
import com.microstream.storage.Root;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.eclipsestore.RootProvider;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import one.microstream.gigamap.GigaQuery;


@Singleton
public class DAOBook extends ReadWriteLocked
{
	public final RootProvider<Root>	rootProvider;
	private final StorageManager	manager;
	private int						storeCount	= 0;
	
	private int						LIST_LIMIT = 100;
	
	DAOBook(final RootProvider<Root> rootProvider, final StorageManager manager)
	{
		this.rootProvider = rootProvider;
		this.manager = manager;
	}
	
	public Book getBookISBN(String isbn)
	{
		return rootProvider.root().gigaBooks.query(BookIndices.ISBNIndex.is(isbn)).findFirst().orElse(null);
	}
	
	public List<Book> searchBooksTitle(String title)
	{
		return this.read(() -> 
		{
			GigaQuery<Book> items = rootProvider.root().gigaBooks
				.query(BookIndices.titleIndex.containsIgnoreCase(title));
			
			if(items.count() > 0)
			{
				return items.toList(LIST_LIMIT);
			}
			
			return new ArrayList<>();
		});
	}
	
	public List<Book> searchAuthorsBooks(String name)
	{
		return this.read(() -> 
		{
			GigaQuery<Book> items = rootProvider.root().gigaBooks
				.query(BookIndices.authorFirstnameIndex.containsIgnoreCase(name).or(BookIndices.authorLastnameIndex.containsIgnoreCase(name)));	
		
			if(items.count() > 0)
			{
				return items.toList(LIST_LIMIT);
			}
			
			return new ArrayList<>();
		});
	}
	
	public List<Book> searchAuthorsBooksFirstnameOnly(String name)
	{
		return this.read(() -> 
		{
			GigaQuery<Book> items = rootProvider.root().gigaBooks
				.query(BookIndices.authorFirstnameIndex.containsIgnoreCase(name));	
			
			if(items.count() > 0)
			{
				return items.toList(LIST_LIMIT);
			}
			
			return new ArrayList<>();
		});
	}
	
	public List<Book> searchAuthorsBooksLastnameOnly(String name)
	{
		return this.read(() -> 
		{
			GigaQuery<Book> items = rootProvider.root().gigaBooks
				.query(BookIndices.authorLastnameIndex.containsIgnoreCase(name));	
			
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
			if(storeCount > 1000)
			{
				root.gigaBooks.store();
				storeCount = 0;
			}
			else
			{
				storeCount++;
			}
		});
	}
	
	public List<Book> pageBooks(@NonNull @NotBlank int limit)
	{
		Root root = rootProvider.root();
		
		try(Stream<Book> stream = root.gigaBooks.query().stream())
		{
			return stream.limit(limit).collect(Collectors.toList());
		}
	}
	
	public Long countBooks()
	{
		AtomicLong count = new AtomicLong();
		
		this.read(() -> 
		{
			count.addAndGet(rootProvider.root().gigaBooks.query().count());
		});
		
		return count.get();
	}

	public long getSize() {
		// TODO Auto-generated method stub
		return rootProvider.root().gigaBooks.size();
	}
}
