package is.ru.honn;

import is.ru.honn.repository.DatabaseConnection;
import is.ru.honn.ui.UIMenu;
import is.ru.honn.utilities.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args)
    {
        UIMenu menu = new UIMenu();

        File f = new File(System.getProperty("user.dir") + "\\library.db");

        if(!f.exists() && f.isDirectory())
        {
            DatabaseConnection db = new DatabaseConnection();
            db.createNewTable();
            db.initialFill();
        }

        menu.run();
    }
}
