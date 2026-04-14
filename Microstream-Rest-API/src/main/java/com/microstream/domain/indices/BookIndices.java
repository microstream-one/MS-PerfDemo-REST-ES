package com.microstream.domain.indices;

import java.nio.file.Paths;

import org.eclipse.store.gigamap.lucene.LuceneContext;
import org.eclipse.store.gigamap.lucene.LuceneIndex;
import org.eclipse.store.gigamap.types.BinaryIndexerString;
import org.eclipse.store.gigamap.types.GigaMap;

import com.microstream.domain.Book;

import jakarta.inject.Singleton;

@Singleton
public class BookIndices
{
	
	public final static BinaryIndexerString<Book> ISBNIndex = new BinaryIndexerString.Abstract<Book>()
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

    public static void registerLuceneIndex(GigaMap<Book> map)
    {
        LuceneContext<Book> luceneContext = LuceneContext.New(
                new BookDocPopulator() // our document populator
        );

        LuceneIndex.Category<Book> category = LuceneIndex.Category(luceneContext);
        map.index().register(category);
    }
}
