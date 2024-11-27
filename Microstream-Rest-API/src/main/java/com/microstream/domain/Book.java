package com.microstream.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.microstream.dto.DTOBook;

import io.micronaut.serde.annotation.Serdeable;


@Serdeable
public class Book
{
	private String		ISBN;
	private String		title;
	private LocalDate	publicationDate;
	private int			edition;
	
	private int			availableQuantity;
	private BigDecimal	price;
	
	private Author		author;
	private Publisher	publisher;
	
	public Book(DTOBook dto)
	{
		super();
		
		this.ISBN = dto.isbn();
		this.title = dto.title();
		this.publicationDate = dto.publicationDate();
		this.edition = dto.edition();
		this.availableQuantity = dto.availableQuantity();
		
		this.price = new BigDecimal(dto.price());
		this.author = new Author(
			dto.author().mail(),
			dto.author().firstname(),
			dto.author().lastname(),
			dto.author().addresses());
		this.publisher = new Publisher(dto.publisher().mail(), dto.publisher().company(), dto.publisher().addresses());
		
	}
	
	public Book()
	{
		super();
	}
	
	public int getAvailableQuantity()
	{
		return availableQuantity;
	}
	
	public void setAvailableQuantity(int availableQuantity)
	{
		this.availableQuantity = availableQuantity;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public String getISBN()
	{
		return ISBN;
	}
	
	public void setISBN(String isbn)
	{
		this.ISBN = isbn;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public LocalDate getPublicationDate()
	{
		return publicationDate;
	}
	
	public void setPublicationDate(LocalDate publicationDate)
	{
		this.publicationDate = publicationDate;
	}
	
	public int getEdition()
	{
		return edition;
	}
	
	public void setEdition(int edition)
	{
		this.edition = edition;
	}
	
	public Author getAuthor()
	{
		return author;
	}
	
	public void setAuthor(Author author)
	{
		this.author = author;
	}
	
	public Publisher getPublisher()
	{
		return publisher;
	}
	
	public void setPublisher(Publisher publisher)
	{
		this.publisher = publisher;
	}
	
}
