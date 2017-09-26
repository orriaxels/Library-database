package is.ru.honn.models;

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version Book.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The Person Class is a simple class that holds the information about one
 * Book object in the database
 */

public class Book
{
    private String title;
    private String firstName;
    private String lastName;
    private String publised;
    private String isbn;

    public Book(String title, String firstName, String lastName, String published, String isbn)
    {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.publised = published;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPublised() {
        return publised;
    }

    public String getIsbn() {
        return isbn;
    }
}
