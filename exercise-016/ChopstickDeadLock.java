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

package task2;

import java.util.Scanner;

/**
 * This class is the demonstration of the Thread Deadlock problem and its solution.
 * @author Okan Atas, created on April 3, 2021
 * @version 1.0.0
 */
public class ChopstickDeadLock {
    /** The thread object that representing philosopher 1 */
    public static Philosophers.Philosopher1 philosopher1 = new Philosophers.Philosopher1();
    /** The thread object that representing philosopher 2 */
    public static Philosophers.Philosopher2 philosopher2 = new Philosophers.Philosopher2();
    /** The thread object that representing philosopher 3 */
    public static Philosophers.Philosopher3 philosopher3 = new Philosophers.Philosopher3();
    /** The thread object that representing philosopher 4 */
    public static Philosophers.Philosopher4 philosopher4 = new Philosophers.Philosopher4();
    /** The thread object that representing philosopher 5 */
    public static Philosophers.Philosopher5 philosopher5 = new Philosophers.Philosopher5();

    /** Boolean value that determines the existence of the waiter that will solve the deadlock */
    public static boolean willTheWaiterBeHere = false;


    /**
     * Main method of Deadlock example
     * N (no) : will present Deadlock
     * Y (yes) : will present solution of Deadlock
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String response = "";

        do{
            System.out.print("Will the waiter be on duty? ( Y/N ) : ");
            response = scanner.nextLine().toUpperCase();
        }while (!validateYesNo(response));

        if(response.equals("Y")){
            willTheWaiterBeHere = true;
        }

        if (willTheWaiterBeHere){
            Waiter.isOnDuty();
        }else{
            System.out.println();  // separator
            philosopher1.start();
            philosopher2.start();
            philosopher3.start();
            philosopher4.start();
            philosopher5.start();
        }
    }

    /**
     * This method validates user response.
     * @param response user answer
     * @return boolean value
     */
    private static boolean validateYesNo(String response) {
        if(response.equals("Y") || response.equals("N"))
        {
            return true;
        } else {
            System.out.println("Please enter Y or N");
            return false;
        }
    }
}