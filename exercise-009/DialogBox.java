package task1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class was created for the Dialog window to inform user about input validation.
 * @author Okan Atas, created on March 14, 2021
 * @version 1.1.0
 */
public class DialogBox {

    /**
     * This method creates a popup window to inform user with high level language.
     * @param title popup window title
     * @param message1 popup window message 1
     * @param message2 popup window message 2
     * @param message3 popup window message 3
     */
    public static void display(String title, String message1, String message2 , String message3){
        Stage stage = new Stage();

        // application modal -> require dialog box action by the user
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle(title);
        stage.setMinWidth(350);

        // creating StackPane
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20,0,20,0));

        // creating Label for message text
        Label msg1 = new Label();
        msg1.setPadding(new Insets(0,0,20,0));
        Label msg2 = new Label();
        msg2.setPadding(new Insets(0,0,20,0));
        Label msg3 = new Label();
        msg3.setPadding(new Insets(0,0,20,0));

        String[] messages = setPopupMessageDesign(message1,message2,message3);

        // set messages
        msg1.setText(messages[0]);
        msg2.setText(messages[1]);
        msg3.setText(messages[2]);

        // creating Button
        Button closeButton = new Button("OK");

        // close button settings
        closeButton.setOnAction( event -> stage.close());
        closeButton.setPrefWidth(90);

        vBox.getChildren().addAll(msg3,msg1,msg2,closeButton);

        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        // show and wait for action
        stage.showAndWait();
    }

    /**
     * This method adjusts the design for displaying the messages.
     * @param msg1 popup window message 1
     * @param msg2 popup window message 1
     * @param msg3 popup window message 1
     * @return revised popup window messages array
     */
    private static String[] setPopupMessageDesign(String msg1, String msg2, String msg3){
        if(msg3.equals(""))
        {
            if(!msg1.equals("") && !msg2.equals("")){
                msg3 = msg1;
                msg1 = msg2;
                msg2 = "";
            }

            if(msg1.equals("")){
                msg3 = "";
                msg1 = msg2;
                msg2 = "";
            }
        }else{
            if(msg1.equals("")){
                msg1 = msg2;
                msg2 = "";
            }
        }
        return new String[] {msg1, msg2, msg3};
    }
}
