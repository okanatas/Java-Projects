import java.util.Random;

/**
 * This class is for the function required to run The Craps Game
 *
 * @author Okan Atas
 * created on 2021/30/01
 */

public class CrapsGame {
    /**
     * This method is for the function that starts The Craps Game
     */
    public static void startCrapsGame() {
        boolean isContinue = true;
        int point = 0, dice1, dice2, sumOfDices;

        while (isContinue) {
            // initialization of random values to two different dice
            dice1 = new Random().nextInt(6) + 1;
            dice2 = new Random().nextInt(6) + 1;

            // initialization of the total values of the dices to the sumOfDices
            sumOfDices = dice1 + dice2;

            System.out.println("You rolled " + dice1 + " + " + dice2 + " = " + sumOfDices);

            // if : the existence of a winning or losing value ( no established value )
            if (point == 0) {
                if (sumOfDices == 2 || sumOfDices == 3 || sumOfDices == 12) {
                    System.out.println("Craps, Better Luck Next Time, You lose");
                    isContinue = false;
                } else if (sumOfDices == 7 || sumOfDices == 11) {
                    System.out.println("Congratulations, You win");
                    isContinue = false;
                } else {
                    point = sumOfDices;
                    System.out.println("Point is (established) set to " + point);
                }
                // else : game played with established value
            } else {
                if (sumOfDices == 7) {
                    System.out.println("Craps, Better Luck Next Time, You lose");
                    isContinue = false;
                }

                if (sumOfDices == point) {
                    System.out.println("Congratulations, You win");
                    isContinue = false;
                }
            }
        }
    }
}
