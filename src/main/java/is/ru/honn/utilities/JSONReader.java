package is.ru.honn.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JSONReader {
    public static void readJson()
    {
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader("test.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String name = (String) jsonObject.get("title");
            System.out.println(name);

            long accommodates = (Long) jsonObject.get("accommodates");
            System.out.println("Accommodates: " + accommodates);

            long bedrooms = (Long) jsonObject.get("bedrooms");
            System.out.println("Bedrooms: " + bedrooms);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("amenities");
            System.out.println("Amenities:");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext())
            {
                System.out.println(iterator.next());
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
