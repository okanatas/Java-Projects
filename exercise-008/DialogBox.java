import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class was created for the Dialog window
 * @author Okan Atas, created on March 3, 2021
 * @version 1.2.0 - updated on March 5 - The trim method has been added to prevent user error
 */
public class DialogBox {

    /**
     * This is the method that displays the DialogBox GUI created to inform the user.
     * @param title title of DialogBox window
     * @param message message that appears in the DialogBox window
     */
    public static void display(String title, String message){
        Stage stage = new Stage();

        // application modal -> require dialog box action by the user
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle(title);
        stage.setMinWidth(350);

        // creating StackPane
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();

        // creating Label for message text
        Label label = new Label();

        // StackPane 1 settings
        label.setText(message);
        stackPane1.getChildren().add(label);
        stackPane1.setPadding(new Insets(15, 0, 0, 0));
        stackPane1.setAlignment(Pos.CENTER);

        // creating Button
        Button closeButton = new Button("OK");

        // StackPane 2 settings
        closeButton.setOnAction( event -> stage.close());
        closeButton.setPrefWidth(90);
        stackPane2.getChildren().add(closeButton);
        stackPane2.setPadding(new Insets(35, 5, 10, 5));
        stackPane2.setAlignment(Pos.CENTER);

        // creating Vbox for cover all layout
        VBox vBoxLayout = new VBox();

        // general layout
        vBoxLayout.getChildren().addAll(stackPane1, stackPane2);

        Scene scene = new Scene(vBoxLayout);
        stage.setScene(scene);

        // show and wait for action
        stage.showAndWait();
    }
}