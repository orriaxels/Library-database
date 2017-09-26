package is.ru.honn;

import is.ru.honn.repository.DatabaseConnection;
import is.ru.honn.repository.Repository;
import is.ru.honn.service.Service;
import is.ru.honn.ui.UIMenu;
import java.io.File;

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version Main.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * Main checks if there is a database already in the system, if not it will create it.
 * It creates an instance of the Repository class, the Service class and the UIMenu class
 * Then it runs the UI menu
 */

public class Main {


    public static void main(String[] args)
    {
        File f;

        // Checks what os user has
        // Checks if the database exists in that path or not
        if(System.getProperty("os.name").equals("Mac OS X"))
        {
            f = new File(System.getProperty("user.dir") + "/library.db");
        }
        else
        {
            f = new File(System.getProperty("user.dir") + "\\library.db");
        }

        // If db does not exist this will run and create it
        if(!f.exists())
        {
            System.out.println("DatabaseCreation");
            DatabaseConnection db = new DatabaseConnection();
            db.createNewTable();
            db.initialFill();
        }

        Repository _repo = new Repository();
        Service _service = new Service(_repo);
        UIMenu menu = new UIMenu(_service);


        menu.run();
    }
}
