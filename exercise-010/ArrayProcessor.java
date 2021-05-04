package task2;

/**
 * Functional interface that has apply function. It will be type of Lambda practice variables.
 * @author Okan Atas, created on March 14, 2021
 * @version 1.1.0
 */
@FunctionalInterface
public interface ArrayProcessor {
    /**
     * The method that takes double array as an argument and returns double value.
     * @param array any array of double
     * @return double value
     */
    double apply( double[] array );
}