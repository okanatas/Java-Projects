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
 * This class is the class of threads representing each philosophers.
 * @author Okan Atas, created on April 3, 2021
 * @version 1.0.0
 */
public class Philosophers {
    private static final Object chopstick1 = new Object();
    private static final Object chopstick2 = new Object();
    private static final Object chopstick3 = new Object();
    private static final Object chopstick4 = new Object();
    private static final Object chopstick5 = new Object();


    /**
     * The thread that representing philosopher 1.
     */
    protected static class Philosopher1 extends Thread {
        /** Actual override thread method of run() */
        @Override
        public void run() {
            synchronized (chopstick1) {
                System.out.println("Philosopher 1 is holding chopstick 1...");
                sleepABit(Thread.currentThread());

                System.out.println("Philosopher 1 is waiting for chopstick 5...");
                sleepABit(Thread.currentThread());

                if(ChopstickDeadLock.willTheWaiterBeHere){
                    System.out.println("\nThe waiter comes and handles the chopstick 5 to Philosopher 1 to dissolve deadlock...");
                    sleepABit(Thread.currentThread());
                }

                synchronized (chopstick5) {
                    System.out.println("\nPhilosopher 1 is holding chopstick 1 and 5 together,");
                    sleepABit(Thread.currentThread());
                    System.out.println("and eats the noodle...");
                    sleepABit(Thread.currentThread());
                    System.out.println("\n\033[3m     mmm yummy!...Let food be thy medicine and medicine be thy food.\033[0m\n");
                    sleepABit(Thread.currentThread());
                }
            }
        }
    }

    /**
     * The thread that representing philosopher 2.
     */
    protected static class Philosopher2 extends Thread {
        /** Actual override thread method of run() */
        @Override
        public void run() {
            synchronized (chopstick2) {
                if(ChopstickDeadLock.philosopher1.isAlive()){
                    System.out.println("Philosopher 2 is holding chopstick 2...");
                    sleepABit(Thread.currentThread());

                    System.out.println("Philosopher 2 is waiting for chopstick 1...");
                }else{
                    System.out.println("Philosopher 1 is released the chopsticks.");
                }
                sleepABit(Thread.currentThread());

                synchronized (chopstick1) {
                    System.out.println("Philosopher 2 is holding chopstick 2 and 1 together,");
                    sleepABit(Thread.currentThread());
                    System.out.println("and eats the noodle...");
                    sleepABit(Thread.currentThread());
                    System.out.println("\n\033[3m     mmm yummy!...There is no love sincerer than the love of food.\033[0m\n");
                    sleepABit(Thread.currentThread());
                }
            }
        }
    }

    /**
     * The thread that representing philosopher 3.
     */
    protected static class Philosopher3 extends Thread {
        /** Actual override thread method of run() */
        @Override
        public void run() {
            synchronized (chopstick3) {
                if(ChopstickDeadLock.philosopher2.isAlive()){
                    System.out.println("Philosopher 3 is holding chopstick 3...");
                    sleepABit(Thread.currentThread());

                    System.out.println("Philosopher 3 is waiting for chopstick 2...");
                }else{
                    System.out.println("Philosopher 2 is released the chopsticks.");
                }
                sleepABit(Thread.currentThread());

                synchronized (chopstick2) {
                    System.out.println("Philosopher 3 is holding chopstick 3 and 2 together,");
                    sleepABit(Thread.currentThread());
                    System.out.println("and eats the noodle...");
                    sleepABit(Thread.currentThread());
                    System.out.println("\n\033[3m     mmm yummy!...He who does not mind his belly, will hardly mind anything else.\033[0m\n");
                    sleepABit(Thread.currentThread());
                }
            }
        }
    }

    /**
     * The thread that representing philosopher 4.
     */
    protected static class Philosopher4 extends Thread {
        /** Actual override thread method of run() */
        @Override
        public void run() {
            synchronized (chopstick4) {
                if(ChopstickDeadLock.philosopher3.isAlive()){
                    System.out.println("Philosopher 4 is holding chopstick 4...");
                    sleepABit(Thread.currentThread());

                    System.out.println("Philosopher 4 is waiting for chopstick 3...");
                }else{
                    System.out.println("Philosopher 3 is released the chopsticks.");
                }
                sleepABit(Thread.currentThread());

                synchronized (chopstick3) {
                    System.out.println("Philosopher 4 is holding chopstick 4 and 3 together,");
                    sleepABit(Thread.currentThread());
                    System.out.println("and eats the noodle...");
                    sleepABit(Thread.currentThread());
                    System.out.println("\n\033[3m     mmm yummy!...Eat to live, not live to eat.\033[0m\n");
                    sleepABit(Thread.currentThread());
                }
            }
        }
    }

    /**
     * The thread that representing philosopher 5.
     */
    protected static class Philosopher5 extends Thread {
        /** Actual override thread method of run() */
        @Override
        public void run() {
            synchronized (chopstick5) {
                if(ChopstickDeadLock.philosopher4.isAlive()){
                    System.out.println("Philosopher 5 is holding chopstick 5...");
                    sleepABit(Thread.currentThread());

                    System.out.println("Philosopher 5 is waiting for chopstick 4...");
                }else{
                    System.out.println("Philosopher 4 is released the chopsticks.");
                }
                sleepABit(Thread.currentThread());

                synchronized (chopstick4) {
                    System.out.println("Philosopher 5 is holding chopstick 5 and 4 together,");
                    sleepABit(Thread.currentThread());
                    System.out.println("and eats the noodle...");
                    sleepABit(Thread.currentThread());
                    System.out.println("\n\033[3m     mmm yummy!...One cannot think well, love well, sleep well, if one has not dined well.\033[0m\n");
                    sleepABit(Thread.currentThread());
                    System.out.println("Philosopher 5 is released the chopsticks.");
                }
            }
        }
    }

    /**
     * This method pauses threads for a while.
     * @param thread thread
     */
    protected static void sleepABit(Thread thread){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
