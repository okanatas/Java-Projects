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

package task1.sequentialBasicDemo;

/**
 * This class is the class that will perform the multiplication of two matrices sequentially.
 * @author Okan Atas, created on April 2, 2021
 * @version 1.0.0
 */
public class SequentialMatrixBasicDemo {

    private static final int ROW = 4;
    private static final int COL = 4;
    private static long duration = 0;

    /**
     * This method prints the specified matrix.
     * @param M matrix
     */
    static void printMatrix(double[][] M){
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++)
            System.out.printf("%.0f ", M[i][j] );
            System.out.println();
        }
    }

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

        // multiplication of the two matrices
        for (i = 0; i < ROW; i++) {
            for (j = 0; j < COL; j++) {
                for (k = 0; k < ROW; k++)
                    resultMatrix[i][j] += c[i][k] * d[k][j];
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
        double[][] firstMatrix = {
                { 1, 1, 2, 3},
                { 2, 2, 2, 4},
                { 3, 3, 3, 2},
                { 2, 4, 1, 4}};

        double[][] secondMatrix = {
                { 1, 2, 2, 3},
                { 2, 1, 2, 4},
                { 3, 0, 3, 2},
                { 4, 1, 0, 4}};

        double[][] resultMatrix = sequentialMultiplyMatrix(firstMatrix, secondMatrix);


        // Print the matrices
        System.out.println("\n*** First Matrix ***");
        printMatrix(firstMatrix);
        System.out.println("\n*** Second Matrix ***");
        printMatrix(secondMatrix);

        // Print the result
        System.out.println("\n*** Result Matrix ***");
        printMatrix(resultMatrix);

        System.out.println("\nDuration of Sequential Multiplication  : "
                + duration
                + " nanoseconds");
    }
}