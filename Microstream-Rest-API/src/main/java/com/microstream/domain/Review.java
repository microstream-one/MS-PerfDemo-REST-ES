package com.microstream.domain;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Review
{
	private final String	UUID	= java.util.UUID.randomUUID().toString();
	private String			review;
	private String			rating;
	private final LocalDate	created	= LocalDate.now();
	
	public Review(String review, String rating)
	{
		super();
		this.review = review;
		this.rating = rating;
	}
	
	public String getReview()
	{
		return review;
	}
	
	public void setReview(String review)
	{
		this.review = review;
	}
	
	public String getRating()
	{
		return rating;
	}
	
	public void setRating(String rating)
	{
		this.rating = rating;
	}
	
	public String getUUID()
	{
		return UUID;
	}
	
	public LocalDate getCreated()
	{
		return created;
	}
	
}
