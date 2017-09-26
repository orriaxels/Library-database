package is.ru.honn.service;

import is.ru.honn.models.*;
import is.ru.honn.repository.Repository;
import org.joda.time.LocalDate;
import org.joda.time.DateTimeZone;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version Serivice.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The Service class constructor takes in an instance of the Repository class
 * The Service class will take care of all the business logic and then it sends the
 * information forward to the repository layer, it will then send back to the UI layer the information it gets back
 */

public class Service
{
    Repository _repo;
    private SimpleDateFormat sdf;

    // Service constructor
    public Service(Repository repo)
    {
        this._repo = repo;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void addNewBook(String title, String firstName, String lastName, String date, String isbn)
    {
        Book newBook = new Book(title, firstName, lastName, date, isbn);
        _repo.addBook(newBook);
    }

    public void addNewPerson(String firstName, String lastName, String address, String email, String phone)
    {
        Person newPerson = new Person(firstName, lastName, address, email, phone);
        _repo.addPerson(newPerson);
    }

    public void addNewLoanTransaction(int pId, int bId, String date)
    {
        LoanTransaction newLoan = new LoanTransaction(pId, bId, date);
        _repo.addLoanTransaction(newLoan);
    }

    public ArrayList<BooksOnLoan> getAllBooksByDate(String date)
    {
        ArrayList<BooksOnLoan> books;
        books = _repo.getBooksOnLoanByDate(date);

        return books;
    }

    public ArrayList<PersonBooksLoan> getPersonAndBooksOnLoan(String date)
    {
        ArrayList<PersonBooksLoan> persons;
        persons =  _repo.getPersonAndBooksOnLoan(date, 1);
        return persons;
    }

    public ArrayList<PersonBooksLoan> getMonthOldLoans(String date)
    {
        ArrayList<PersonBooksLoan> persons;

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7)) -1;
        int day = Integer.parseInt(date.substring(8, 10));

        Date dt;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);
        c.add(Calendar.DATE, -30);
        dt = c.getTime();


        persons =  _repo.getPersonAndBooksOnLoan(sdf.format(dt), 2);
        return persons;
    }

}
