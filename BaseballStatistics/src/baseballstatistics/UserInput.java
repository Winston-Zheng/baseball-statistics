
package baseballstatistics;

// Brenden Rayburn
// 4/27/2022

// B.R. 4/27/2022 - Finished the first draft for the GUI elements.
// still needs input to be verified and proper submit method.


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private TextField putOutsField;
    private TextField assistsField;
    private TextField leftOnBaseField;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Player Batting Stats");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);
        
        Scene scene = new Scene(grid, 350, 475);
        
        grid.add(new Label("First Name: "), 0, 0);
        fNameField = new TextField();
        grid.add(fNameField, 1, 0);
        
        grid.add(new Label("Last Name: "), 0, 1);
        lNameField = new TextField();
        grid.add(lNameField, 1, 1);
        
        grid.add(new Label("Uniform #: "), 0, 2);
        numberField = new TextField();
        grid.add(numberField, 1, 2);
        
        grid.add(new Label("Times at bat (ab): "), 0, 3);
        atBatField = new TextField();
        grid.add(atBatField, 1, 3);
        
        grid.add(new Label("Runs (r): "), 0, 4);
        runsField = new TextField();
        grid.add(runsField, 1, 4);
        
        grid.add(new Label("Hits (h): "), 0, 5);
        hitsField = new TextField();
        grid.add(hitsField, 1, 5);
        
        grid.add(new Label("Runs batted in (rbi): "), 0, 6);
        runsBattedInField = new TextField();
        grid.add(runsBattedInField, 1, 6);
        
        grid.add(new Label("Bases on balls (bb): "), 0, 7);
        basesOnBallsField = new TextField();
        grid.add(basesOnBallsField, 1, 7);
        
        grid.add(new Label("Strike outs (so): "), 0, 8);
        strikeOutsField = new TextField();
        grid.add(strikeOutsField, 1, 8);
        
        grid.add(new Label("Put outs (po): "), 0, 9);
        putOutsField = new TextField();
        grid.add(putOutsField, 1, 9);
        
        grid.add(new Label("Assists (a): "), 0, 10);
        assistsField = new TextField();
        grid.add(assistsField, 1, 10);
        
        grid.add(new Label("Left on base (lob): "), 0, 11);
        leftOnBaseField = new TextField();
        grid.add(leftOnBaseField, 1, 11);
        

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
       
    }
    
    private void exitButtonClicked() {
        System.exit(0);
    }
}

