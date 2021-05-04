import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * This class was created for to control Address Book Application
 * @author Okan Atas, created on March 3, 2021
 * @version 1.2.0 - updated on March 5 - The trim method has been added to prevent user error
 */
public class EntryController {

    // file path
    static final String FILEPATH = "address-info.txt";

    // current entry info
    static int currentEntry = -1;
    static int[] currentEntryIndexes = new int[2];

    /**
     * This method controls the process of adding a new entry in the file
     */
    static void addEntry(){

        boolean allFieldsFilled = areFieldsFilled();

        if(allFieldsFilled){
            // creating csv line
            String combinedFields = turnCurrentEntryIntoCsvStyle();

            // write to the file
            try {
                writeToFile(combinedFields);
                DialogBox.display("Successful", "New entry successfully added");
            } catch (IOException e) {
                e.printStackTrace();
            }
            clearFields();

            // after cleaning set back to first-time launch status
            currentEntry = -1;
        }else{
            DialogBox.display("Empty Field(s)", "Please fill all the fields");
        }
    }

    /**
     * This method controls the process of getting the first entry in the file
     */
    static void getFirstEntry(){
        try {
            String firstEntry = lineGetter(1);

            // split
            String[] splitEntry = splitLineString(firstEntry);

            // set to show on the fields
            setFields(splitEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method controls the process of getting the next entry in the file
     */
    static void getNextEntry(){
        try {
            if(currentEntry != getNumberOfLine())
            {
                if(currentEntry < 0){currentEntry++;}
                currentEntry++;
                String nextEntry = lineGetter(currentEntry);

                // split
                String[] splitEntry = splitLineString(nextEntry);

                // set to show on the fields
                setFields(splitEntry);
            }else{
                DialogBox.display("End of the List", "This is the last entry on the list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method controls the process of getting the previous entry in the file
     */
    static void getPreviousEntry(){
        try {
            if(currentEntry != 1)
            {
                if(currentEntry < 0){currentEntry = getNumberOfLine() + 1;}
                currentEntry--;
                String previousEntry = lineGetter(currentEntry);

                // split
                String[] splitEntry = splitLineString(previousEntry);

                // set to show on the fields
                setFields(splitEntry);
            }
            else{
                DialogBox.display("End of the List", "This is the first entry on the list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method controls the process of getting the last entry in the file
     */
    static void getLastEntry(){
        try {
            String lastEntry = lineGetter(getNumberOfLine());

            // split
            String[] splitEntry = splitLineString(lastEntry);

            // set to show on the fields
            setFields(splitEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the validation stage of the method update process.
     * If the process is valid, the next stage is passed, if not, a dialog box is displayed.
     */
    static void updateEntry(){
        // creating csv line
        String combinedFields = turnCurrentEntryIntoCsvStyle();

        // write to the file
        try {
            if(currentEntry != -1)
            {
                boolean completelyFilled = areFieldsFilled();
                if(completelyFilled)
                {
                    updateTheFile(combinedFields + "\n", false);
                    DialogBox.display("Successful", "Entry successfully updated");
                }
                else{
                    boolean completelyEmpty = areFieldsCompletelyEmpty();
                    if(completelyEmpty)
                    {
                        updateTheFile(combinedFields + "\n", true);
                        DialogBox.display("Successful", "Entry successfully deleted");
                    }
                    else{
                        DialogBox.display("Invalid", "Do you want to delete or update?\n\nFor DELETE, empty all the fields\n\nFor the UPDATE, fill all the fields");
                    }
                }
            }
            else{
                DialogBox.display("No Entry", "Does not specified any information to update");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows the file to be updated.
     * Determines whether the process is update or delete and executes the necessary action.
     * @param data newly entered information
     * @param isDelete delete or update controller
     * @throws IOException I/O exception for RandomAccessFile
     */
    private static void updateTheFile(String data, boolean isDelete)
            throws IOException {

        RandomAccessFile file = new RandomAccessFile(FILEPATH, "rw");

        int X = currentEntryIndexes[0];
        int Y = currentEntryIndexes[1] + 2;
        int Z = ((int)(file.length()) - Y);

        file.seek(0);
        byte[] bytes1 = new byte[X];
        file.read(bytes1);

        byte[] bytes2 = data.getBytes();

        file.seek(Y);
        if(Z == -1){Z = 0;}
        byte[]  bytes3 = new byte[Z];
        file.read(bytes3);

        byte[] bytes1And2 = new byte[bytes1.length + bytes2.length];

        System.arraycopy(bytes1,0,bytes1And2,0,bytes1.length);
        System.arraycopy(bytes2,0,bytes1And2,bytes1.length,bytes2.length);

        byte[] bytes1And2And3 = new byte[bytes1And2.length + bytes3.length];

        System.arraycopy(bytes1And2,0,bytes1And2And3,0,bytes1And2.length);
        System.arraycopy(bytes3,0,bytes1And2And3,bytes1And2.length,bytes3.length);

        file.seek(0);

        if(!isDelete)
        {
            // if it is the last entry
            if(Z == 0){
                // copy to prevent creating an empty line at the end of the file
                byte[] copyOne = new byte[bytes1And2.length - 1];

                file.setLength(copyOne.length);

                if (copyOne.length >= 0) System.arraycopy(bytes1And2, 0, copyOne, 0, copyOne.length);

                file.write(copyOne);
            }
            else {
                file.setLength(bytes1And2And3.length);
                file.write(bytes1And2And3);
            }

            // to fix current entry indexes
            findLineAndSetIndexes(currentEntry);

        }else{
            // if it is the last entry
            if(Z == 0){
                // copy to prevent creating an empty line at the end of the file
                byte[] updatedBytes1 = new byte[bytes1.length - 1];

                file.setLength(updatedBytes1.length);

                if (updatedBytes1.length >= 0) System.arraycopy(bytes1, 0, updatedBytes1, 0, updatedBytes1.length);

                file.write(updatedBytes1);

                // to set new current entry which is the last entry
                getLastEntry();
            }
            else {

                byte[] bytes1And3 = new byte[bytes1.length + bytes3.length];

                System.arraycopy(bytes1,0,bytes1And3,0,bytes1.length);
                System.arraycopy(bytes3,0,bytes1And3,bytes1.length,bytes3.length);

                file.setLength(bytes1And3.length);
                file.write(bytes1And3);

                // to set new current entry
                setFields(splitLineString(lineGetter(currentEntry)));
            }
        }

        file.close();
    }

    /**
     * This method is for to write information to the file
     * @param data newly entered information
     * @throws IOException I/O exception for RandomAccessFile
     */
    private static void writeToFile(String data)
            throws IOException {

        RandomAccessFile file = new RandomAccessFile(FILEPATH, "rw");
        int fileLength = (int) file.length();

        if (fileLength > 0) {
            data = "\n" + data;
        }

        file.seek(fileLength);
        file.write(data.getBytes());
        file.close();
    }

    /**
     * This method eliminates leading and trailing spaces entered with user error.
     * @return trimmed entries as string array format
     */
    private static String[] trimCurrentEntry() {
        String firstname = AddressBook.firstNameTextField.getText();
        String lastname = AddressBook.lastNameTextField.getText();
        String city = AddressBook.cityTextField.getText();
        String province = AddressBook.provinceComboBox.getValue();
        String postalCode = AddressBook.postalCodeTextField.getText();

        return new String[]{firstname.trim(), lastname.trim(), city.trim(), province == null ? "" : province.trim(), postalCode.trim()};
    }

    /**
     * This method converts the value entered by the user to the csv format for human readability.
     * @return csv formatted string
     */
    private static String turnCurrentEntryIntoCsvStyle() {
        String[] trimmedEntry = trimCurrentEntry();

        return trimmedEntry[0] + "," + trimmedEntry[1] + "," +
                trimmedEntry[2] + "," + trimmedEntry[3] + "," + trimmedEntry[4];
    }

    /**
     * This method validates all fields are fully filled or not.
     * @return boolean that indicates whether fields are filled or not
     */
    private static boolean areFieldsFilled() {
        String[] trimmedEntry = trimCurrentEntry();

        return !trimmedEntry[0].equals("") && !trimmedEntry[1].equals("") &&
                !trimmedEntry[2].equals("") &&  !trimmedEntry[3].equals("") &&
                !trimmedEntry[4].equals("");
    }

    /**
     * This method validates all fields are completely empty or not.
     * @return boolean that indicates whether fields are completely empty or not
     */
    private static boolean areFieldsCompletelyEmpty() {
        String[] trimmedEntry = trimCurrentEntry();

        return trimmedEntry[0].equals("") && trimmedEntry[1].equals("") &&
                trimmedEntry[2].equals("") &&  trimmedEntry[3].equals("") &&
                trimmedEntry[4].equals("");
    }

    /**
     * This method is for to get specified line by the user.
     * @param specifiedLine requested line number
     * @return string that consist of requested line
     * @throws IOException I/O exception for RandomAccessFile
     */
    private static String lineGetter(int specifiedLine) throws IOException {

        findLineAndSetIndexes(specifiedLine);

        RandomAccessFile file = new RandomAccessFile(FILEPATH, "r");

        byte[] bytes = new byte[(currentEntryIndexes[1] - currentEntryIndexes[0]) + 1];
        file.seek(currentEntryIndexes[0]);
        file.read(bytes);

        //System.out.println(new String(bytes));
        String result = new String(bytes);

        file.close();

        return result;
    }

    /**
     * This method is for finding the specified line and store its index values for the purpose of showing it to the user.
     * @param specifiedLine requested line number
     * @throws IOException I/O exception for RandomAccessFile
     */
    private static void findLineAndSetIndexes(int specifiedLine) throws IOException{
        boolean corrected = false;
        int completedLine = 0;
        int lineIndex = 0;
        int firstIndex = 0;
        int lastIndex = 0;

        RandomAccessFile file = new RandomAccessFile(FILEPATH, "r");
        file.seek(0);

        while (!corrected) {
            int check = file.read();

            if (check == 10 || check == -1) {
                completedLine++;
                if (completedLine == specifiedLine) {
                    lastIndex = lineIndex - 1;
                    corrected = true;
                } else {
                    firstIndex = lineIndex + 1;
                }
            }
            lineIndex++;
        }

        // setting indexes
        currentEntryIndexes[0] = firstIndex;
        currentEntryIndexes[1] = lastIndex;
        currentEntry = specifiedLine;

        file.close();
    }

    /**
     * This method is for splitting the csv formatted line.
     * @param line csv formatted line from the file
     * @return string array for split values
     */
    private static String[] splitLineString(String line) {
        return line.split(",", 5);
    }

    /**
     * This method is to set the fields to be shown to the user.
     * @param splitEntry string array containing split values to be displayed to the user
     */
    private static void setFields(String[] splitEntry){
        // set to show on the fields
        AddressBook.firstNameTextField.setText(splitEntry[0]);
        AddressBook.lastNameTextField.setText(splitEntry[1]);
        AddressBook.cityTextField.setText(splitEntry[2]);
        AddressBook.provinceComboBox.setValue(splitEntry[3]);
        AddressBook.postalCodeTextField.setText(splitEntry[4]);
    }

    /**
     * This method is for the clear all the fields.
     */
    private static void clearFields(){
        // clear all field
        AddressBook.firstNameTextField.clear();
        AddressBook.lastNameTextField.clear();
        AddressBook.cityTextField.clear();
        AddressBook.provinceComboBox.setValue("");
        AddressBook.postalCodeTextField.clear();
    }

    /**
     * This method is to find out how many lines (address entries) have been written to the file.
     * @return number of line in the file
     * @throws IOException I/O exception for RandomAccessFile
     */
    private static int getNumberOfLine() throws IOException {

        boolean corrected = false;
        int lineNumber = 0;

        RandomAccessFile file = new RandomAccessFile(FILEPATH, "r");
        file.seek(0);

        while (!corrected) {
            int check = file.read();

            if (check == 10) {
                lineNumber++;
            }

            // if end of the file : -1
            if(check == -1) {
                corrected = true;
            }
        }
        return lineNumber + 1;
    }
}