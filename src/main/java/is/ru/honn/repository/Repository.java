package is.ru.honn.repository;

import is.ru.honn.models.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version Repository.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The Repository starts by connecting to the database, it then takes care of
 * all communication with the database
 */

public class Repository
{
    Connection conn;

    // Repository constructor
    public Repository()
    {
        conn = connect();
    }

    /// <summary>
    /// This method connects to the database
    /// </summary>
    private Connection connect()
    {
        String url = "jdbc:sqlite:library.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /// <summary>
    /// This method adds a new book to the library database
    /// </summary>
    /// <param name="newBook">This method takes in a Book object containing book data</param>
    public void addBook(Book newBook)
    {
        String sql = "INSERT INTO books(title, fname, lname, published, isbn) VALUES(?,?,?,?,?)";

        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, (String)newBook.getTitle());
            pstmt.setString(2, (String)newBook.getFirstName());
            pstmt.setString(3, (String)newBook.getLastName());
            pstmt.setString(4, (String)newBook.getPublised());
            pstmt.setString(5, (String)newBook.getIsbn());
            pstmt.executeUpdate();

            System.out.println(newBook.getTitle() + " added to database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /// <summary>
    /// This method adds a new person to the library database
    /// </summary>
    /// <param name="newPerson">This method takes in a person object containing person data</param>
    public void addPerson(Person newPerson)
    {
        String sql = "INSERT INTO persons(fname, lname, address, email, phone) VALUES(?,?,?,?,?)";

        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, (String)newPerson.getFirstName());
            pstmt.setString(2, (String)newPerson.getLastName());
            pstmt.setString(3, (String)newPerson.getAddress());
            pstmt.setString(4, (String)newPerson.getEmail());
            pstmt.setString(5, (String)newPerson.getPhone());
            pstmt.executeUpdate();

            System.out.println(newPerson.getFirstName() + " " + newPerson.getLastName() + " added to database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /// <summary>
    /// This method adds a new loan transaction to the library database
    /// </summary>
    /// <param name="newLoan">This method takes in a LoanTransaction object containing loan transaction data</param>
    public void addLoanTransaction(LoanTransaction newLoan)
    {
        String sqlBook = "SELECT id FROM books WHERE id == ?";
        String sqlPerson = "SELECT id FROM persons WHERE id == ?";
        String sqlBookId = "SELECT bid FROM loans WHERE bid == ?";
        String sqlLoan = "INSERT INTO loans(pid, bid, dateOfLoan) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmtBookId = conn.prepareStatement(sqlBookId))
        {
            pstmtBookId.setInt(1, newLoan.getbId());
            ResultSet rsBookId = pstmtBookId.executeQuery();

            if(rsBookId.getInt("bid") == newLoan.getbId())
            {
                System.out.println("Book already on loan");
                return;
            }
        }
        catch (SQLException e)
        {
            System.out.println("");
        }


        try (Connection conn = this.connect();
             PreparedStatement pstmtBook   = conn.prepareStatement(sqlBook);
             PreparedStatement pstmtPerson = conn.prepareStatement(sqlPerson);
             PreparedStatement pstmt = conn.prepareStatement(sqlLoan))
        {
            pstmtBook.setInt(1, newLoan.getbId());
            pstmtPerson.setInt(1, newLoan.getpId());

            ResultSet rsBook   = pstmtBook.executeQuery();
            ResultSet rsPerson = pstmtPerson.executeQuery();

            pstmt.setLong(1, (long)rsPerson.getInt("id"));
            pstmt.setLong(2, (long)rsBook.getInt("id"));
            pstmt.setString(3, newLoan.getDate());
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Book or Person not found");
        }
    }

    /// <summary>
    /// This method gets a list of all books on loan for given date
    /// </summary>
    /// <param name="date">This method takes a string containing the date to search by</param>
    /// <returns>A list of all books on loan for a given date</return>
    public ArrayList<BooksOnLoan> getBooksOnLoanByDate(String date)
    {
        String sqlDate = "SELECT pid, bid FROM loans WHERE dateOfLoan == ?";

        ArrayList<BooksOnLoan> allBooks = new ArrayList<BooksOnLoan>();

        try (Connection conn = this.connect();
             PreparedStatement pstmtBookId = conn.prepareStatement(sqlDate))
        {
            pstmtBookId.setString(1, date);
            ResultSet rsBooks = pstmtBookId.executeQuery();

            while(rsBooks.next())
            {
                Person person = getPersonById(rsBooks.getInt("pid"));
                Book book = getBookById(rsBooks.getInt("bid"));
                BooksOnLoan booksOnLoan = new BooksOnLoan(book.getTitle(), person.getFirstName());
                allBooks.add(booksOnLoan);
            }
        }
        catch (SQLException e)
        {
            System.out.println("No transaction on this date");
        }

        return allBooks;
    }

    /// <summary>
    /// This method does two things
    /// If int Action = 1 it gets all persons and the books they have on loan for the given date
    /// If int Action != 1 it gets all persons and book they have on loan older then a month from given date
    /// </summary>
    /// <param name="date">This method takes a string containing the date to search by</param>
    /// <param name="action">This method takes a string saying what action it's gonna make</param>
    /// <returns>A list of PersonBookLoan object that contains a Person and the books that person has on loans</return>
    public ArrayList<PersonBooksLoan> getPersonAndBooksOnLoan(String date, int action)
    {
        String sqlDate;

        if(action == 1)
        {
            sqlDate = "SELECT pid, bid FROM loans WHERE dateOfLoan == ?";
        }
        else
        {
            sqlDate = "SELECT pid, bid FROM loans WHERE dateOfLoan <= ?";
        }

        ArrayList<PersonBooksLoan> persons = new ArrayList<PersonBooksLoan>();
        boolean alreadyExists = false;

        try (Connection conn = this.connect();
             PreparedStatement pstmtDate = conn.prepareStatement(sqlDate))
        {
            pstmtDate.setString(1, date);
            ResultSet rsBooks = pstmtDate.executeQuery();

            while(rsBooks.next())
            {
                Person person = getPersonById(rsBooks.getInt("pid"));
                Book book = getBookById(rsBooks.getInt("bid"));

                for(int i = 0; i < persons.size(); i++)
                {
                    if(persons.get(i).getPid() == rsBooks.getInt("pid"))
                    {
                        persons.get(i).getBooks().add(book);
                        alreadyExists = true;
                    }
                }

                if(!alreadyExists)
                {
                    PersonBooksLoan personAndBooks = new PersonBooksLoan();
                    personAndBooks.setPerson(person);
                    personAndBooks.setPid(rsBooks.getInt("pid"));
                    personAndBooks.setBooks(book);

                    persons.add(personAndBooks);

                }
                alreadyExists = false;
            }
        }
        catch (SQLException e)
        {
            System.out.println("No transaction on this date");
        }

        return  persons;
    }

    /// <summary>
    /// This method returns a person object by Id
    /// </summary>
    /// <param name="id">This method takes an Id of the person to search for</param>
    /// <returns>A Person object containing the person detail</return>
    public Person getPersonById(int id)
    {
        String sqlPerson = "SELECT * FROM persons WHERE id == ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmtPersonId = conn.prepareStatement(sqlPerson))
        {
            pstmtPersonId.setInt(1, id);
            ResultSet rsPerson = pstmtPersonId.executeQuery();

            Person person = new Person(rsPerson.getString("fName"), rsPerson.getString("lName"), rsPerson.getString("email"), rsPerson.getString("phone"), rsPerson.getString("address"));

            return person;
        }
        catch (SQLException e)
        {
            System.out.println("");
        }

        return null;
    }

    /// <summary>
    /// This method returns a book object by Id
    /// </summary>
    /// <param name="id">This method takes an Id of the book to search for</param>
    /// <returns>A Book object containing the person detail</return>
    public Book getBookById(int id)
    {
        String sqlBook = "SELECT * FROM books WHERE id == ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmtBookId = conn.prepareStatement(sqlBook))
        {
            pstmtBookId.setInt(1, id);
            ResultSet reBook = pstmtBookId.executeQuery();

            Book book = new Book(reBook.getString("title"), reBook.getString("fName"), reBook.getString("lName"), reBook.getString("published"), reBook.getString("isbn"));

            return book;
        }
        catch (SQLException e)
        {
            System.out.println("");
        }

        return null;
    }
}
