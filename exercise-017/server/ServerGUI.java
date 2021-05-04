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

package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class was created for the Server side GUI.
 * @author Okan Atas, created on April 11, 2021
 * @version 1.0.0
 */
public class ServerGUI extends Application {

    /**
     * Textarea
     */
    public static TextArea textArea1 = new TextArea();

    /**
     * Server side GUI.
     * @param stage server window
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("Multithreaded Server");

        textArea1.setEditable(false);

        textArea1.setPrefHeight(600);

        VBox vbox = new VBox(textArea1);

        Scene scene = new Scene(vbox, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method for Server side GUI.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Server.startSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Application.launch(args);
    }
}
