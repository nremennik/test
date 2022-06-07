/*
* BOOKS SUGGESTION SERVICE
Introduction
The service suggests books to readers based on the book's ratings, reader's favourite genres, book's author and the preferences of other readers of the same age.
Your job is to fulfill all requirements, which are described below. There are several unit tests that will help you to verify your solution. Please feel free to use any external dependencies by including them in pom.xml descriptor.
* main receives two json files Set<Book> and Set<Reader> in command line, create docker
Problem Statement
Your job is to correctly implement all the methods in the BookSuggestionService class, so that they meet the following requirements:
The method Set<String> suggestBooks(Reader reader) should return titles of all the books that meet all the following criteria:
	1	Have a rating of four or higher
	2	Belong to one of the reader's favourite genres
	3	Belong to favourite books' list of at least one different reader of the same age
The method Set<String> suggestBooks(Reader reader, Author author) should return titles of all the books that meet all the following criteria:
	1	Have a rating of four or higher
	2	Belong to one of the reader's favourite genres
	3	Belong to favourite books' list of at least one different reader of the same age
	4	Have been written by the author specified in the method argument
The method Set<String> suggestBooks(Reader reader, int rating) should return titles of all the books that meet all the following criteria:
	1	Have exactly the rating specified in the method argument
	2	Belong to one of the reader's favourite genres
	3	Belong to favourite books' list of at least one different reader of the same age
* */

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        List<String> files = new ArrayList<>();
        InputStream inputStreamFile;
        Set<Book> books = new HashSet<>();

        if (args.length < 2)
            System.out.println("Needed arguments: first argument Set<Book> and second argument Set<Readers>");
        else
        {
            try
            {
                inputStreamFile = openFile(args[0]);
                books=parseJsonToBook(inputStreamFile);
                inputStreamFile.close();

            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        }

    }

    static InputStream openFile(String fileName) throws FileNotFoundException
    {
        InputStream is = null;

        try
        {
            is = new FileInputStream(fileName);
        }
        catch (Exception ignored)
        {
        }
        if (is == null)
        {
            is = Main.class.getResourceAsStream(fileName);
        }
        if (is == null)
        {
            System.out.printf("Cannot open file %s.\n", fileName);
        }
        return (is);
    }

    static Set<Book> parseJsonToBook(InputStream is) throws IOException, IOException
    {

        // Create and configure an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Set<Book> books = new HashSet<>();

        // Create a JsonParser instance
        try (JsonParser jsonParser = mapper.getFactory().createParser(is)) {

            // Check the first token
            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected content to be an array");
            }

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                // Read a contact instance using ObjectMapper and do something with it
                Book book= mapper.readValue(jsonParser, Book.class);
                books.add(book);
            }
        }
        return books;
    }
}
