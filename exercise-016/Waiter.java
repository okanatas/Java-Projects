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

/**
 * This class determines the behavior of the waiter who solves the deadlock.
 * @author Okan Atas, created on April 3, 2021
 * @version 1.0.0
 */
public class Waiter {

    /**
     * This method calls the waiter to solve the deadlock.
     */
    public static void isOnDuty(){
        System.out.println(); // separator

        ChopstickDeadLock.philosopher1.start();
        try {
            ChopstickDeadLock.philosopher1.join();
        } catch (Exception e) {
            System.out.println("Exception has been caught" + e);
        }

        ChopstickDeadLock.philosopher2.start();
        try {
            ChopstickDeadLock.philosopher2.join();
        } catch (Exception e) {
            System.out.println("Exception has been caught" + e);
        }

        ChopstickDeadLock.philosopher3.start();
        try {
            ChopstickDeadLock.philosopher3.join();
        } catch (Exception e) {
            System.out.println("Exception has been caught" + e);
        }

        ChopstickDeadLock.philosopher4.start();
        try {
            ChopstickDeadLock.philosopher4.join();
        } catch (Exception e) {
            System.out.println("Exception has been caught" + e);
        }

        ChopstickDeadLock.philosopher5.start();
    }
}
