
package baseballstatistics;

// Brenden Rayburn
// 4/27/2022

// B.R. 4/27/2022 - Finished the first draft for the GUI elements.
// still needs input to be verified and proper submit method.

// **CODE TO CAll CLASS**
// Application.launch(UserInput.class, args)

import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UserInput extends Application {

    private TextField numberField;
    private TextField fNameField;
    private TextField lNameField;
    private TextField atBatField;
    private TextField runsField;
    private TextField hitsField;
    private TextField runsBattedInField;
    private TextField basesOnBallsField;
    private TextField strikeOutsField;
    // private TextField putOutsField;
    // private TextField assistsField;
    private TextField leftOnBaseField;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Player Batting Stats");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);

        DatePicker datePicker = new DatePicker();
        HBox datebox = new HBox(datePicker);

        // **CODE TO GRAB DATE VALUE**
        // LocalDate date = datePicker.getValue();

        Scene scene = new Scene(grid, 350, 450);

        grid.add(new Label("Date of game: "), 0, 0);
        grid.add(datebox, 1, 0);

        grid.add(new Label("First Name: "), 0, 1);
        fNameField = new TextField();
        grid.add(fNameField, 1, 1);

        grid.add(new Label("Last Name: "), 0, 2);
        lNameField = new TextField();
        grid.add(lNameField, 1, 2);

        grid.add(new Label("Uniform #: "), 0, 3);
        numberField = new TextField();
        grid.add(numberField, 1, 3);

        grid.add(new Label("Times at bat (ab): "), 0, 4);
        atBatField = new TextField();
        grid.add(atBatField, 1, 4);

        grid.add(new Label("Runs (r): "), 0, 5);
        runsField = new TextField();
        grid.add(runsField, 1, 5);

        grid.add(new Label("Hits (h): "), 0, 6);
        hitsField = new TextField();
        grid.add(hitsField, 1, 6);

        grid.add(new Label("Runs batted in (rbi): "), 0, 7);
        runsBattedInField = new TextField();
        grid.add(runsBattedInField, 1, 7);

        grid.add(new Label("Bases on balls (bb): "), 0, 8);
        basesOnBallsField = new TextField();
        grid.add(basesOnBallsField, 1, 8);

        grid.add(new Label("Strike outs (so): "), 0, 9);
        strikeOutsField = new TextField();
        grid.add(strikeOutsField, 1, 9);

        // ** B.R. COMMENTED OUT ASSITS AND PUT OUTS, NOT NEEDED FOR BATTING STATS **
        // grid.add(new Label("Put outs (po): "), 0, 10);
        // putOutsField = new TextField();
        // grid.add(putOutsField, 1, 10);

        // grid.add(new Label("Assists (a): "), 0, 11);
        // assistsField = new TextField();
        // grid.add(assistsField, 1, 11);

        grid.add(new Label("Left on base (lob): "), 0, 10);
        leftOnBaseField = new TextField();
        grid.add(leftOnBaseField, 1, 10);

        // Button handler
        Button Submit = new Button("Submit");
        Submit.setOnAction(event -> submitButtonClicked());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitButtonClicked());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().add(Submit);
        buttonBox.getChildren().add(exitButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(buttonBox, 0, 11, 2, 1);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void submitButtonClicked() {

    }

    private void exitButtonClicked() {
        System.exit(0);
    }
}
