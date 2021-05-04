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

package task1.parallelBasicDemo;

/**
 * This class is the thread class that will perform the multiplication of two matrices.
 * @author Okan Atas, created on April 2, 2021
 * @version 1.0.0
 */
public class MatrixThreadBasicDemo implements Runnable{

    private final int X;
    private final int Y;
    private final int COL;

    private final double [][] firstMatrix;
    private final double [][] secondMatrix;
    private final double [][] resultMatrix;

    /**
     * Thread class constructor
     * @param X first index
     * @param Y second index
     * @param COL column number
     * @param firstMatrix a matrix
     * @param secondMatrix a matrix
     * @param resultMatrix result matrix
     */
    public MatrixThreadBasicDemo(int X, int Y, int COL, double [][] firstMatrix, double [][] secondMatrix, double [][] resultMatrix){
        this.X = X;
        this.Y = Y;
        this.COL = COL;
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
    }

    /**
     * Actual override thread method of run()
     */
    @Override
    public void run() {

        // helper indexes
        int i, j, k;

        // actual multiplication process
        for (i = this.X; i < Y; i++) {
            for (j = 0; j < COL; j++) {
                for (k = 0; k < COL; k++)
                    resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
            }
        }
    }
}
