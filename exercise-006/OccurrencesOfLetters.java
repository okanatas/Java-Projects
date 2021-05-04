import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class was created to determine the number of letters in any chosen file
 * @author Okan Atas,
 * created on February 21, 2021
 */
public class OccurrencesOfLetters {
    /**
     * The main method of determining the existing number of letters
     * @param args command line arguments (not used in this project)
     */
    public static void main(String[] args) {

        String filePath = "";

        System.out.print("Enter a file name: ");

        File file = new File(filePath + (new Scanner(System.in).next()));

        if (!file.exists()) {
            System.out.println(file + " doesn't exist");
            System.exit(1);
        }
        String buffer;

        // number of element for A to z in ASCII table
        int[] capitalLowerAz = new int[58];

        try (Scanner input = new Scanner(file)) {

            while (input.hasNext()) {
                buffer = input.nextLine();
                for (char eachCharacter : buffer.toCharArray()) {
                    if (isLetter(eachCharacter)) {

                        // find out : which index of alphabet?
                        capitalLowerAz[eachCharacter - 'A']++;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        System.out.print("\n");
        for (int i = 0; i < (capitalLowerAz.length - 6) / 2; i++) {
            System.out.println("Number of " + (char)(i + 'A') + " is : " + capitalLowerAz[i]);
            System.out.println("Number of " + (char)(i + 'a') + " is : " + capitalLowerAz[i + 32]);
        }

    }

    /**
     * This method verifies whether the incoming value is a letter or not
     * @param ch character value from file
     * @return boolean value
     */
    private static boolean isLetter(char ch) {
        return ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'));
    }

}