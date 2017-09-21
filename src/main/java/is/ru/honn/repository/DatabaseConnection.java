package is.ru.honn.repository;

import is.ru.honn.utilities.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    List<JSONObject> allBooks = new ArrayList<JSONObject>();
    List<JSONObject> allFriends = new ArrayList<JSONObject>();
    List<JSONObject> loans = new ArrayList<JSONObject>();

    JSONReader reader = new JSONReader();

    private Connection connect()
    {
        String url = "jdbc:sqlite:library.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void createNewTable() {

        // SQL statement for creating a new table
        System.out.println("Creating tables...");
        String booksTable = "CREATE TABLE IF NOT EXISTS books (\n"
                + " id INTEGER PRIMARY KEY, \n"
                + " title text NOT NULL, \n"
                + " fname text NOT NULL, \n"
                + " lname text NOT NULL, \n"
                + " published date, \n"
                + " isbn text NOT NULL \n"
                + ");";

        String personTable = "CREATE TABLE IF NOT EXISTS persons (\n"
                + " id INTEGER PRIMARY KEY, \n"
                + " fname text NOT NULL, \n"
                + " lname text NOT NULL, \n"
                + " email text, \n"
                + " phone text, \n"
                + " address text \n"
                + ");";

        String outloansTable = "CREATE TABLE IF NOT EXISTS loans(\n"
                + " id INTEGER PRIMARY KEY, \n"
                + " pid long, \n"
                + " bid long, \n"
                + " dateOfLoan text NOT NULL, \n"
                + " rating long \n"
                + ");";

        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(booksTable);
            stmt.execute(personTable);
            stmt.execute(outloansTable);
            System.out.println("Done creating tables");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initialFill()
    {
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

        // Fill books table
        System.out.println("Inserting into book table...");
        for(JSONObject book : allBooks)
        {
            String sql = "INSERT INTO books(id, title, fname, lname, published, isbn) VALUES(?,?,?,?,?,?)";

            try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1,   (Long)book.get("bok_id"));
                pstmt.setString(2, (String)book.get("bok_titill"));
                pstmt.setString(3, (String)book.get("fornafn_hofundar"));
                pstmt.setString(4, (String)book.get("eftirnafn_hofundar"));
                pstmt.setString(5, (String)book.get("utgafudagur"));
                pstmt.setString(6, (String)book.get("ISBN"));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Finished inserting into book table");

        System.out.println("Inserting into persons table...");
        // Fill person table
        for(JSONObject friend : allFriends)
        {
            String sql = "INSERT INTO persons(id, fname, lname, email, address) VALUES(?,?,?,?,?)";

            try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1,   (Long)friend.get("vinur_id"));
                pstmt.setString(2, (String)friend.get("fornafn"));
                pstmt.setString(3, (String)friend.get("eftirnafn"));
                pstmt.setString(4, (String)friend.get("netfang"));
                pstmt.setString(5, (String)friend.get("heimilisfang"));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Finished inserting into persons table");


        System.out.println("Inserting into loans table...");
        // Fill outloans table
        for(JSONObject loan : loans)
        {
            String sql = "INSERT INTO loans(pid, bid, dateOfLoan) VALUES(?,?,?)";

            try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1,   (long)loan.get("pid"));
                pstmt.setLong(2, (long)loan.get("bok_id"));
                pstmt.setString(3, (String)loan.get("bok_lanadagsetning"));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Finished inserting into loans table");
    }
}
