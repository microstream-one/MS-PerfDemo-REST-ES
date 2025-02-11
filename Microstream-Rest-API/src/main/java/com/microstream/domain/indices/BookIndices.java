package com.microstream.domain.indices;

import java.time.LocalDate;

import com.microstream.domain.Book;

import one.microstream.gigamap.IndexerLocalDate;
import one.microstream.gigamap.IndexerString;

public class BookIndices
{
	public final static IndexerString<Book> ISBNIndex = new IndexerString.Abstract<Book>()
	{
		public String name()
		{
			return "isbn";
		}
		
		@Override
		public String getString(final Book entity)
		{
			return entity.getISBN();
		}
	};
	
	public final static IndexerString<Book> titleIndex = new IndexerString.Abstract<Book>()
	{
		public String name()
		{
			return "title";
		}
		
		@Override
		public String getString(final Book entity)
		{
			return entity.getTitle();
		}
	};
	
	public final static IndexerLocalDate<Book> pubDateIndex = new IndexerLocalDate.Abstract<Book>()
	{
		public String name()
		{
			return "pubDate";
		}
		
		@Override
		protected LocalDate getLocalDate(final Book entity)
		{
			return entity.getPublicationDate();
		}
	};
	
	public final static IndexerString<Book> authorEmailIndex = new IndexerString.Abstract<Book>()
	{
		public String name()
		{
			return "authorEmail";
		}
		
		@Override
		public String getString(final Book entity)
		{
			return entity.getAuthor().getMail();
		}
	};
	
	public final static IndexerString<Book> authorFirstnameIndex = new IndexerString.Abstract<Book>()
	{
		public String name()
		{
			return "authorFirstname";
		}
		
		@Override
		public String getString(final Book entity)
		{
			return entity.getAuthor().getFirstname();
		}
	};
	
	public final static IndexerString<Book> authorLastnameIndex = new IndexerString.Abstract<Book>()
	{
		public String name()
		{
			return "authorLastname";
		}
		
		@Override
		public String getString(final Book entity)
		{
			return entity.getAuthor().getLastname();
		}
	};
}
