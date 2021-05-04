package task1;

import java.util.Scanner;

/**
 * This class was created for the Connect4 Game
 * @author Okan Atas, created on March 21, 2021
 * @version 1.1.1
 */
public class Connect4Game {

    static int row = 0;
    static int col = 0;
    private static String validationMessage;
    private static boolean playAgain = false;

    /**
     * Entry point of Connect4 Game (main method)
     * @param args command line arguments
     */
    public static void main(String[] args) {
        screenController();
    }

    /**
     * This method for to control the game screens
     * ( game rule screen, play screen and, terminating the game )
     */
    private static void screenController(){
        Scanner in = new Scanner(System.in);
        boolean displayed = false;
        do{
            int menuOption = menu(in);

            switch (menuOption) {
                case 1 -> {
                    displayGameRules();
                    displayed = true;
                }
                case 2 -> {
                    startGame();
                    displayed = true;
                }
                case 3 -> System.exit(0);
            }
        }while (displayed);
    }

    /**
     * This method displays the Connect4 Game Rules
     */
    private static void displayGameRules(){
        System.out.println("\nHOW TO PLAY");
        System.out.println("""
                1.\tRed player everytime will be the first.
                2.\tOn your turn, drop one of your disk at the bottom of ANY of the column in the game board.
                3.\tPlay alternates until one player gets FOUR disks of his or her color in a row.
                4.\tThe four in a row can be horizontal, vertical or diagonal.""");
        promptEnterKey();
    }

    /**
     * This methods asks user "ENTER" tho continue
     */
    private static void promptEnterKey(){
        System.out.print("Press \"ENTER\" to continue...");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }

    /**
     * This method starts the game
     */
    private static void startGame(){
        Scanner in = new Scanner(System.in);

        char[][] gameBoard = new char[6][7];
        String player = "Red";
        boolean winner = false;
        String valueForCol;
        String respond;

        do{
            int turn = 1;
            // set fields
            fillEmptyFields(gameBoard);
            // play a turn
            while (!winner && turn <= 42) {
                boolean isPlayValid = true;
                do {
                    System.out.println("\nFor Menu Enter (M)");
                    System.out.println("_________________");
                    display(gameBoard);

                    if (isPlayValid) {
                        System.out.print("Drop a " + player + " disk at column (0-6): ");
                    } else {
                        System.out.print(getValidationMessage() + " The " + player + " player try again (0-6): ");
                    }
                    valueForCol = in.nextLine();

                    if(valueForCol.equalsIgnoreCase("M")){
                        screenController();
                    }else {
                        //validate play
                        isPlayValid = validateColumn(valueForCol, gameBoard);
                    }

                } while (!isPlayValid);

                // drop the disk at specified column
                for (row = gameBoard.length - 1; row >= 0; row--) {
                    if (gameBoard[row][col] == ' ') {
                        gameBoard[row][col] = player.charAt(0);
                        break;
                    }
                }

                // find if there is a winner
                winner = isWinner(player.charAt(0), gameBoard, turn);

                // switch players
                if(!winner){
                    if (player.equals("Red")) {
                        player = "Yellow";
                    } else {
                        player = "Red";
                    }
                }
                turn++;
            }

            System.out.println("\n  Result Screen");
            System.out.println("_________________");
            display(gameBoard);

            if (winner) {
                System.out.println("The " + player + " player won.");
            } else {
                System.out.println("Nobody won. The game is tie.");
            }

            do{
                System.out.print("Would you like to play again? (Y/N) : ");
                respond = (in.nextLine()).toUpperCase();
                winner = false;
            }while(!validateYesNo(respond));

        }while (playAgain);
    }

    /**
     * This method sets fields as empty to get a refreshed board
     * @param gameBoard game board
     */
    private static void fillEmptyFields(char[][] gameBoard){
        // fill the game board
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                gameBoard[row][col] = ' ';
            }
        }
    }

    /**
     * This method displays the menu screen
     * @param input player input for menu option
     * @return selected option
     */
    private static int menu(Scanner input){
        boolean accepted = false;
        int numericOption = 0;
        System.out.println("\nWelcome to Connect4");
        System.out.println("""
                1. How to Play
                2. Play
                3. Exit""");
        System.out.print("Enter an option : ");

        while(!accepted){
            String option = input.nextLine();
            boolean numeric = option.matches("-?\\d+(\\.\\d+)?");
            if(numeric){
                numericOption = Integer.parseInt(option);
                if(numericOption < 1 || numericOption > 3){
                    System.out.print("Please enter (1-3) : ");
                }else {
                    accepted = true;
                }
            }else{
                System.out.print("Value is not numeric, please enter (1-3) : ");
            }
        }
        return numericOption;
    }

    /**
     * This method for design of game board
     * @param gameBoard game board
     */
    public static void display(char[][] gameBoard) {
        for (char[] chars : gameBoard) {
            System.out.print(" |");
            for (int col = 0; col < gameBoard[0].length; col++) {
                System.out.print(chars[col]);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("_________________");
        System.out.println("  0 1 2 3 4 5 6");
        System.out.println();
    }

    /**
     * This method for validation entered column value by the player
     * @param value entered column by the player
     * @param gameBoard game board
     * @return boolean if column entry is valid
     */
    public static boolean validateColumn(String value, char[][] gameBoard) {
        // check if it is numeric value -> using regex
        boolean numeric = value.matches("-?\\d+(\\.\\d+)?");
        // boolean result = false;

        if(numeric){
            int numericValue = Integer.parseInt(value);
            // check column number
            if (numericValue < 0 || numericValue >= gameBoard[0].length) {
                setValidationMessage("Please enter between 0 and 6.");
                return false;
            }else{
                col = numericValue;
            }

            // check if column available
            if (gameBoard[0][numericValue] != ' ') {
                setValidationMessage("This column is full.");
                return false;
            }
        }
        return true;
    }

    /**
     * This method sets validation messages depends on the situation
     * @param message validation message
     */
    private static void setValidationMessage(String message){
        validationMessage = message;
    }

    /**
     * Getter method for validation message
     * @return validation message
     */
    private static String getValidationMessage(){
        return validationMessage;
    }

    /**
     * This method validates "yes" or "no" entry
     * @param response player response
     * @return boolean value depends on player response
     */
    public static boolean validateYesNo(String response) {
        if(response.charAt(0) == 'Y' || response.charAt(0) == 'N')
        {
            playAgain = response.charAt(0) == 'Y';
            return true;
        } else {
            System.out.println("Please enter Y or N");
            return false;
        }
    }

    /**
     * This method determines if any player won the game,
     * it checks only entered area without searching all game board
     * @param player current player
     * @param gameBoard game board
     * @return boolean value if there is a winner
     */
    public static boolean isWinner(char player, char[][] gameBoard, int turn) {

        boolean[] ways = {true,true,true,true,true,true,true,true};
        boolean winnerValidated = false;

        int i = 1;

        if (turn >= 7) {
            while (!winnerValidated && i <= 3) {
                if (row != 0) {
                    if(ways[0]){
                        if (col - i >= 0 && row - i >= 0) {
                            if (gameBoard[row - i][col - i] != player) {
                                ways[0] = false;
                            }
                        } else {
                            ways[0] = false;
                        }
                    }

                    if(ways[1]){
                        if (row - i >= 0) {
                            if (gameBoard[row - i][col] != player) {
                                ways[1] = false;
                            }
                        } else {
                            ways[1] = false;
                        }
                    }

                    if(ways[2]){
                        if (col + i <= 6 && row - i >= 0) {
                            if (gameBoard[row - i][col + i] != player) {
                                ways[2] = false;
                            }
                        } else {
                            ways[2] = false;
                        }
                    }
                } else {
                    ways[0] = false;
                    ways[1] = false;
                    ways[2] = false;
                }

                if (row != 5) {
                    if(ways[3]){
                        if (row + i <= 5 && col - i >= 0) {
                            if (gameBoard[row + i][col - i] != player) {
                                ways[3] = false;
                            }
                        } else {
                            ways[3] = false;
                        }
                    }

                    if(ways[4]){
                        if (row + i <= 5) {
                            if (gameBoard[row + i][col] != player) {
                                ways[4] = false;
                            }
                        } else {
                            ways[4] = false;
                        }
                    }

                    if(ways[5]){
                        if (row + i <= 5 && col + i <= 6) {
                            if (gameBoard[row + i][col + i] != player) {
                                ways[5] = false;
                            }
                        } else {
                            ways[5] = false;
                        }
                    }
                } else {
                    ways[3] = false;
                    ways[4] = false;
                    ways[5] = false;
                }

                if(ways[6]){
                    if (col - i >= 0) {
                        if (gameBoard[row][col - i] != player) {
                            ways[6] = false;
                        }
                    } else {
                        ways[6] = false;
                    }
                }

                if(ways[7]){
                    if (col + i <= 6) {
                        if (gameBoard[row][col + i] != player) {
                            ways[7] = false;
                        }
                    } else {
                        ways[7] = false;
                    }
                }

                if(i == 1){
                    if (ways[0] && ways[5]) {
                        if (col - 2 >= 0 && row - 2 >= 0) {
                            if (gameBoard[row - 2][col - 2] == player) {
                                winnerValidated = true;
                                break;
                            }
                        }

                        if (col + 2 <= 6 && row + 2 <= 5) {
                            if (gameBoard[row + 2][col + 2] == player) {
                                winnerValidated = true;
                                break;
                            }
                        }
                    }

                    if (ways[2] && ways[3]) {
                        if (col + 2 <= 6 && row - 2 >= 0) {
                            if (gameBoard[row - 2][col + 2] == player) {
                                winnerValidated = true;
                                break;
                            }
                        }

                        if (col - 2 >= 0 && row + 2 <= 5) {
                            if (gameBoard[row + 2][col - 2] == player) {
                                winnerValidated = true;
                                break;
                            }
                        }
                    }

                    if (ways[6] && ways[7]) {
                        if (col + 2 <= 6) {
                            if (gameBoard[row][col + 2] == player) {
                                winnerValidated = true;
                                break;
                            }
                        }

                        if (col - 2 >= 0) {
                            if (gameBoard[row][col - 2] == player) {
                                winnerValidated = true;
                                break;
                            }
                        }
                    }
                }

                if (i == 3) {
                    // check for any way is true : which means that way is ended with winner value ( R or Y )
                    for (boolean anyWayFinalize : ways) {
                        if (anyWayFinalize) {
                            winnerValidated = true;
                            break;
                        }
                    }
                }

                i++;
            }
        }
        return winnerValidated;
    }
}