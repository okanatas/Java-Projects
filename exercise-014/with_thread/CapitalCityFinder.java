package task2.with_thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is created for a program that shows the capital of the country entered by the user
 * @author Okan Atas, created on March 26, 2021
 * @version 1.0.0
 */
public class CapitalCityFinder {
    /** HashMap to store Countries and their Capitals */
    public static Map<String, String> capitalCities = Collections.synchronizedMap(new HashMap<>());

    /**
     * This method is the main method of Capital City Finder program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String respond;
        boolean searchAgain = true;
        long startTime = 0;

        // Create a Scanner
        Scanner input = new Scanner(System.in);

        try {
            startTime = System.nanoTime();
            File fileObj = new File("src/files/countries.txt");

            Scanner scanner = new Scanner(fileObj);
            while (scanner.hasNextLine()) {
                CityFinderThread thread = new CityFinderThread(scanner.nextLine());
                thread.start();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Please make sure the file is placed correctly!");
            e.printStackTrace();
            System.exit(1);
        }
        long endTime = System.nanoTime();

        while (searchAgain){
            // Prompt the user to enter the country name
            System.out.print("Enter the Country: ");
            String countryName = correctingEnteredCountry(input.nextLine());

            if(capitalCities.get(countryName) != null){
                System.out.println("The Capital city of "
                        + countryName
                        + " is "
                        + capitalCities.get(countryName));
            } else{
                System.out.println("The Country of " + countryName + " not found");
            }

            do{
                System.out.print("Would you like to make a new search? (Y/N) : ");
                respond = (input.nextLine()).toUpperCase();
            }while(!validateYesNo(respond));

            if(respond.equals("N")){
                searchAgain = false;
            }
        }

        System.out.println("\nWith thread, processing time -> " + (endTime - startTime) + " ns");
    }

    /**
     * This method converts the country name entered by the user into a correct spelling format
     * @param enteredCountry user entered country
     * @return country name in correct spelling format
     */
    private static String correctingEnteredCountry(String enteredCountry){
        int spaceCounter = 0;
        String fixedCountry;

        enteredCountry = enteredCountry.toUpperCase().trim().replaceAll("\\s+"," ");

        for(char ch : enteredCountry.toCharArray()){
            if(ch == ' '){
                spaceCounter++;
            }
        }

        if(spaceCounter > 0){
            String[] split = enteredCountry.split(" ", spaceCounter + 1);
            String[] fixedSplit = new String[spaceCounter + 1];

            for(int i = 0; i < spaceCounter + 1; i++){
                if(split[i].equals("OF") || split[i].equals("THE") || split[i].equals("AND")){
                    fixedSplit[i] = split[i].toLowerCase() + " ";
                }else{
                    fixedSplit[i] = split[i].charAt(0) + split[i].substring(1).toLowerCase() + " ";
                }
            }
            fixedCountry = String.join("", fixedSplit);
            fixedCountry = fixedCountry.trim();
        }else{
            fixedCountry = enteredCountry.charAt(0) + enteredCountry.substring(1).toLowerCase();
        }
        return fixedCountry;
    }

    /**
     * This method confirms if the user wants to search any other country name
     * @param response user response on searching a new country
     * @return boolean depends on user response
     */
    private static boolean validateYesNo(String response) {
        if(response.equals("Y") || response.equals("N") || response.equals("YES") || response.equals("NO") )
        {
            return true;
        } else {
            System.out.println("Please enter Y or N");
            return false;
        }
    }
}
