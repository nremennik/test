package service;

import models.Author;
import models.Book;
import models.Genre;
import models.Reader;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


class BookSuggestionService
{

    private final Set<Book> books;
    private final Set<Reader> readers;
    Set<String> sugBooks = new HashSet<>();

    public BookSuggestionService(Set<Book> books, Set<Reader> readers)
    {
        this.books = books;
        this.readers = readers;
    }

    Set<String> suggestBooks(Reader reader)
    {
//         Set<String> suggestedBooks = books.stream()
//                .filter(b -> b.getRating() > 4)
//                .filter(b -> reader.getFavouriteBooks().stream().anyMatch(fav -> fav.getGenre().equals(b.getGenre())))
//                .map(b -> b.getTitle())
//                .collect(Collectors.toSet());
//
//        return suggestedBooks;
        Set<Genre> favGanres = reader.getFavouriteGenres();

        for (Book favouriteBook : books)
        {
            if ((favouriteBook.getRating() > 3)&& favGanres.contains(favouriteBook.getGenre()))
            {
                for(Reader r:readers)
                {
                    if((r.getAge()==reader.getAge()&&!r.equals(reader)))
                    sugBooks.add(favouriteBook.getTitle());
                    break;
                }
            }

        }
        return sugBooks;

    }

    Set<String> suggestBooks(Reader reader, int rating)
    {
        Set<Genre> favGanres = reader.getFavouriteGenres();

        for(Book favouriteBook : books)
        {
            if(favouriteBook.getRating()==rating&&favGanres.contains(favouriteBook.getGenre()))
            {
                for(Reader r: readers)
                {
                    if((readers.contains(reader.getAge())&&!r.equals(reader))&&r.getFavouriteBooks().contains(favouriteBook))
                    {
                        sugBooks.add(favouriteBook.getTitle());
                        break;
                    }
                }
            }
        }
        return sugBooks;
    }


    Set<String> suggestBooks(Reader reader, Author author)
    {

        for(Book favouriteBook: books)
        {
            if(favouriteBook.getRating()>3&&reader.getFavouriteGenres().contains(favouriteBook.getGenre())&&favouriteBook.getAuthor().equals(author))
            {
                for(Reader r:readers)
                {
                    if((r.getAge()==reader.getAge()&&!reader.equals(r))&&r.getFavouriteBooks().contains(favouriteBook))
                    {
                        sugBooks.add(favouriteBook.getTitle());
                        break;
                    }
                }
            }
        }

            return sugBooks;
    }

}

