package task2;

import java.util.Scanner;

/**
 * This class was created to do Lambda exercises.
 * @author Okan Atas, created on March 14, 2021
 * @version 1.1.0
 */
public class LambdaPractice {

    /**
     * Lambda expression to find the maximum value in the array.
     */
    public static final ArrayProcessor findMaximum = userList -> {
        double maximumValue = userList[0];
        for (double v : userList) {
            if (v > maximumValue)
                maximumValue = v;
        }
        return maximumValue;
    };

    /**
     * Lambda expression to find the minimum value in an array.
     */
    public static final ArrayProcessor findMinimum = userList -> {
        double minimumValue = userList[0];
        for (double v : userList) {
            if (v < minimumValue)
                minimumValue = v;
        }
        return minimumValue;
    };

    /**
     * Lambda expression to find the sum of the values in the array.
     */
    public static final ArrayProcessor findSum = userList -> {
        double sumOfValues = 0;
        for (double v : userList) {
            sumOfValues += v;
        }
        return sumOfValues;
    };

    /**
     * Lambda expression to find the average of the values in the array.
     */
    public static final ArrayProcessor findAverage = userList ->
            findSum.apply(userList) / userList.length;

    /**
     * This method counts how many times the specified value has been seen in the list.
     * @param value specified value to count
     * @return number of times value appeared
     */
    public static ArrayProcessor counter(double value) {
        return array -> {
            int counter = 0;
            for (double v : array) {
                if (v == value)
                    counter++;
            }
            return counter;
        };
    }

    /**
     * Main method to test Lambda expressions
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the list : ");
        String listStr = input.nextLine();

        System.out.print("Which value would you like to count from this list? : ");
        double specifiedValue = input.nextDouble();

        String[] splitList = listStr.split(" ");

        double[] db = new double[splitList.length];

        for (int i = 0; i < splitList.length; ++i) {
            db[i] = Double.parseDouble(splitList[i]);
        }

        System.out.println("Maximum value of the list is " + findMaximum.apply(db));
        System.out.println("Minimum value of the list is " + findMinimum.apply(db));
        System.out.println("Sum of the list is " + findSum.apply(db));
        System.out.printf("Average of the list is %.2f\n", findAverage.apply(db));
        System.out.printf("The value of %.2f appeared %.0f times in your list.", specifiedValue, counter(specifiedValue).apply(db));
    }
}