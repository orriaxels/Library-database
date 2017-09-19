package is.ru.honn;

import is.ru.honn.repository.DatabaseConnection;
import is.ru.honn.utilities.JSONReader;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args)
    {
        List<JSONObject> allBooks = new ArrayList<JSONObject>();
        List<JSONObject> allFriends = new ArrayList<JSONObject>();

        JSONReader reader = new JSONReader();

        String fileBooks = System.getProperty("user.dir") + "/jsonData/SC-T-302-HONN 2017- Bækur.json";
        String fileFriends = System.getProperty("user.dir") + "/jsonData/SC-T-HONN-302 2017- Vinir.json";

        allBooks = reader.readJson(fileBooks);
        allFriends = reader.readJson(fileFriends);
        Integer count = 1;
        for(JSONObject obj : allFriends)
        {
            if(obj.get("lanasafn") != null)
            {
                System.out.println(count + " : " + obj.get("lanasafn"));
                count++;
            }

        }

        DatabaseConnection db = new DatabaseConnection();

        db.createNewTable();
        db.insert("Raw materials", 3000);
        db.insert("Drasl", 2000);
        db.insert("bleh", 500);

    }
}
