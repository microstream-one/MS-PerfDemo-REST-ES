package com.microstream.domain.indices;

import java.time.LocalDate;

import com.microstream.domain.Book;

import one.microstream.gigamap.Indexer;

public class BookIndices
{
	public final static Indexer.AbstractString<Book> ISBNIndex = new Indexer.AbstractString<>()
	{
		public String name()
		{
			return "isbn";
		}
		
		@Override
		public String indexEntity(final Book entity)
		{
			return entity.getISBN();
		}
	};
	
	public final static Indexer.AbstractString<Book> titleIndex = new Indexer.AbstractString<>()
	{
		public String name()
		{
			return "title";
		}
		
		@Override
		public String indexEntity(final Book entity)
		{
			return entity.getTitle();
		}
	};
	
	public final static Indexer.AbstractLocalDate<Book> pubDateIndex = new Indexer.AbstractLocalDate<>()
	{
		public String name()
		{
			return "pubDate";
		}
		
		@Override
		protected LocalDate getDate(final Book entity)
		{
			return entity.getPublicationDate();
		}
	};
	
	public final static Indexer.AbstractString<Book> authorEmailIndex = new Indexer.AbstractString<>()
	{
		public String name()
		{
			return "authorEmail";
		}
		
		@Override
		public String indexEntity(final Book entity)
		{
			return entity.getAuthor().getMail();
		}
	};
	
	public final static Indexer.AbstractString<Book> authorFirstnameIndex = new Indexer.AbstractString<>()
	{
		public String name()
		{
			return "authorFirstname";
		}
		
		@Override
		public String indexEntity(final Book entity)
		{
			return entity.getAuthor().getFirstname();
		}
	};
	
	public final static Indexer.AbstractString<Book> authorLastnameIndex = new Indexer.AbstractString<>()
	{
		public String name()
		{
			return "authorLastname";
		}
		
		@Override
		public String indexEntity(final Book entity)
		{
			return entity.getAuthor().getLastname();
		}
	};
}
