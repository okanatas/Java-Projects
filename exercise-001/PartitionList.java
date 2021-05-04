import java.util.Scanner;

/**
 * This class is for the functions required in order to have array partition
 *
 * @author Okan Atas
 * created on 2021/30/01
 */

public class PartitionList {
    /**
     * This method is for to collect user values and send them to the partition(int[]) for processing
     */
    public static void partitionManager() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the list: ");

        // to store array.length as int enteredListSize
        int enteredListSize = input.nextInt();

        // creates an array in the memory of length enteredListSize
        int[] enteredList = new int[enteredListSize];

        for (int i = 0; i < enteredListSize; i++) {
            //reading array elements from the user list
            enteredList[i] = input.nextInt();
        }

        System.out.println("Number of elements to be processed: " + enteredList.length);
        System.out.println("Pivot element: " + enteredList[0]);

        System.out.println("******* The List After Partition *******");

        // accessing partitionedList elements using the for loop
        for (int elementOfList: partition(enteredList)) {
            System.out.print(elementOfList + " ");
        }

        input.close();
    }

    /**
     * This method is for rearrange the incoming array, values that less than pivot on the left
     * and the greater ones is on the right
     *
     * @param enteredList incoming array for partition
     * @return returning rearranged array
     */
    public static int[] partition(int[] enteredList){
        // creates an array in the memory of length enteredList.length
        int[] partitionedList = new int[enteredList.length];

        for (int i = 1, j = 0, m = enteredList.length - 1; i < enteredList.length; i++) {
            if (enteredList[i] <= enteredList[0]) {
                partitionedList[j] = enteredList[i];
                j++;
            } else {
                partitionedList[m] = enteredList[i];
                m--;
            }

            // end of the loop values of m and j will have the same value which is the remaining empty index for pivot
            if (j == m) {
                partitionedList[j] = enteredList[0];
            }
        }
        // returning rearranged array
        return partitionedList;
    }
}
