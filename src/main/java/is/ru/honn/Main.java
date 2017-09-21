package is.ru.honn;

import is.ru.honn.repository.DatabaseConnection;
import is.ru.honn.ui.UIMenu;
import is.ru.honn.utilities.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args)
    {
        UIMenu menu = new UIMenu();

        List<JSONObject> allBooks = new ArrayList<JSONObject>();
        List<JSONObject> allFriends = new ArrayList<JSONObject>();
        List<JSONObject> loans = new ArrayList<JSONObject>();

        JSONReader reader = new JSONReader();

        String fileBooks = System.getProperty("user.dir") + "/jsonData/SC-T-302-HONN 2017- Bækur.json";
        String fileFriends = System.getProperty("user.dir") + "/jsonData/SC-T-HONN-302 2017- Vinir.json";

        allBooks = reader.readJson(fileBooks);
        allFriends = reader.readJson(fileFriends);


        for(JSONObject obj : allFriends)
        {
            if(obj.get("lanasafn") != null)
            {
                JSONArray loan = (JSONArray) obj.get("lanasafn");

                for(Object ls : loan)
                {
                    JSONObject loanInstance = (JSONObject) ls;
                    loanInstance.put("pid", obj.get("vinur_id"));
                    loans.add(loanInstance);
                }
            }
        }

        DatabaseConnection db = new DatabaseConnection();

//        db.createNewTable();
//        db.insertBooks(allBooks);
//        db.insertFriends(allFriends);
//        db.insertLoans(loans);

        menu.run();
    }
}
