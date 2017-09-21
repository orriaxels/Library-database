package is.ru.honn.ui;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIMenu
{
    private Scanner scanner = new Scanner(System.in);
    private String input;
    private boolean running = true;

    public UIMenu() {}

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
    }

    private void createNewFriend()
    {
        System.out.println("New friend");
    }

    private void createNewLoanTransaction()
    {
        System.out.println("New Loan Transaction");
    }

}
