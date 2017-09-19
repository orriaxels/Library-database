package is.ru.honn;

import is.ru.honn.repository.DatabaseConnection;
import is.ru.honn.utilities.JSONReader;

import java.util.Scanner;

public class Main {


    public static void main(String[] args)
    {
        JSONReader reader = new JSONReader();
        reader.readJson();

        DatabaseConnection db = new DatabaseConnection();

        db.createNewTable();
        db.insert("Raw materials", 3000);
        db.insert("Drasl", 2000);
        db.insert("bleh", 500);

    }
}
