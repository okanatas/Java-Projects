package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class was created for to control Search Name Ranking Application.
 * @author Okan Atas, created on March 14, 2021
 * @version 1.1.0
 */
public class SearchManager {
    private static String message1 = "";
    private static String message2 = "";
    private static String message3 = "";

    private static boolean boyFound = false;
    private static boolean girlFound = false;

    private static boolean activateResultScreen = false;

    /**
     * This method for starting point of Search Name Ranking Application.
     * It validates user entered fields.
     * @return Boolean value for whether the second screen should be activated or not.
     */
    public static boolean startQuery() {
        activateResultScreen = false;
        boolean isYearValid = validateYear(AppGUI.yearField.getText().trim());
        boolean isGenderValid = validateGender(AppGUI.genderField.getText().trim().toUpperCase());
        boolean emptyFields = anyFieldsEmpty();

        if(emptyFields){
            message3 = "Please enter all fields.";
        }

        if (isYearValid && isGenderValid && !emptyFields) {
            findTheName();
        } else {
            DialogBox.display("Failed", message1, message2, message3);
            clearFields();
            message1 = "";
            message2 = "";
            message3 = "";
        }
        return activateResultScreen;
    }

    /**
     * This function is activated after validations and searches inside the file for the entered name.
     */
    private static void findTheName() {
        String [] trimmedFields = trimFields();

        // creating an ArrayList
        ArrayList<String> valuesPerLine = new ArrayList<>();

        try {
            File myObj = new File("src/baby-names-ranking-files/babynamesranking"
                    + trimmedFields[0] + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                for (int i = 0; i < 5; i++) {
                    valuesPerLine.add(i, myReader.next());
                }

                // Display ranking for the name for the year
                if (trimmedFields[1].charAt(0) == 'M' && valuesPerLine.get(1).toUpperCase().equals(trimmedFields[2])) {
                    boyFound = true;
                    break;
                }

                if (trimmedFields[1].charAt(0) == 'F' && valuesPerLine.get(3).toUpperCase().equals(trimmedFields[2])) {
                    girlFound = true;
                    break;
                }
                valuesPerLine.clear();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            DialogBox.display("File Error", "File not found.", "Please check the filepath again.", "Make sure filename also matching.");
            e.printStackTrace();
        }

        // set result screen message
        if (boyFound) {
            activateResultScreen = true;
            AppGUI.resultMessage = "Boy name " +
                    trimmedFields[2].charAt(0) + trimmedFields[2].substring(1).toLowerCase() + " is ranked #" + valuesPerLine.get(0) + " in year " + trimmedFields[0];
            boyFound = false;
        } else if (girlFound) {
            activateResultScreen = true;
            AppGUI.resultMessage = "Girl name " +
                    trimmedFields[2].charAt(0) + trimmedFields[2].substring(1).toLowerCase() + " is ranked #" + valuesPerLine.get(0) + " in year " + trimmedFields[0];
            girlFound = false;
        } else {
            activateResultScreen = true;
            String boyOrGirl = (trimmedFields[1].charAt(0) == 'M') ? "Boy name " : "Girl name ";
            AppGUI.resultMessage = boyOrGirl + trimmedFields[2].charAt(0) + trimmedFields[2].substring(1).toLowerCase()
                    + " is not ranked in year " + trimmedFields[0];
        }
    }

    /**
     * This method clears all fields.
     * If any field valid it will not clear it, then user corrects wrong ones without deleting all fields.
     * And if user decide a new search that time this method will clear all fields.
     */
    public static void clearFields() {
        // clear all field
        if(AppGUI.resultMessage.equals("after result")){
            AppGUI.yearField.clear();
            AppGUI.genderField.clear();
            AppGUI.nameField.clear();

            AppGUI.resultMessage = "";
        }else{
            if(!message1.equals("")){
                AppGUI.yearField.clear();
            }else{
                AppGUI.yearField.setText(AppGUI.yearField.getText().trim());
            }

            if(!message2.equals("")){
                AppGUI.genderField.clear();
            }else{
                AppGUI.genderField.setText(AppGUI.genderField.getText().trim());
            }

            AppGUI.nameField.setText(AppGUI.nameField.getText().trim());
        }
    }

    /**
     * This method validates year input.
     * @param enteredYear user entered year
     * @return boolean if year valid or not
     */
    private static boolean validateYear(String enteredYear) {
        // check if it is numeric value -> using regex
        boolean numeric = enteredYear.matches("-?\\d+(\\.\\d+)?");
        boolean result = false;
        if (numeric) {
            //Converting String into int using Integer.parseInt()
            int numericYear = Integer.parseInt(enteredYear);
            if ((numericYear >= 2009) && (numericYear <= 2018)) {
                result = true;
            } else {
                message1 = "Year should between 2009 and 2018.";
            }
        } else {
            message1 = "Value should be numeric.";
        }
        return result;
    }

    /**
     * This method validates gender input.
     * @param enteredGender user entered gender
     * @return boolean if gender valid or not
     */
    public static boolean validateGender(String enteredGender) {
        if (enteredGender.equals("M") || enteredGender.equals("F")) {
            return true;
        } else {
            message2 = "Gender should be M or F.";
            return false;
        }
    }

    /**
     * This method validates whether the fields are empty.
     * @return boolean if fields empty or not
     */
    private static boolean anyFieldsEmpty(){
        String [] trimmedFields = trimFields();

        return trimmedFields[0].equals("") || trimmedFields[1].equals("") || trimmedFields[2].equals("");
    }

    /**
     * This method trims all fields.
     * @return trimmed user entries
     */
    private static String[] trimFields() {
        return new String[]{AppGUI.yearField.getText().trim(), AppGUI.genderField.getText().toUpperCase().trim(),
                AppGUI.nameField.getText().toUpperCase().trim()};
    }

}