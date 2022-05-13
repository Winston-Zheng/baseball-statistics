//5/13/2022  this was made by me
package baseballstatistics;

import baseballstatistics.Pitcher;
import baseballstatistics.PitcherSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileReader {

    public static ObservableList<Pitcher> getConnection(String dateString) {
        String sql = "SELECT * FROM Pitchers ";

        ObservableList<Pitcher> stats = FXCollections.observableArrayList();
        try (Connection c = PitcherSQL.getConnection(dateString);
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                double ERA = Math.round(9 * rs.getInt("Earned_runs") / rs.getDouble("Innings_pitched") * 100) / 100;
                Pitcher pitcher = new Pitcher(dateString, rs.getInt("Uniform_number"), rs.getString("First_name"),
                        rs.getString("Last_name"), (Math.round(rs.getDouble("Innings_pitched") * 100) / 100),
                        rs.getInt("Hits"), rs.getInt("Runs"), rs.getInt("Earned_runs"), rs.getInt("Bases_on_balls"),
                        rs.getInt("Strike_outs"), rs.getInt("Batters_faced"), rs.getInt("Number_of_pitches"), ERA);
//                    Add the pitchers to the ObservableList
                stats.add(pitcher);
            }

            return stats;
        } catch (SQLException e) {
            System.out.println(e);
            return null;

        }

    }
}