package is.ru.honn.models;

import java.util.ArrayList;

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version PersonBookLoan.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The PersonBooksLoan class holds an one instance of the Person object and an
 * array of Book objects, we use this to tell us all the books a single person
 * has on loan for given date
 */

public class PersonBooksLoan
{
    private int pid;

    private Person person;
    private ArrayList<Book> books;

    public PersonBooksLoan(){
        books = new ArrayList<Book>();
    };

    public PersonBooksLoan(Person name, ArrayList<Book> books, int pid)
    {
        this.person = name;
        this.books = books;
        this.pid = pid;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Book> getBooks()
    {
        return books;
    }

    public void setBooks(Book books) {
        this.books.add(books);
    }

    public int getPid()
    {
        return pid;
    }
}
