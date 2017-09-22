package is.ru.honn.ui;

import is.ru.honn.models.BooksOnLoan;
import is.ru.honn.models.PersonBooksLoan;
import is.ru.honn.service.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIMenu
{
    private Scanner scanner = new Scanner(System.in);
    private String input;
    private boolean running = true;
    private Service _service;
    private DateFormat sdf;
    private Date date;

    public UIMenu(Service service) {
        this._service = service;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = new Date();
    }

    public void run() {
        while(running)
        {
            System.out.print("\033[H\033[2J");
            try
            {
                Runtime.getRuntime().exec("clear");
            }
            catch(final Exception e)
            {

            }

            printMenu();

            input = (String) scanner.nextLine();

            if(input.toLowerCase().equals("0"))
            {
                break;
            }
            else if(input.toLowerCase().equals("1"))
            {
                createNewBook();
            }
            else if(input.toLowerCase().equals("2"))
            {
                createNewFriend();
            }
            else if(input.toLowerCase().equals("3"))
            {
                createNewLoanTransaction();
            }
            else if(input.toLowerCase().equals("4"))
            {
                printLoansMenu();
                String input = (String) scanner.nextLine();

                if(input.toLowerCase().equals("0"))
                {
                    run();
                }
                else if(input.toLowerCase().equals("1"))
                {
                    allBooks();
                }
                if(input.toLowerCase().equals("2"))
                {
                    allPersons();
                }
                if(input.toLowerCase().equals("3"))
                {
                    allPersonMonth();
                }
            }
            else
            {
                System.out.println("Wrong input");
            }
        }
    }

    private void printMenu()
    {
        System.out.println("Welcome \n");
        System.out.println("1: Add new book");
        System.out.println("2: Add new friend");
        System.out.println("3: Create loan transaction");
        System.out.println("4: All outstanding loans");
        System.out.println("0: exit\n");
        System.out.println("Choose");
    }

    public void printLoansMenu(){
        System.out.println("Outstanding loans \n");
        System.out.println("1: All books and persons");
        System.out.println("2: All persons and books");
        System.out.println("3: All persons that have books for more than a month");
        System.out.println("0: Back\n");
        System.out.println("Choose");
    }

    private void createNewBook()
    {
        System.out.println("New book");

        System.out.print("Book title: ");
        String newBookName = (String) scanner.nextLine();

        System.out.print("Author first name: ");
        String newAuthorFirstName = (String) scanner.nextLine();

        System.out.print("Author last name: ");
        String newAuthorLastName = (String) scanner.nextLine();

        System.out.print("Publish date: ");
        String newPublishDate = (String) scanner.nextLine();

        System.out.print("ISBN nr: ");
        String newISBN = (String) scanner.nextLine();


        _service.addNewBook(newBookName, newAuthorFirstName, newAuthorLastName, newPublishDate, newISBN);
    }

    private void createNewFriend()
    {
        System.out.println("New friend");

        System.out.print("First name: ");
        String newFirstName = (String) scanner.nextLine();

        System.out.print("Last name: ");
        String newLastName = (String) scanner.nextLine();

        System.out.print("Address: ");
        String newAddress = (String) scanner.nextLine();

        System.out.print("Email: ");
        String newEmail = (String) scanner.nextLine();

        System.out.print("Phone: ");
        String newPhone = (String) scanner.nextLine();

        _service.addNewPerson(newFirstName, newLastName, newAddress, newEmail, newPhone);
    }

    private void createNewLoanTransaction()
    {
        System.out.println("New Loan Transaction");

        System.out.print("Person Id: ");
        int pId = scanner.nextInt();

        System.out.print("Book Id: ");
        int bId = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Date: " + sdf.format(date) + "\n");

        String newDate = (String)sdf.format(date);
        _service.addNewLoanTransaction(pId, bId, newDate);
    }

    private void allBooks()
    {
        System.out.println("Get books on loan for specific date");

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        ArrayList<BooksOnLoan> books;

        books = _service.getAllBooksByDate(date);

        System.out.println("Title\t\t\t\t\t\t\t\t\t\t\t\t\tPerson name" );
        for(int i = 0; i < books.size(); i++)
        {
            System.out.println(books.get(i).getTitle() + "\t\t\t\t" + books.get(i).getPersonName());
        }

    }

    private void allPersons()
    {
        ArrayList<PersonBooksLoan> persons;

        System.out.println("Get persons and their books on loan for specific date");
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        persons  = _service.getPersonAndBooksOnLoan(date);

        System.out.println("");
        for(int i = 0; i < persons.size();i++)
        {
            System.out.println(persons.get(i).getPerson().getFirstName() + " " + persons.get(i).getPerson().getLastName());
            for(int j = 0; j < persons.get(i).getBooks().size(); j++)
            {
                System.out.println("\t- " + persons.get(i).getBooks().get(j).getTitle());
            }
            System.out.println("");
        }

        System.out.println("");
    }

    private void allPersonMonth()
    {

    }
}
