import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class was created for the Hangman game
 * @author Okan Atas,
 * created on February 21, 2021
 */
public class Hangman {
    /**
     * This is the main method of linking methods, and running the Hangman game
     * @param args command line arguments (not used in this project)
     * @throws Exception exception control for reading file
     */
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        char symbol = '*';
        ArrayList<Character> guessedLetters = new ArrayList<>();
        int menuOption;
        boolean terminate = false;
        boolean isFirstGame = true;

        System.out.println("**** Welcome to Hangman ****");

        do {
            menuOption = menu(input, isFirstGame);

            if (menuOption == 1) {
                char[] pickedWord = pickTheWord();

                char[] unknownLetterPointers = new char[pickedWord.length];

                // Fill with symbol : * (in this case)
                Arrays.fill(unknownLetterPointers, symbol);

                int wrongGuess = 0;
                System.out.print("\n");

                do {
                    System.out.print("(Guess) Enter a letter in word ");
                    System.out.print(unknownLetterPointers);
                    System.out.print(" > ");
                    String guess = input.next();

                    guessedLetters.add(guess.charAt(0));

                    if (!guessValidationProcess(pickedWord, unknownLetterPointers, guessedLetters))
                        wrongGuess++;

                } while (checkStillUnknown(unknownLetterPointers, symbol));

                // Print results
                printResult(pickedWord, wrongGuess);
                isFirstGame = false;
                guessedLetters.clear();

                System.out.println("\nI hope you enjoyed!\nWhat would you like to do now?");

            } else if (menuOption == 2) {
                appendNewWordToTheFile(input);
            } else {
                System.out.println(isFirstGame
                        ? "\nLeaving without playing Hangman? I am sorry :( Bye..."
                        : "\nThanks for playing Hangman :) See you again...");
                terminate = true;
            }
        } while (!terminate);
    }

    /**
     * This method shows the starting menu in the hangman game
     * @param input Scanner object
     * @param isFirstGame boolean value that indicates whether the game was played for the first time
     * @return validated menu option
     */
    public static int menu(Scanner input, boolean isFirstGame) {
        System.out.println("\n-------> Game Menu <------- ");
        System.out.println(isFirstGame ? "\t1: Play" : "\t1: Play Again");
        System.out.println("\t2: Add a New Word\n\t0: Exit");
        System.out.print("Please choose an option: ");

        return checkMenuOptionValue(input);
    }

    /**
     * This method creates a file object and allows us to select a word from the existing file
     * @return chosen word from file
     * @throws FileNotFoundException file not found exception
     */
    public static char[] pickTheWord() throws FileNotFoundException {

        File file = openFile();

        // Arraylist for each word in the file ( String and unknown amount)
        ArrayList<String> wordsFromFile = new ArrayList<>();
        try (
                Scanner wordFromFile = new Scanner(file);
        ) {
            while (wordFromFile.hasNext()) {
                // add() method : to add word to the ArrayList one by one
                wordsFromFile.add(wordFromFile.next());
            }
        }

        // Choose a word form ArrayList randomly
        String chosenWord = wordsFromFile.get((int) (Math.random() * wordsFromFile.size()));

        return chosenWord.toCharArray();
    }

    /**
     * This method opens the existing file and initializes it to the file object
     * @return file object
     */
    public static File openFile() {
        File file = new File("hangman.txt");

        // Check if file exists
        if (!file.exists()) {
            System.out.print(file.getName() + " does not exist");
            System.exit(1);
        }
        return file;
    }

    /**
     * This method considers and verifies all conditions during word prediction
     * Please see @see Hangman#printReport(int, char) for all conditions
     *
     * @param pickedWord picked word from file
     * @param unknownLetterPointers  a variable that indicates unknown letters
     * @param guessedLetters array list for guessed letters by user
     * @return boolean value
     */
    public static boolean guessValidationProcess(char[] pickedWord, char[] unknownLetterPointers, ArrayList<Character> guessedLetters) {
        boolean correctGuess = false;
        int reportCase = 3;
        char highlightedLetter = '\0';

        char lastRecord = guessedLetters.get((guessedLetters.size() - 1));

        for (int i = 0; i < (guessedLetters.size() - 1); i++) {
            if (lastRecord == guessedLetters.get(i)) {
                for (char c : pickedWord) {
                    if (lastRecord == c) {
                        reportCase = 3;
                        break;
                    } else {
                        correctGuess = true;
                        reportCase = 1;
                        highlightedLetter = guessedLetters.get(i);
                    }
                }
                break;
            }
        }

        if (reportCase != 1) {
            highlightedLetter = lastRecord;

            for (int i = 0; i < pickedWord.length; i++) {
                if (pickedWord[i] == lastRecord) {
                    correctGuess = true;
                    if (unknownLetterPointers[i] == lastRecord) {
                        reportCase = 2;
                    } else {
                        unknownLetterPointers[i] = lastRecord;
                        reportCase = 0;
                    }
                }
            }
        }

        if (reportCase > 0)
            printReport(reportCase, highlightedLetter);
        return correctGuess;
    }

    /**
     * This method prints reports for three different conditions
     * @param reportCase shows report case
     * @param guessedLetter guessed letter by user
     */
    public static void printReport(int reportCase, char guessedLetter) {
        System.out.print("\t" + guessedLetter);
        switch (reportCase) {
            case 1 -> System.out.println(" is already tried, try another letter");
            case 2 -> System.out.println(" is already in the word");
            case 3 -> System.out.println(" is not in the word");
        }
    }

    /**
     * This method checks if there are still unknown letters in the word
     * @param unknownLetterPointers a variable that indicates unknown letters
     * @param symbol a symbol that replacement of unknown letters
     * @return boolean value
     */
    public static boolean checkStillUnknown(char[] unknownLetterPointers, char symbol) {
        for (char unknown : unknownLetterPointers) {
            if (unknown == symbol)
                return true;
        }
        return false;
    }

    /**
     * This method prints the result of the game
     * @param word randomly picked word from file
     * @param wrongGuess number of wrong guess
     */
    public static void printResult(char[] word, int wrongGuess) {
        System.out.print("The word is ");
        System.out.print(word);
        System.out.println(". You missed " + wrongGuess +
                (wrongGuess > 1 ? " times" : " time"));
    }

    /**
     * This method appends new words to the file
     * @param input Scanner object
     */
    public static void appendNewWordToTheFile(Scanner input) {

        System.out.print("\nPlease enter the word you want to add to the list: ");
        String str = input.next();

        String filePath = "hangman.txt";

        try {
            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(filePath, true));
            out.write(" " + str);
            out.close();
            System.out.println("The word '" + str + "' is added!");
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    /**
     * This method validates user input
     * @param input Scanner object
     * @return user input for menu option
     */
    public static int checkMenuOptionValue(Scanner input) {
        boolean incorrect = true;
        int userInput = 0;

        while (incorrect) {
            try {
                userInput = input.nextInt();
                if (userInput < 0 || userInput > 2) {
                    System.out.print("Please enter only 1, 2, or 0: ");
                } else {
                    incorrect = false;
                }
            } catch (InputMismatchException e) {
                System.err.print("Wrong input! Only an integer is acceptable: ");
                input.nextLine();
            }
        }

        return userInput;
    }
}