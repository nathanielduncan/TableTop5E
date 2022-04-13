package Data;

import java.sql.*;

public class dataAccess {
    //This class is the SQL level of the data access framework.

    public static void testRead() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");

            System.out.println("Connection made!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM races;");

            while(rs.next()) {
                System.out.println("Name = " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
