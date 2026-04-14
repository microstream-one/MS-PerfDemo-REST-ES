package com.microstream.domain.indices;

import com.microstream.domain.Book;
import org.apache.lucene.document.Document;
import org.eclipse.store.gigamap.lucene.DocumentPopulator;

public class BookDocPopulator extends DocumentPopulator<Book>
{
	@Override
    public void populate(Document document, Book book)
    {
        document.add(createTextField("title", book.getTitle()));
        document.add(createTextField("authorEmail", book.getAuthor().getMail()));
        document.add(createTextField("authorLastname", book.getAuthor().getLastname()));
        document.add(createTextField("pubDate", book.getPublicationDate().toString()));
    }
}
