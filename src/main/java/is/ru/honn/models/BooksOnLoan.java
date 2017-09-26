package is.ru.honn.models;

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version BooksOnLoan.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The BooksOnLoan class holds only two variables, the title of the book and
 * the persons name, when an instace of this object is created information can only
 * be added through the constructor
 */

public class BooksOnLoan
{
    private String title;

    private String personName;

    public BooksOnLoan(String title, String personName)
    {
        this.title = title;
        this.personName = personName;
    }

    public String getTitle() {
        return title;
    }
    public String getPersonName() {
        return personName;
    }
}
