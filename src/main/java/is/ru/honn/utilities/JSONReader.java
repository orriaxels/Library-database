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

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version JSONReader.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The class JSONReader creates an instance of JSONParse, it will open a json
 * file and read from it, it will store information in an array that it then returns
 */

public class JSONReader
{
    public static List<JSONObject> readJson(String fileName)
    {
        JSONParser parser = new JSONParser();
        List<JSONObject> library = new ArrayList<JSONObject>();

        try
        {
            JSONArray obj = (JSONArray) parser.parse(new FileReader(fileName));

            for(Object object : obj)
            {
                JSONObject book = (JSONObject) object;
                library.add(book);
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

        return library;
    }
}
