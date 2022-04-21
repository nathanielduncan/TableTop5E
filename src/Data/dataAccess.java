package Data;

import java.sql.*;

public class dataAccess {
    //This class is the SQL level of the data access framework.

    public static ResultSet getAllRaces() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");

            System.out.println("Connection made!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM races;");

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
