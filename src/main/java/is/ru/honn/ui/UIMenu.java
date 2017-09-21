package is.ru.honn.ui;

import is.ru.honn.service.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        System.out.println("0: exit\n");
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

        System.out.print("Date: " + sdf.format(date));

        String newDate = (String)sdf.format(date);
        _service.addNewLoanTransaction(pId, bId, newDate);


    }

}
