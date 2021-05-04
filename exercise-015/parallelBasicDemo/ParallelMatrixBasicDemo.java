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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class is the tester class to perform the multiplication of two different matrices using Thread.
 * @author Okan Atas, created on April 2, 2021
 * @version 1.0.0
 */
public class ParallelMatrixBasicDemo {
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
     * The multiplication function of two matrices a[][] and b[][]
     * @param a matrix
     * @param b matrix
     * @return result matrix
     * @throws InterruptedException exception for Executor Service
     */
    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) throws InterruptedException {

        double [][] resultMatrix = new double[ROW][COL];

        MatrixThreadBasicDemo work1 = new MatrixThreadBasicDemo(0, 2, COL, a, b, resultMatrix);
        MatrixThreadBasicDemo work2 = new MatrixThreadBasicDemo(2, 4, COL, a, b, resultMatrix);

        Thread thread1 = new Thread(work1);
        Thread thread2 = new Thread(work2);

        duration = getDuration(thread1,thread2);

        return resultMatrix;
    }

    /**
     * This method executes Threads using Executor Service and
     * calculates the execution time of the processes.
     * @param tasks threads
     * @return duration time of all threads
     * @throws InterruptedException exception for Executor Service
     */
    private static long getDuration(Runnable... tasks)
            throws InterruptedException
    {
        ExecutorService pool = Executors.newCachedThreadPool();
        long startTime = System.nanoTime();

        for (Runnable task : tasks)
            pool.execute(task);

        pool.shutdown();
        pool.awaitTermination(100, TimeUnit.SECONDS);

        return System.nanoTime() - startTime;
    }

    /**
     * Main class, which will execute operations.
     * @param args command line arguments
     * @throws InterruptedException exception for Executor Service
     */
    public static void main(String[] args) throws InterruptedException {

        double[][] firstMatrix = { { 1, 1, 2 , 3},
                { 2, 2, 2, 4 },
                { 3, 3, 3 , 2},
                { 2, 4, 1 , 4} };

        double[][] secondMatrix = { { 1, 2, 2 , 3},
                { 2, 1, 2, 4 },
                { 3, 0, 3 , 2},
                { 4, 1, 0 , 4} };

        double[][] resultMatrix = parallelMultiplyMatrix(firstMatrix, secondMatrix);


        // Print the matrices
        System.out.println("\n*** First Matrix ***");
        printMatrix(firstMatrix);
        System.out.println("\n*** Second Matrix ***");
        printMatrix(secondMatrix);

        // Print the result
        System.out.println("\n*** Result Matrix ***");
        printMatrix(resultMatrix);

        System.out.println("\nDuration : " + duration + " nanoseconds");
    }
}
