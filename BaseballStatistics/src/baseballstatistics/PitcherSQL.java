package baseballstatistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PitcherSQL {
    
    public static void createDatabase(String fileName) {
        // Function to create a new database
        
        String dbUrl = "jdbc:sqlite:" + fileName;
        
        try {
            Connection connection = DriverManager.getConnection(dbUrl);
            if (connection != null) {
                System.out.println("Database has been created.");
                createTable(fileName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createTable(String fileName) {
        String sql = "CREATE TABLE Pitchers ("
                + "Pitcher_id       INTEGER PRIMARY KEY NOT NULL,"
                + "First_name       TEXT NOT NULL,"
                + "Last_name        TEXT NOT NULL,"
                + "Uniform_number   INTEGER NOT NULL,"
                + "Times_at_bat     INTEGER,"
                + "Runs             INTEGER,"
                + "Hits             INTEGER,"
                + "Runs_batted_in   INTEGER,"
                + "Bases_on_balls   INTEGER,"
                + "Strike_outs      INTEGER,"
                + "Left_on_base     INTEGER"
                + ");";
        try {
            String dbUrl = "jdbc:sqlite:" + fileName;
            Connection connection = DriverManager.getConnection(dbUrl); 
            
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            
            
        } catch (SQLException e) {
            System.err.println(e);
            return;
        }
        
        
    }
    
    public static Connection getConnection(String fileName) {
        // Function to get connection to database
        Connection connection = null;
        try
        {
            String dbUrl = "jdbc:sqlite:" + fileName;
            connection = DriverManager.getConnection(dbUrl);
            return connection;
        }
        catch (SQLException e)
        {
            for (Throwable t : e)
                t.printStackTrace();   // for debugging
            return null;
        }
    }
    
    public static boolean disconnect(String fileName) {
        // Function to disconnect from database
        try {
            // On a successful shutdown, this throws an exception
            String shutdownURL = "jdbc:sqlite:" + fileName + ";shutdown=true";
            DriverManager.getConnection(shutdownURL);
        }
        catch (SQLException e) {
            if (e.getMessage().equals("System shutdown."))
                return true;
        }
        return false;
    }
    
    public static void addPitcherData(String fileName, String fName, String lName, int uniformNum, 
            int timesAtBat, int runs, int hits, int runsBattedIn, int basesOnBalls, 
            int strikeOuts, int leftOnBase) {
        // Function to add data into table
        
        String sql = "INSERT INTO Pitchers (First_name, Last_name, Uniform_number, "
                + "Times_at_bat, Runs, Hits, Runs_batted_in, Bases_on_balls, Strike_outs,"
                + "Put_outs, Assists, Left_on_base)"
                + "VALUES ('" + fName + "', '" + lName + "', '" + uniformNum + "', "
                + "'" + timesAtBat + "', '" + runs + "', '" + hits + "', '" + runsBattedIn + "', "
                + "'" + basesOnBalls + "', '" + strikeOuts + "', "
                + "'" + leftOnBase + "')"; 
        
        Connection connection;
        try {
            String dbUrl = "jdbc:sqlite:" + fileName;
            connection = DriverManager.getConnection(dbUrl); 
            
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            
            
        } catch (SQLException e) {
            System.err.println(e);
            return;
        }
    }
}



/*
 * Dorian Earl
 * 4/29/2022
 * Created file, added createDatabase, getConnection, and disconnect functions.
 */

/*
 * Dorian Earl
 * 5/1/2022
 * Created addPitcherData function.
 */

/*
 * Dorian Earl
 * 5/3/2022
 * Updated addPitcherData function to remove assists and Put Outs.
 */
