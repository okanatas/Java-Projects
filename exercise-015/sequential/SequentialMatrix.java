/**********************************************
 Workshop #9
 Course: JAC444 - Winter 2021
 Last Name: Atas
 First Name: Okan
 ID: 130627193
 Section: NFF
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature: Okan Atas
 Date: April 4, 2021
 **********************************************/

package task1.sequential;

import java.util.Random;

/**
 * This class is the class that will perform the multiplication of two matrices sequentially.
 * @author Okan Atas, created on April 2, 2021
 * @version 1.0.0
 */
public class SequentialMatrix {

    private static final int ROW = 2000;
    private static final int COL = 2000;
    private static long duration = 0;

    /**
     * The multiplication function of two matrices c[][] and d[][]
     * @param c matrix
     * @param d matrix
     * @return result matrix
     */
    public static double[][] sequentialMultiplyMatrix(double[][] c, double[][] d) {
        int i, j, k;

        double[][] resultMatrix = new double[ROW][COL];

        long startTime = System.nanoTime();

        // Multiply the two matrices
        for (i = 0; i < ROW; i++) {
            for (j = 0; j < COL; j++) {
                for (k = 0; k < ROW; k++){
                    resultMatrix[i][j] += c[i][k] * d[k][j];
                }
            }
        }

        duration = System.nanoTime() - startTime;

        return resultMatrix;
    }

    /**
     * Main class, which will execute operations.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Random random = new Random();

        double[][] firstMatrix = new double[ROW][COL];
        double[][] secondMatrix = new double[ROW][COL];

        // Randomly set each addresses of the matrices
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                firstMatrix[i][j] = random.nextDouble() * 10;
                secondMatrix[i][j] = random.nextDouble() * 10;
            }
        }

        double[][] resultMatrix = sequentialMultiplyMatrix(firstMatrix, secondMatrix);

        System.out.println("Duration of Sequential Multiplication  : "
                + duration
                + " nanoseconds");
    }
}