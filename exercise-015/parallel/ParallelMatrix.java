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

package task1.parallel;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class is the tester class to perform the multiplication of two different matrices using Thread.
 * @author Okan Atas, created on April 2, 2021
 * @version 1.0.0
 */
public class ParallelMatrix {
    private static final int ROW = 2000;
    private static final int COL = 2000;
    private static long duration = 0;

    /**
     * The multiplication function of two matrices a[][] and b[][]
     * @param a matrix
     * @param b matrix
     * @return result matrix
     * @throws InterruptedException exception for Executor Service
     */
    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) throws InterruptedException {

        double [][] resultMatrix = new double[ROW][COL];

        MatrixThread work1 = new MatrixThread(0, 500, COL, a, b, resultMatrix);
        MatrixThread work2 = new MatrixThread(500, 1000, COL, a, b, resultMatrix);
        MatrixThread work3 = new MatrixThread(1000, 1500, COL, a, b, resultMatrix);
        MatrixThread work4 = new MatrixThread(1500, 2000, COL, a, b, resultMatrix);

        Thread thread1 = new Thread(work1);
        Thread thread2 = new Thread(work2);
        Thread thread3 = new Thread(work3);
        Thread thread4 = new Thread(work4);

        duration = getDuration(thread1,thread2,thread3,thread4);

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

        double[][] resultMatrix = parallelMultiplyMatrix(firstMatrix, secondMatrix);


        System.out.println("Duration of Multiplication with Threads : "
                + duration
                + " nanoseconds");
    }
}
