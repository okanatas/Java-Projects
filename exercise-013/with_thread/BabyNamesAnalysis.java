package task1.with_thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is created for a program that shows unisex baby names for the specific year,
 * and alphabetically ordered names for the specific year
 * @author Okan Atas, created on March 26, 2021
 * @version 1.0.0
 */
public class BabyNamesAnalysis {
    /** Set to store boy names in specified year */
    public static Set<String> boys = Collections.synchronizedSet(new HashSet<>());
    /** Set to store girl names in specified year */
    public static Set<String> girls = Collections.synchronizedSet(new HashSet<>());

    /**
     * This method is the main method of Baby Name Analysis program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        long startTime = 0;
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter one of file names
        System.out.print("Enter a file name for baby name ranking: ");
        String fileName = input.next();

        try {
            startTime = System.nanoTime();

            File fileObj = new File("src/baby-names-ranking-files/"
                    + fileName );
            // Add names to their sets
            Scanner scanner = new Scanner(fileObj);
            while (scanner.hasNextLine()) {
                NamesSetterThread thread = new NamesSetterThread(scanner.nextLine());
                thread.start();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Please make sure the file is placed correctly!");
            e.printStackTrace();
            System.exit(1);
        }

        // Sorting HashSet using TreeSet
        SortedSet<String> treeSetBoys = new TreeSet<>(boys);
        SortedSet<String> treeSetGirls = new TreeSet<>(girls);

        // Copy of boys set
        Set<String> boysCopy;
        synchronized (boys) {
            boysCopy = new HashSet<>(boys);
        }

        // Display the names that are used for both genders
        boys.retainAll(girls);
        System.out.println((boys.size()) + " names used for both genders");
        System.out.print("They are ");
        for (String name: boys) {
            System.out.print(name + " ");
        }
        System.out.println("\n");

        System.out.println("SORTING PROCESS");

        System.out.println("Original HashSet for boys  : " + boysCopy);
        System.out.println("Sorted TreeSet for boys    : " + treeSetBoys);
        System.out.println();
        System.out.println("Original HashSet for girls : " + girls);
        System.out.println("Sorted TreeSet for girls   : " + treeSetGirls);

        long endTime = System.nanoTime();
        System.out.println("\nWith thread, processing time -> "+(endTime - startTime) + " ns");
    }
}
