package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @JsonProperty("author")
    private final Author author;
    @JsonProperty("title")
    private final String title;
    @JsonProperty("isbn")
    private final String isbn;
    @JsonProperty("genre")
    private final Genre genre;
    @JsonProperty("rating")
    private int rating;

    @JsonCreator
    public Book(    @JsonProperty("author") Author author,
                    @JsonProperty("title") String title,
                    @JsonProperty("isbn") String isbn,
                    @JsonProperty("genre") Genre genre) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
    }

    @JsonCreator
    public Book(@JsonProperty("author") @Nullable Author author,
                @JsonProperty("title") String title,
                @JsonProperty("isbn")  String isbn,
                @JsonProperty("genre") Genre genre,
                @JsonProperty("rating") @Nullable int rating) {
        validate(rating);
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.rating = rating;
    }

    private void validate(int rating) {
        if (rating > 5 || rating < 1) {
            throw new IllegalArgumentException();
        }
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        validate(rating);
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (rating != book.rating) return false;
        if (!Objects.equals(author, book.author)) return false;
        if (!Objects.equals(title, book.title)) return false;
        if (!Objects.equals(isbn, book.isbn)) return false;
        return genre == book.genre;
    }
}

