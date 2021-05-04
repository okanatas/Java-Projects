import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Collections;

/**
 * This class was created for the Address Book Application
 * @author Okan Atas,
 * created on March 3, 2021
 */
public class AddressBookGUI extends Application {

    // province array
    final String[] provinces = {"Alberta", "British Columbia", "Manitoba", "New Brunswick",
            "Newfoundland and Labrador", "Nova Scotia", "Ontario", "Prince Edward Island",
            "Quebec", "Saskatchewan", "Northwest Territories", "Nunavut", "Yukon"};

    // creating labels
    Label firstName = new Label("First Name:");
    Label lastName = new Label("Last Name:");
    Label city = new Label("City:");
    Label province = new Label("Province:");
    Label postalCode = new Label("Postal Code:");

    // creating fields
    /** First name text field */
    public static TextField firstNameTextField = new TextField();
    /** Last name text field */
    public static TextField lastNameTextField = new TextField();
    /** City text field */
    public static TextField cityTextField = new TextField();
    /** Province text field with ComboBox */
    public static ComboBox<String> provinceComboBox = new ComboBox<>();
    /** Postal code text field */
    public static TextField postalCodeTextField = new TextField();

    // creating buttons
    Button button1 = new Button("Add");
    Button button2 = new Button("First");
    Button button3 = new Button("Next");
    Button button4 = new Button("Previous");
    Button button5 = new Button("Last");
    Button button6 = new Button("Update");


    // creating GridPane object
    GridPane gridPane1 = new GridPane();
    GridPane gridPane2 = new GridPane();
    GridPane gridPane3 = new GridPane();

    // creating VBox object
    VBox vBoxLayout = new VBox();

    /**
     * Main entry point for Address Book GUI
     * @param stage address book window
     */
    @Override
    public void start(Stage stage) {
        // setting window title
        stage.setTitle("Address Book");

        // adding labels to the GridPane
        gridPane1.add(firstName, 0, 1);
        gridPane1.add(firstNameTextField, 1, 1);

        gridPane1.add(lastName, 0, 2);
        gridPane1.add(lastNameTextField, 1, 2);

        gridPane2.add(city, 0, 1);
        gridPane2.add(cityTextField, 1, 1);

        gridPane2.add(province, 2, 1);
        gridPane2.add(provinceComboBox, 3, 1);

        gridPane2.add(postalCode, 4, 1);
        gridPane2.add(postalCodeTextField, 5, 1);

        // adding buttons to the GridPane
        gridPane3.add(button1, 0, 0);
        gridPane3.add(button2, 1, 0);
        gridPane3.add(button3, 2, 0);
        gridPane3.add(button4, 3, 0);
        gridPane3.add(button5, 4, 0);
        gridPane3.add(button6, 5, 0);

        // design of fields
        firstNameTextField.setPrefWidth(480);  // since same grid and share same column with lastNameTextField, will effect it too

        cityTextField.setPrefWidth(120);

        provinceComboBox.setEditable(true);
        provinceComboBox.setPrefWidth(120);
        Collections.addAll(provinceComboBox.getItems(), provinces);  // adding provinces array values
        provinceComboBox.setPromptText("Select Province");

        postalCodeTextField.setPrefWidth(120);

        // setting button width
        button1.setPrefWidth(90);
        button2.setPrefWidth(90);
        button3.setPrefWidth(90);
        button4.setPrefWidth(90);
        button5.setPrefWidth(90);
        button6.setPrefWidth(90);

        // spacing
        gridPane1.setHgap(10);
        gridPane1.setVgap(15);
        gridPane2.setHgap(10);
        gridPane2.setVgap(15);
        gridPane3.setHgap(5);

        // padding the buttons
        gridPane3.setPadding(new Insets(35, 5, 0, 5));

        // setting the Grid alignment
        gridPane1.setAlignment(Pos.CENTER);
        gridPane2.setAlignment(Pos.CENTER);
        gridPane3.setAlignment(Pos.CENTER);

        // adding all GridPane to the vBox
        vBoxLayout.getChildren().add(gridPane1);
        vBoxLayout.getChildren().add(gridPane2);
        vBoxLayout.getChildren().add(gridPane3);

        // adding VBox to the scene
        Scene scene = new Scene(vBoxLayout, 600, 200);
        stage.setScene(scene);

        // and showing the stage
        stage.show();
    }

    /**
     * Main method to launch application
     * @param args command line arguments
     */
    public static void main(String[] args) { Application.launch(); }
}