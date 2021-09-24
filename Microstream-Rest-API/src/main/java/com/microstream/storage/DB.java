
package com.microstream.storage;

import java.util.stream.Collectors;

import com.microstream.bookstore.BookStoreDemo;
import com.microstream.bookstore.data.Data;
import com.microstream.bookstore.data.RandomDataAmount;


public final class DB
{
	public static Data data;
	
	public DB()
	{
		System.out.println("Lazy: " + MicroStream.root().getLazyBooks().get().size());
		System.out.println("Books: " + MicroStream.root().getBooks().size());
		if(MicroStream.root().getBooks().isEmpty() || MicroStream.root().getShops().isEmpty())
		{
			MicroStream.root().getBooks().clear();
			MicroStream.root().getLazyBooks().get().clear();
			final BookStoreDemo bookStoreDemo = new BookStoreDemo(
				RandomDataAmount.Medium());
			DB.data = bookStoreDemo.data();
			MicroStream.root().setBooks(DB.data.books().all().stream().collect(Collectors.toList()));
			MicroStream.root().getLazyBooks().get().addAll(DB.data.books().all().stream().collect(Collectors.toList()));
			MicroStream.root().setShops(DB.data.shops().all().stream().collect(Collectors.toList()));
			MicroStream.storageManager().store(MicroStream.root());
			MicroStream.storageManager().store(MicroStream.root().getBooks());
			MicroStream.storageManager().store(MicroStream.root().getLazyBooks().get());
			MicroStream.storageManager().store(MicroStream.root().getShops());
			System.out.println("[Database loaded]");

		}
		System.out.println("Lazy: " + MicroStream.root().getLazyBooks().get().size());
		System.out.println("Books: " + MicroStream.root().getBooks().size());
		System.out.println("Shops: " + MicroStream.root().getShops().size());
	}
}
