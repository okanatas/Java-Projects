package task1.with_thread;

/**
 * This class is created for thread behavior of BabyNamesAnalysis class
 * @author Okan Atas, created on March 26, 2021
 * @version 1.0.0
 */
public class NamesSetterThread extends Thread {
    private final String line;

    /**
     * One Argument Constructor
     * @param line line of specified baby names ranking file
     */
    public NamesSetterThread(String line) {
        this.line = line;
    }

    /**
     * The run method for thread behavior
     */
    @Override
    public void run() {
        try {

            String[] tokens = (line.trim().replaceAll("\\s+"," ")).split(" ");

            BabyNamesAnalysis.boys.add(tokens[1]);
            BabyNamesAnalysis.girls.add(tokens[3]);


        }catch (Exception e){
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
