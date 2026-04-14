package com.microstream.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.store.gigamap.lucene.LuceneIndex;
import org.eclipse.store.gigamap.types.BitmapIndices;
import org.eclipse.store.gigamap.types.GigaMap;
import org.eclipse.store.gigamap.types.GigaQuery;

import com.microstream.concurrent.ReadWriteLocked;
import com.microstream.domain.Book;
import com.microstream.domain.indices.BookIndices;
import com.microstream.storage.Root;
import com.microstream.storage.StorageServiceImpl;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;


@Singleton
public class DAOBook extends ReadWriteLocked
{
	@Inject private StorageServiceImpl storageService;
		
	private int						LIST_LIMIT	= 1000;
	
	public Book getBookByISBN(String isbn)
	{
		return storageService.provideRoot().gigaBooks.query(BookIndices.ISBNIndex.is(isbn)).findFirst().orElse(null);
	}
	
	public List<Book> searchBooksByTitle(String title)
	{
		return this.read(() ->
		{
            LuceneIndex<Book> luceneIndex = storageService.provideRoot().gigaBooks.index().get(LuceneIndex.class);

            List<Book> result = luceneIndex.query("title:" + title);

			return result;
		});
	}
	
	public synchronized void insert(Book book)
	{		
		storageService.provideRoot().gigaBooks.add(book);
		
		this.write(() ->
		{
			storageService.provideRoot().gigaBooks.store();
		});
	}
	
	public synchronized void insertForPM(Book book)
	{
		storageService.provideRoot().gigaBooksForInsert.add(book);
		
		this.write(() ->
		{
			storageService.provideRoot().gigaBooksForInsert.store();
		});
	}
	
	public synchronized void insertBatch(List<Book> books)
	{
		this.write(() ->
		{
			storageService.provideRoot().gigaBooks.addAll(books);
			storageService.provideRoot().gigaBooks.store();
		});
	}
	
	public long countBooks()
	{
		return storageService.provideRoot().gigaBooks.size();
	}
	
	public long countBooksInsertPM()
	{
		return storageService.provideRoot().gigaBooksForInsert.size();
	}
	
	public void flushBooks()
	{
		this.write(() ->
		{
			storageService.provideRoot().gigaBooks = GigaMap.New();
			
			final BitmapIndices<Book> indices = storageService.provideRoot().gigaBooks.index().bitmap();
			indices.add(BookIndices.ISBNIndex);
			indices.setIdentityIndices(BookIndices.ISBNIndex);
			
			storageService.provideStorageManager().store(storageService.provideRoot());
		});
	}
	
	public void flushInsertPM()
	{
		Root root = storageService.provideRoot();
		
		this.write(() ->
		{
			root.gigaBooksForInsert = GigaMap.New();
			
			final BitmapIndices<Book> indices = root.gigaBooksForInsert.index().bitmap();
			indices.add(BookIndices.ISBNIndex);
			indices.setIdentityIndices(BookIndices.ISBNIndex);
			
			storageService.provideStorageManager().store(root);
		});
		
	}
}
