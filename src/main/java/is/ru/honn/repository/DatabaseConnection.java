package is.ru.honn.repository;

import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class DatabaseConnection {

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
        // db parameters

        // SQL statement for creating a new table
        String booksTable = "CREATE TABLE IF NOT EXISTS books (\n"
                + " id long PRIMARY KEY, \n"
                + " title text NOT NULL, \n"
                + " fname text NOT NULL, \n"
                + " lname text NOT NULL, \n"
                + " published date, \n"
                + " isbn text NOT NULL \n"
                + ");";

        String personTable = "CREATE TABLE IF NOT EXISTS persons (\n"
                + " id long PRIMARY KEY, \n"
                + " fname text NOT NULL, \n"
                + " lname text NOT NULL, \n"
                + " email text, \n"
                + " phone text, \n"
                + " address text \n"
                + ");";

        String outloansTable = "CREATE TABLE IF NOT EXISTS outloans(\n"
                + " id long PRIMARY KEY, \n"
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertBooks(List<JSONObject> books)
    {
        for(JSONObject book : books)
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
    }

    public void insertFriends(List<JSONObject> friends)
    {
        for(JSONObject friend : friends)
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
    }

    public void insertLoans(List<JSONObject> loans)
    {
        long counter = 1;
        for(JSONObject loan : loans)
        {
            String sql = "INSERT INTO outloans(id, pid, bid, dateOfLoan) VALUES(?, ?,?,?)";

            try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1,  counter);
                pstmt.setLong(2,   (Long)loan.get("pid"));
                pstmt.setLong(3, (Long)loan.get("bok_id"));
                pstmt.setString(4, (String)loan.get("bok_lanadagsetning"));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            counter++;
        }
    }
}
