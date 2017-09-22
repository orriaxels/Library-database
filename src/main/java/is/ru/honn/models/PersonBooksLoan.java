package is.ru.honn.models;

import java.util.ArrayList;

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
