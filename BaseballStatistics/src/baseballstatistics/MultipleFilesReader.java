package baseballstatistics;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/* 
Name: Winston Zheng
Purpose: This will be used to read multiple game files and summarize the 
statistics on each pitcher for a specified number of games. 
 */
public class MultipleFilesReader {

    public static ObservableList<Pitcher> getPitcherData() {
        HashMap<String, ArrayList<ArrayList<Object>>> pitcherData = FileHelper.getPitcherDataByPlayer();
        // loop and get the ArrayList filled with each pitcher's data

        deleteAllPitcherSummaryData();
        FileHelper.savePitcherData(pitcherData);

        String sql = "SELECT * FROM Pitchers";
        Connection connection;

        try {
            String dbUrl = "jdbc:sqlite:full_pitchers_summary.sqlite";

            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            ObservableList<Pitcher> filesData = FXCollections.observableArrayList();

            while (rs.next()) {
                Pitcher pitcher = new Pitcher("", rs.getInt("Uniform_number"), rs.getString("First_name"),
                        rs.getString("Last_name"), rs.getDouble("Innings_pitched"),
                        rs.getInt("Hits"), rs.getInt("Runs"), rs.getInt("Earned_runs"), rs.getInt("Bases_on_balls"),
                        rs.getInt("Strike_outs"), rs.getInt("Batters_faced"), rs.getInt("Number_of_pitches"), rs.getDouble("ERA"));

                filesData.add(pitcher);
            }

            rs.close();
            return filesData;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static void showSummary(Stage primaryStage, Scene primaryScene) {
        TableView<Pitcher> table = new TableView<>();
        ObservableList<Pitcher> pitcherData = MultipleFilesReader.getPitcherData();

        primaryStage.setTitle("Full Pitchers Summary");

        primaryStage.setWidth(1800);
        primaryStage.setHeight(550);

        final Label label = new Label("Full Pitchers Summary");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30);
        label.setFont(font);
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);

        TableColumn earnedRunAverageCol = new TableColumn("ERA");
        earnedRunAverageCol.setMinWidth(100);
        earnedRunAverageCol.setCellValueFactory(
                new PropertyValueFactory<Pitcher, String>("earnedRunAverage"));

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("fName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lName"));

        TableColumn uniformNumberCol = new TableColumn("Uniform Number");
        uniformNumberCol.setMinWidth(150);
        uniformNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("uniformNumber"));

        TableColumn inningsPitchedCol = new TableColumn("Innings Pitched");
        inningsPitchedCol.setMinWidth(150);
        inningsPitchedCol.setCellValueFactory(
                new PropertyValueFactory<>("inningsPitched"));

        TableColumn hitsCol = new TableColumn("Hits");
        hitsCol.setMinWidth(100);
        hitsCol.setCellValueFactory(
                new PropertyValueFactory<>("hits"));

        TableColumn runsCol = new TableColumn("Runs");
        runsCol.setMinWidth(100);
        runsCol.setCellValueFactory(
                new PropertyValueFactory<>("runs"));

        TableColumn earnedRunsCol = new TableColumn("Earned Runs");
        earnedRunsCol.setMinWidth(150);
        earnedRunsCol.setCellValueFactory(
                new PropertyValueFactory<>("earnedRuns"));

        TableColumn basesOnBallsCol = new TableColumn("Bases on Balls");
        basesOnBallsCol.setMinWidth(200);
        basesOnBallsCol.setCellValueFactory(
                new PropertyValueFactory<>("basesOnBalls"));

        TableColumn strikeoutsCol = new TableColumn("Strikeouts");
        strikeoutsCol.setMinWidth(150);
        strikeoutsCol.setCellValueFactory(
                new PropertyValueFactory<>("strikeOuts"));

        TableColumn battersFacedCol = new TableColumn("Batters Faced");
        battersFacedCol.setMinWidth(150);
        battersFacedCol.setCellValueFactory(
                new PropertyValueFactory<>("battersFaced"));

        TableColumn numberOfPitchesCol = new TableColumn("Number of Pitches");
        numberOfPitchesCol.setMinWidth(200);
        numberOfPitchesCol.setCellValueFactory(
                new PropertyValueFactory<>("numOfPitches"));

        table.setItems(pitcherData);
        table.getColumns().addAll(earnedRunAverageCol, firstNameCol, lastNameCol, uniformNumberCol, inningsPitchedCol, hitsCol, runsCol, earnedRunsCol, basesOnBallsCol, strikeoutsCol, battersFacedCol, numberOfPitchesCol);

        firstNameCol.setSortType(TableColumn.SortType.DESCENDING);
        lastNameCol.setSortable(false);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        // Button handler
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(event -> goBackButtonClicked(primaryStage, primaryScene));
        goBackButton.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(goBackButton, 0.0);
        AnchorPane.setRightAnchor(goBackButton, 0.0);
        goBackButton.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(label, table, goBackButton
        );

        Scene summaryScene = new Scene(new Group());
        ((Group) summaryScene.getRoot()).getChildren().addAll(vbox);
        primaryStage.setScene(summaryScene);

    }

    private static void goBackButtonClicked(Stage primaryStage, Scene primaryScene) {
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Pitcher Stats");
        primaryStage.setWidth(500);
        primaryStage.setHeight(700);
    }

    private static void deleteAllPitcherSummaryData() {
        String sql = "DELETE FROM Pitchers";
        Connection connection = PitcherSQL.getConnection("full_pitchers_summary");
        try {

            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Deletion successful");

        } catch (SQLException e) {
            System.err.println(e);
            return;
        }
    }

}
