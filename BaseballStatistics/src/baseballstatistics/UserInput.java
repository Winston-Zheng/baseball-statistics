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
import javax.swing.JOptionPane;

public class UserInput extends Application {

    private TextField numberField;
    private TextField fNameField;
    private TextField lNameField;
    private TextField inningsPitchedField;
    private TextField runsField;
    private TextField hitsField;
    private TextField earnedRunsField;
    private TextField basesOnBallsField;
    private TextField numPitchesField;
    private TextField strikeOutsField;
    private DatePicker datePicker = new DatePicker();
    private TextField battersFacedField;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Pitcher Stats");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);

        HBox datebox = new HBox(datePicker);

        Scene scene = new Scene(grid, 395, 475);

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

        grid.add(new Label("Innings Pitched (ip): "), 0, 4);
        inningsPitchedField = new TextField();
        grid.add(inningsPitchedField, 1, 4);

        grid.add(new Label("Hits (h): "), 0, 5);
        hitsField = new TextField();
        grid.add(hitsField, 1, 5);

        grid.add(new Label("Runs (r): "), 0, 6);
        runsField = new TextField();
        grid.add(runsField, 1, 6);

        grid.add(new Label("Earned Runs (er): "), 0, 7);
        earnedRunsField = new TextField();
        grid.add(earnedRunsField, 1, 7);

        grid.add(new Label("Bases on balls (bb): "), 0, 8);
        basesOnBallsField = new TextField();
        grid.add(basesOnBallsField, 1, 8);

        grid.add(new Label("Strike Outs (so): "), 0, 9);
        strikeOutsField = new TextField();
        grid.add(strikeOutsField, 1, 9);

        grid.add(new Label("Batters faced (bf): "), 0, 10);
        battersFacedField = new TextField();
        grid.add(battersFacedField, 1, 10);

        grid.add(new Label("Number of pitches (pc/np): "), 0, 11);
        numPitchesField = new TextField();
        grid.add(numPitchesField, 1, 11);

        // Button handler
        Button Submit = new Button("Submit");
        Submit.setOnAction(event -> submitButtonClicked());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitButtonClicked());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().add(Submit);
        buttonBox.getChildren().add(exitButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(buttonBox, 0, 12, 2, 1);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void submitButtonClicked() {
        LocalDate date = datePicker.getValue();
        if (date == null || numberField.getText().isEmpty()
                || fNameField.getText().isEmpty() || lNameField.getText().isEmpty()
                || inningsPitchedField.getText().isEmpty() || runsField.getText().isEmpty()
                || hitsField.getText().isEmpty() || earnedRunsField.getText().isEmpty()
                || basesOnBallsField.getText().isEmpty() || numPitchesField.getText().isEmpty()
                || strikeOutsField.getText().isEmpty() || battersFacedField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please add data to all fields.");
        } else {
            if (!isInt(numberField)) {
                numberField.requestFocus();
            } else if (!isDouble(inningsPitchedField, "You must enter a positive number")) {
                inningsPitchedField.requestFocus();
            } else if (!isInt(runsField)) {
                runsField.requestFocus();
            } else if (!isInt(hitsField)) {
                hitsField.requestFocus();
            } else if (!isInt(earnedRunsField)) {
                earnedRunsField.requestFocus();
            } else if (!isInt(basesOnBallsField)) {
                basesOnBallsField.requestFocus();
            } else if (!isInt(numPitchesField)) {
                numPitchesField.requestFocus();
            } else if (!isInt(strikeOutsField)) {
                strikeOutsField.requestFocus();
            } else if (!isInt(battersFacedField)) {
                battersFacedField.requestFocus();
            } else {
                String dateString = date.toString();
                JOptionPane.showMessageDialog(null, "All data Valid!");
                PitcherSQL.createDatabase(dateString);

                String fName = fNameField.getText();
                String lName = lNameField.getText();
                int uniformNum = Integer.parseInt(numberField.getText());
                double inningsPitched = Double.parseDouble(inningsPitchedField.getText());
                int runs = Integer.parseInt(runsField.getText());
                int hits = Integer.parseInt(hitsField.getText());
                int earnedRuns = Integer.parseInt(earnedRunsField.getText());
                int basesOnBalls = Integer.parseInt(basesOnBallsField.getText());
                int numPitches = Integer.parseInt(numPitchesField.getText());
                int strikeOuts = Integer.parseInt(strikeOutsField.getText());
                int battersFaced = Integer.parseInt(battersFacedField.getText());

                PitcherSQL.addPitcherData(dateString, fName, lName, uniformNum, inningsPitched,
                        hits, runs, earnedRuns, basesOnBalls, strikeOuts, battersFaced, numPitches);
            }

        }
    }

    private void exitButtonClicked() {
        System.exit(0);
    }

    private boolean isInt(TextField f) {
        try {
            int i = Integer.parseInt(f.getText());
            if (i >= 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "You must enter a positive whole number");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must enter a positive whole number");
            return false;
        }
    }

    private boolean isDouble(TextField f, String msg) {
        try {
            double i = Double.parseDouble(f.getText());
            if (i >= 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, (msg));
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, (msg));
            return false;
        }
    }
}
