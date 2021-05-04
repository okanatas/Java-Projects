/**********************************************
 Workshop #10
 Course: JAC444 - Winter 2021
 Last Name: Atas
 First Name: Okan
 ID: 130627193
 Section: NFF
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature: Okan Atas
 Date: April 11, 2021
 **********************************************/

package client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class was created for the ClientSideChatBox GUI.
 * @author Okan Atas, created on April 11, 2021
 * @version 1.0.0
 */
public class ClientSideChatBox extends Application {
    /** Textarea */
    public static TextArea textArea1 = new TextArea();
    /** Textarea */
    public static TextArea textArea2 = new TextArea();
    /** TextField */
    public static TextField textField = new TextField();
    /** Label */
    public static Label label = new Label("Enter your name");
    /** Client Name */
    public static String clientName;

    /**
     * Client side GUI.
     * @param stage window
     */
    @Override
    public void start(Stage stage){


        stage.setTitle("Client");


        textArea1.setEditable(false);

        textArea1.setPrefHeight(400);
        textArea2.setPrefHeight(100);
        textArea2.setPrefWidth(500);


        Button connectButton = new Button("Connect");
        Button sendButton = new Button("Send");

        sendButton.setPrefWidth(70);
        sendButton.setPrefHeight(100);

        connectButton.setPrefWidth(90);
        connectButton.setPrefHeight(30);


        VBox vbox1 = new VBox();


        VBox vbox2 = new VBox();

        StackPane stackPane = new StackPane(textArea1);

        HBox hBox = new HBox();

        textField.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(label, textField, connectButton);
        vbox1.setSpacing(10);

        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(0,90,0,90));


        hBox.getChildren().addAll(textArea2, sendButton);
        hBox.setAlignment(Pos.CENTER);

        hBox.setSpacing(10);

        StackPane stackPane1 = new StackPane(hBox);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(10,10,0,10));
        stackPane1.setPadding(new Insets(10,0,10,0));

        vbox2.getChildren().addAll(stackPane, stackPane1);


        Scene entryScene = new Scene(vbox1, 600, 500);
        Scene chatScene = new Scene(vbox2, 600, 500);


        sendButton.setOnAction(event -> {
            try {
                ProcessController.messageFlowController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        connectButton.setOnAction(event -> {
            clientName = textField.getText();
            stage.setTitle(clientName);

            try {
                ProcessController.startClient();
            } catch (IOException e) {
                e.printStackTrace();
            }

            textArea2.requestFocus();

            ProcessController.startListening();


            stage.setScene(chatScene);
        });


        stage.setScene(entryScene);
        stage.show();
    }

    /**
     * Client side GUI main method.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Application.launch();
    }
}
