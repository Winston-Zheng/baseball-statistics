package baseballstatistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderHelper {

    public static void savePitcherDataForOneDate(String dateString, double era, String fName, String lName, int uniformNum,
            double inningsPitched, int hits, int runs, int earnedRuns, int basesOnBalls,
            int strikeOuts, int battersFaced, int numPitches) {

        createDatabase(dateString);
        String sql = "INSERT INTO Pitchers (ERA, First_name, Last_name, Uniform_number, "
                + "Innings_pitched, Hits, Runs, Earned_runs, Bases_on_balls, Strike_outs,"
                + "Batters_faced, Number_of_pitches)"
                + "VALUES ('" + era + "', '" + fName + "', '" + lName + "', '" + uniformNum + "', "
                + "'" + inningsPitched + "', '" + hits + "', '" + runs + "', '" + earnedRuns + "', "
                + "'" + basesOnBalls + "', '" + strikeOuts + "', "
                + "'" + battersFaced + "', '" + numPitches + "')";
        Connection connection;

        try {
            String dbUrl = "jdbc:sqlite:" + dateString + "_summary.sqlite";
            connection = DriverManager.getConnection(dbUrl);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println(e);
            return;
        }
    }

    private static void createDatabase(String fileName) {
        // Function to create a new database

        File dbFile = new File(fileName + "_summary.sqlite");

        if (!dbFile.exists()) {
            String dbUrl = "jdbc:sqlite:" + fileName + "_summary.sqlite";

            try {
                Connection connection = DriverManager.getConnection(dbUrl);
                if (connection != null) {
                    System.out.println("Database has been created.");
                    createTable(fileName);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("File already exists!");
        }
    }

    private static void createTable(String fileName) {
        String sql = "CREATE TABLE Pitchers ("
                + "Pitcher_id        INTEGER PRIMARY KEY NOT NULL,"
                + "ERA               DOUBLE,"
                + "First_name        TEXT NOT NULL,"
                + "Last_name         TEXT NOT NULL,"
                + "Uniform_number    INTEGER NOT NULL,"
                + "Innings_pitched   DOUBLE,"
                + "Hits              INTEGER,"
                + "Runs              INTEGER,"
                + "Earned_runs       INTEGER,"
                + "Bases_on_balls    INTEGER,"
                + "Strike_outs       INTEGER,"
                + "Batters_faced     INTEGER,"
                + "Number_of_pitches INTEGER"
                + ");";
        try {
            String dbUrl = "jdbc:sqlite:" + fileName + "_summary.sqlite";
            Connection connection = DriverManager.getConnection(dbUrl);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println(e);
            return;
        }

    }

//    public functions
    public static ArrayList<String> getDates() {
        return findFileNames();
    }
//HashMap keys are in lower case

    public static HashMap<String, ArrayList<ArrayList<Object>>> getPitcherDataByPlayer() {
        HashMap<String, ArrayList<ArrayList<Object>>> fileDataByDate = getFileDataMap();

        ArrayList<String> playerNames = getPitcherNames();
        HashMap<String, ArrayList<ArrayList<Object>>> dataByPlayer = new HashMap<>();

        for (String playerName : playerNames) {
            dataByPlayer.put(playerName, new ArrayList<>());
        }
        for (String key : fileDataByDate.keySet()) {

            ArrayList<ArrayList<Object>> oneDateFileData = fileDataByDate.get(key);

            for (ArrayList<Object> oneDateFileDatum : oneDateFileData) {
//                names are in lowercase
                String name = (oneDateFileDatum.get(0) + " " + oneDateFileDatum.get(1)).toLowerCase();

                dataByPlayer.get(name).add(oneDateFileDatum);
            }
        }

        return dataByPlayer;
    }

    public static ArrayList<String> getPitcherNames() {
        HashMap<String, ArrayList<ArrayList<Object>>> fileDataByDate = getFileDataMap();
        //       player names
        ArrayList<String> playerNames = new ArrayList<>();
        for (String key : fileDataByDate.keySet()) {
            ArrayList<ArrayList<Object>> oneDateFileData = fileDataByDate.get(key);

            for (ArrayList<Object> oneDateFileDatum : oneDateFileData) {
                String name = (oneDateFileDatum.get(0) + " " + oneDateFileDatum.get(1)).toLowerCase();

                if (!playerNames.contains(name)) {
                    playerNames.add(name);
                }
            }
        }
        return playerNames;
    }

    public static ArrayList<ArrayList<Object>> getPitcherDataByDate(String dateString) {
        HashMap<String, ArrayList<ArrayList<Object>>> fileData = getFileDataMap();
        ArrayList<ArrayList<Object>> fileDataByDate = fileData.get(dateString);
        return fileDataByDate;
    }

    //    private functions
    private static ArrayList<String> getPitcherNamesForOneDate(String dateString) {
        HashMap<String, ArrayList<ArrayList<Object>>> fileDataByDate = getFileDataMap();
        ArrayList<ArrayList<Object>> fileDataArr = fileDataByDate.get(dateString);
        //       player names
        ArrayList<String> playerNames = new ArrayList<>();
        for (ArrayList<Object> fileDatumArr : fileDataArr) {
            String name = (fileDatumArr.get(1) + " " + fileDatumArr.get(2)).toLowerCase();
            if (!playerNames.contains(name)) {
                playerNames.add(name);
            }
        }

        return playerNames;
    }

    private static HashMap<String, ArrayList<ArrayList<Object>>> getFileDataMap() {
        List<String> databaseNames = FileReaderHelper.findFileNames();

        HashMap<String, ArrayList<ArrayList<Object>>> filesDataMap = new HashMap<>();
        for (String databaseName : databaseNames) {
            String sql = "SELECT * FROM Pitchers";
            Connection connection;
            try {
                String dbUrl = "jdbc:sqlite:" + databaseName + ".sqlite";
                connection = DriverManager.getConnection(dbUrl);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                ArrayList<ArrayList<Object>> filesData = new ArrayList<>();

                ArrayList<Object> fileData = new ArrayList<>();
                while (rs.next()) {
                    fileData.add(rs.getString("First_name"));
                    fileData.add(rs.getString("Last_name"));
                    fileData.add(rs.getInt("Uniform_number"));
                    fileData.add(rs.getDouble("Innings_pitched"));
                    fileData.add(rs.getInt("Hits"));
                    fileData.add(rs.getInt("Runs"));
                    fileData.add(rs.getInt("Earned_runs"));
                    fileData.add(rs.getInt("Bases_on_balls"));
                    fileData.add(rs.getInt("Strike_outs"));
                    fileData.add(rs.getInt("Batters_faced"));
                    fileData.add(rs.getInt("Number_of_pitches"));
                    filesData.add(fileData);
                }
                filesDataMap.put(databaseName, filesData);
            } catch (SQLException e) {
                System.err.println(e);
                return null;
            }

        }
        return filesDataMap;
    }

    private static ArrayList<String> findFileNames() {
        List<String> files = null;
        try {

            String fileExtension = "sqlite";
            files = findFilesHelper(Paths.get(Paths.get("").toAbsolutePath().toString()), fileExtension);

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> databases = files;
        ArrayList<String> databaseNames = new ArrayList<>();
        for (String database : databases) {
            databaseNames.add(new File(database).getName().replaceFirst("[.][^.]+$", ""));
        }

        return databaseNames;
    }

    private static List<String> findFilesHelper(Path path, String fileExtension) throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;
        try (Stream<Path> walk = Files.walk(path, 1)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    // convert path to string
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
        return result;

    }

}
