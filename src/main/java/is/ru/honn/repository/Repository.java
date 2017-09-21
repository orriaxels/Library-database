package is.ru.honn.repository;

import is.ru.honn.models.Book;
import is.ru.honn.models.BooksOnLoan;
import is.ru.honn.models.LoanTransaction;
import is.ru.honn.models.Person;

import java.sql.*;
import java.util.ArrayList;

public class Repository
{
    Connection conn;

    public Repository()
    {
        conn = connect();
    }

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

    public void addLoanTransaction(LoanTransaction newLoan)
    {
        String sqlBook = "SELECT id FROM books WHERE id == ?";
        String sqlPerson = "SELECT id FROM persons WHERE id == ?";
        String sqlBookId = "SELECT id FROM loans WHERE id == ?";
        String sqlLoan = "INSERT INTO loans(pid, bid, dateOfLoan) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmtBookId = conn.prepareStatement(sqlBookId))
        {
            pstmtBookId.setInt(1, newLoan.getbId());
            ResultSet rsBookId = pstmtBookId.executeQuery();

            if(rsBookId.getInt("id") == newLoan.getbId())
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

    public ArrayList<BooksOnLoan> getBooksOnLoanByDate(String date)
    {
        String sqlDate = "SELECT pid, bid FROM loans WHERE dateOfLoan == ?";
        ArrayList<BooksOnLoan> allBooks = new ArrayList<BooksOnLoan>();
        System.out.println(date);
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
            System.out.println("");
        }

        return allBooks;
    }

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
