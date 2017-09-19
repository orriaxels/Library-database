package is.ru.honn.repository;

import java.sql.*;

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
                + " id integer PRIMARY KEY, \n"
                + " title text NOT NULL, \n"
                + " fname text NOT NULL, \n"
                + " lname text NOT NULL, \n"
                + " published date, \n"
                + " isbn text NOT NULL \n"
                + ");";

        String personTable = "CREATE TABLE IF NOT EXISTS persons (\n"
                + " id integer PRIMARY KEY, \n"
                + " fname text NOT NULL, \n"
                + " lname text NOT NULL, \n"
                + " email text NOT NULL, \n"
                + " address text \n"
                + ");";

        String outloansTable = "CREATE TABLE IF NOT EXISTS outloans(\n"
                + " pid integer, \n"
                + " bid integer, \n"
                + " dateOfLoan text NOT NULL, \n"
                + " rating integer \n"
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

    public void insert(String name, double capacity)
    {
        String sql = "INSERT INTO warehouses(name, capacity) VALUES(?,?)";


        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
