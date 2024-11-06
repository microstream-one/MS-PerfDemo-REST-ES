package com.microstream.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.eclipse.serializer.reference.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microstream.domain.Author;
import com.microstream.domain.Book;
import com.microstream.domain.Publisher;
import com.microstream.repository.DAOBook;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import one.microstream.gigamap.GigaMap;

@Singleton
public class StorageInit {
	
	private static final Logger LOG = LoggerFactory.getLogger(StorageInit.class);
	
	@Inject
	DAOBook bookDao;
	
	public void generateStorage() {
		
		Random rnd = new Random();
		RandomString rnString = new RandomString(10, rnd);
		
//		LOG.info("Number of existing authors", this.authorDao.getSize());
//		
//		List<Author> authorsToBeAdded = new ArrayList<>();
//		
//		for(long i = this.authorDao.getSize(); i <= 10_000_000; i++)
//		{
//			Author a = new Author();
//			a.setFirstname(rnString.nextString());
//			a.setLastname(rnString.nextString());
//			a.setId(UUID.randomUUID());
//			authorsToBeAdded.add(a);
//			
//			if(i % 10 == 0)
//			{
//				LOG.info("Generated author {}", i);
//			}
//			
//			if(i % 100_000 == 0)
//			{
//				authorDao.addAuthors(authorsToBeAdded);
//				authorsToBeAdded.clear();
//			}
//				
//		}
//		
//		db.collectGarbage();
//		
//		GigaMap<Author> authors = db.getRoot().getAuthors();
		
        LOG.info("Number of pre-existing books: {}", this.bookDao.getSize());
		
		for(long i = this.bookDao.getSize(); i <= 1_000_000; i++)
		{
			Book b = new Book();
//			authors.query().stream().skip(rnd.nextLong(authors.size())).findFirst().get();
//			
//			b.setAuthor(
//					authors
//					.query()
//					.stream()
//					.skip(
//							rnd.nextLong(authors.size())
//						)
//					.findFirst()
//					.get());
			
			b.setAvailableQuantity((int) Math.round(rnd.nextDouble() * 100));
			b.setPrice(BigDecimal.valueOf(rnd.nextDouble() * 10));
			b.setEdition((int) Math.round(rnd.nextDouble() * 10));
			b.setISBN(rnString.nextString());
			b.setPublicationDate(LocalDate.now());
			b.setTitle(rnString.nextString());
			
			
			//Author a = new Author(rnString.nextString(), rnString.nextString(), rnString.nextString());
			//b.setAuthor(a);
			
			//Publisher p = new Publisher(rnString.nextString(), rnString.nextString());
			//b.setPublisher(p);
			
			bookDao.insert(b);
			
			if(i % 1000 == 0)
			{
				LOG.info("Generated book {}", i);
			}
			
		}
		
        LOG.info("Number of existing books: {}", this.bookDao.getSize());
        
//        List<Customer> customersToBeAdded = new ArrayList<>();
//        
//        for(int i = customerDao.getSize(); i <= 200_000; i++)
//        {
//            Customer c = new Customer();
//            c.setId(UUID.randomUUID());
//            c.setFirstname(rnString.nextString());
//            c.setLastname(rnString.nextString());
//            customersToBeAdded.add(c);
//            
//            if(i % 1000 == 0)
//            {
//                LOG.info("Generated customer {}", i);
//            }
//            
//            if(i % 100_000 == 0)
//            {
//                customerDao.addCustomers(customersToBeAdded);
//                customersToBeAdded.clear();
//            }
//        }
		
	}

}
