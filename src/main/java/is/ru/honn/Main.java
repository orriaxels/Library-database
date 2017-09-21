package is.ru.honn;

import is.ru.honn.repository.DatabaseConnection;
import is.ru.honn.repository.Repository;
import is.ru.honn.service.Service;
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
        Repository _repo = new Repository();
        Service _service = new Service(_repo);
        UIMenu menu = new UIMenu(_service);
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
