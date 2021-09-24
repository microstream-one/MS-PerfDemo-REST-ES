
package com.microstream.bookstore.data;

import static com.microstream.bookstore.data.Named.Validation.validateName;
import static com.microstream.bookstore.data.NamedWithAddress.Validation.validateAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import one.microstream.reference.Lazy;


/**
 * Shop entity which holds a name, {@link Address}, {@link Employee}s and an {@link Inventory}.
 * <p>
 * This type is immutable and therefor inherently thread safe.
 *
 */
public interface Shop extends NamedWithAddress
{
	/**
	 * Get the employees.
	 *
	 * @return a {@link Stream} of {@link Employee}s
	 */
	public Stream<Employee> employees();

	/**
	 * Get the inventory.
	 *
	 * @return the inventory
	 */
	public Inventory inventory();

	/**
	 * Clears all {@link Lazy} references held by this shop.
	 * This frees the used memory but you do not lose the persisted data. It is loaded again on demand.
	 */
	public void clear();

	/**
	 * Pseudo-constructor method to create a new {@link Shop} instance with default implementation.
	 *
	 * @param name
	 *            not empty, {@link Named.Validation#validateName(String)}
	 * @param address
	 *            not <code>null</code>
	 * @return a new {@link Shop} instance
	 */
	public static Shop New(
		final String name,
		final Address address)
	{
		return new Default(
			validateName(name),
			validateAddress(address));
	}

	/**
	 * Default implementation of the {@link Shop} interface.
	 *
	 */
	public static class Default extends NamedWithAddress.Abstract implements Shop
	{
		private final List<Employee> employees;
		private final Inventory inventory;

		Default(
			final String name,
			final Address address)
		{
			super(name, address);
			this.employees = new ArrayList<>();
			this.inventory = new Inventory.Default();
		}

		Default(
			final String name,
			final Address address,
			final List<Employee> employees,
			final Inventory inventory)
		{
			super(name, address);
			this.employees = new ArrayList<>(employees);
			this.inventory = inventory;
		}

		@Override
		public Stream<Employee> employees()
		{
			return this.employees.stream();
		}

		@Override
		public Inventory inventory()
		{
			return this.inventory;
		}

		@Override
		public void clear()
		{
			// clearIfStored(this.inventory);
		}

	}

}
