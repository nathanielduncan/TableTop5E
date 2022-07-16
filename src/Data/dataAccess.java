package Data;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class dataAccess {
    //This class is the SQL level of the data access framework.

    public static ResultSet getAllRaces() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM races;");

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getAllClasses() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM classes;");

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getAllBackgrounds() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM backgrounds;");

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSetMetaData getClassColumns() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");
            Statement stmt = conn.createStatement();

            ResultSet results = stmt.executeQuery("SELECT * FROM classes");
            ResultSetMetaData metaData = results.getMetaData();

            return metaData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSetMetaData getRaceColumns() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");
            Statement stmt = conn.createStatement();

            ResultSet results = stmt.executeQuery("SELECT * FROM races");
            ResultSetMetaData metaData = results.getMetaData();

            return metaData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSetMetaData getBackGColumns() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/ogl.db");
            Statement stmt = conn.createStatement();

            ResultSet results = stmt.executeQuery("SELECT * FROM backgrounds");
            ResultSetMetaData metaData = results.getMetaData();

            return metaData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void putNewCharacter(PlayerCharacter pc) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/Data/PCCharacters.db");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO characters VALUES ('" + pc.getClas() + "', '" + pc.getRace() + "', '" + pc.getBackground() + "')";

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
