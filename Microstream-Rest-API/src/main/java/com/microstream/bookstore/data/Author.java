
package com.microstream.bookstore.data;

import static com.microstream.bookstore.data.Named.Validation.validateName;
import static com.microstream.bookstore.data.NamedWithAddress.Validation.validateAddress;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Author entity which holds a name and an {@link Address}.
 * <p>
 * This type is immutable and therefor inherently thread safe.
 *
 */
public interface Author extends NamedWithAddress
{
	/**
	 * Pseudo-constructor method to create a new {@link Author} instance with default implementation.
	 *
	 * @param name
	 *            not empty, {@link Named.Validation#validateName(String)}
	 * @param address
	 *            not <code>null</code>, {@link NamedWithAddress.Validation#validateAddress(Address)}
	 * @return a new {@link Author} instance
	 */
	/**
	 * Get the name.
	 *
	 * @return name
	 */
	@JsonSerialize
	public String name();
	
	public static Author New(
		final String name,
		final Address address)
	{
		return new Default(
			validateName(name),
			validateAddress(address));
	}
	
	/**
	 * Default implementation of the {@link Author} interface.
	 *
	 */
	public static class Default extends NamedWithAddress.Abstract implements Author
	{
		Default(
			final String name,
			final Address address)
		{
			super(name, address);
		}
		
		@Override
		public String name()
		{
			return super.name();
		}
		
	}
	
}
