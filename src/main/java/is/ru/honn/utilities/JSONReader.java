package is.ru.honn.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONReader {
    public static void readJson()
    {
        JSONParser parser = new JSONParser();

        try
        {
            JSONArray obj = (JSONArray) parser.parse(new FileReader("test.json"));
            List<JSONObject> all_books = new ArrayList<JSONObject>();
            for(Object object : obj)
            {
                JSONObject book = (JSONObject) object;
                all_books.add(book);

            }

            for(JSONObject book1 : all_books)
            {

                System.out.println(book1.get("bok_id"));
            }
            
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

    }
}
