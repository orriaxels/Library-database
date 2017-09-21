package is.ru.honn.service;

import is.ru.honn.models.Book;
import is.ru.honn.models.LoanTransaction;
import is.ru.honn.models.Person;
import is.ru.honn.repository.Repository;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Service
{
    Repository _repo;
    public Service(Repository repo)
    {
        this._repo = repo;
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

}
