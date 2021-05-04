package task2.with_thread;

/**
 * This class is created for thread behavior of CapitalCityFinder class
 * @author Okan Atas, created on March 26, 2021
 * @version 1.0.0
 */
public class CityFinderThread extends Thread{
    private final String line;

    /**
     * One Argument Constructor
     * @param line line of specified baby names ranking file
     */
    public CityFinderThread(String line){
        this.line = line;
    }

    /**
     * The run method for thread behavior
     */
    @Override
    public void run() {
        try {
            String[] splitLine = line.split("," , 2);
            CapitalCityFinder.capitalCities.put(splitLine[0], splitLine[1]);
        }catch (Exception e){
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
