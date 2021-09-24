package com.microstream;

import com.microstream.storage.DB;

import io.micronaut.runtime.Micronaut;


public class Application
{
	
	public static void main(String[] args)
	{
		Micronaut.run(Application.class, args);
		new DB();
	}
}
