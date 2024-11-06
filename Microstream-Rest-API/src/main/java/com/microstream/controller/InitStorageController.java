package com.microstream.controller;

import java.util.List;

import com.microstream.domain.Book;
import com.microstream.dto.DTOBook;
import com.microstream.repository.DAOBook;
import com.microstream.utils.StorageInit;

import io.micronaut.core.annotation.Blocking;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


@Controller("/init")
public class InitStorageController
{
	private final StorageInit sInit;
	
	public InitStorageController(StorageInit sInit)
	{
		this.sInit = sInit;
	}
	
	@Post
	HttpResponse countBooks()
	{
		sInit.generateStorage();
		
		return HttpResponse.ok();
	}

}
