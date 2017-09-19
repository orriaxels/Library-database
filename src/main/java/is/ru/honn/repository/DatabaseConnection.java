package is.ru.honn.repository;

import java.sql.*;

public class DatabaseConnection {

    private Connection connect()
    {
        String url = "jdbc:sqlite:test.db";
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
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + " id integer PRIMARY KEY, \n"
                + " name text NOT NULL, \n"
                + " capacity real\n"
                + ");";

        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
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
