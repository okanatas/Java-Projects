package task1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This class was created for main window (Search window ) of Search Name Ranking Application GUI.
 * @author Okan Atas, created on March 14, 2021
 * @version 1.1.0
 */
public class AppGUI extends Application{

    /** TextField object that for year field */
    public static TextField yearField = new TextField();
    /** TextField object that for gender field */
    public static TextField genderField = new TextField();
    /** TextField object that for name field */
    public static TextField nameField = new TextField();

    private final Button buttonExit = new Button("Exit");
    private final Button buttonSubmit = new Button("Submit Query");

    private final Button buttonYes = new Button("Yes");
    private final Button buttonNo = new Button("No");

    /** result screen message */
    public static String resultMessage = "";

    /**
     * This start method for Main stage of Search Name Ranking Application
     * @param stage main window
     */
    @Override
    public void start(Stage stage) {
        VBox vBox = new VBox();  // general layout for scene 1
        VBox vBox2 = new VBox(); // general layout for scene 2

        // CREATING SCENE 1 : Search Screen  **************************************************************
        GridPane gridPane1 = new GridPane();
        GridPane gridPane2 = new GridPane();
        GridPane gridPane3 = new GridPane();

        gridPane1.setHgap(30);
        gridPane2.setHgap(14);
        gridPane3.setHgap(16);
        gridPane3.setVgap(5);

        gridPane1.add(new Label("Enter the Year:"), 0, 0);
        gridPane1.add(yearField, 1, 0);
        gridPane2.add(new Label("Enter the Gender:"), 0, 0);
        gridPane2.add(genderField, 1, 0);
        gridPane3.add(new Label("Enter the Name:"), 0, 0);
        gridPane3.add(nameField, 1, 0);
        gridPane3.add(buttonSubmit, 0, 5);
        gridPane3.add(buttonExit, 1, 5);

        buttonExit.setPrefWidth(90);
        buttonSubmit.setPrefWidth(90);
        genderField.setPrefWidth(30);

        gridPane1.setAlignment(Pos.CENTER);
        gridPane2.setAlignment(Pos.CENTER);
        gridPane3.setAlignment(Pos.CENTER);

        GridPane.setHalignment(buttonExit, HPos.RIGHT);
        GridPane.setHalignment(buttonSubmit, HPos.LEFT);

        gridPane2.setPadding(new Insets(0,0,0,-120));

        vBox.getChildren().addAll(gridPane1,gridPane2,gridPane3);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        // activate "enter key" for submit button ---> OPTIONAL
        //buttonSubmit.setDefaultButton(true);

        Scene scene1 = new Scene(vBox, 400,250);

        // CREATING SCENE 2 : Result Screen  ***********************************************************
        GridPane gridPane4 = new GridPane();
        gridPane4.setHgap(30);
        gridPane4.setVgap(10);
        Label resultLabel = new Label();
        Label questionLabel = new Label("Do you want to Search for Another Name?");

        vBox2.setSpacing(15);
        vBox2.setAlignment(Pos.CENTER);
        gridPane4.setAlignment(Pos.CENTER);

        gridPane4.add(buttonYes, 0,0);
        gridPane4.add(buttonNo, 1,0);

        vBox2.getChildren().addAll(resultLabel, questionLabel, gridPane4);

        resultLabel.setAlignment(Pos.CENTER);
        questionLabel.setAlignment(Pos.CENTER);

        buttonYes.setPrefWidth(90);
        buttonNo.setPrefWidth(90);

        // activate "enter key" for yes button ---> OPTIONAL
        //buttonYes.setDefaultButton(true);

        Scene scene2 = new Scene(vBox2 ,400,250);

        // SETTING BUTTON EVENTS  *********************************************************************

        // set exit button event
        buttonExit.setOnAction(e -> stage.close());

        // set submit button event
        buttonSubmit.setOnAction(e -> {
            boolean isSecond = SearchManager.startQuery();
            if(isSecond){
                stage.setScene(scene2);
                resultLabel.setText(resultMessage);
                resultMessage = "after result";
                SearchManager.clearFields();
            }
        });

        // set yes button event
        buttonYes.setOnAction(e -> stage.setScene(scene1));

        // set no button event
        buttonNo.setOnAction(e -> stage.close());

        // STAGE SETTINGS : at the first time scene 1 ( search screen ) will be active  ***********************
        stage.setTitle("Search Name Ranking Application");
        stage.setScene(scene1);
        stage.show();
    }

    /**
     * Main method to run Search Name Ranking Application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
