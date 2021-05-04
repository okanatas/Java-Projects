/**
 * This class is for testing of the inheritance example and see polymorphic behaviors
 *
 * @author Okan Atas
 * created on 2021/07/02
 */

public class TesterOne {

    /**
     * This main method includes tests prepared for each object.
     * For the concept of inheritance and polymorphism.
     *
     * @param args was not used in this test
     */
    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("#.00");

        Account obj1 = new Account(1000);
        SavingsAccount obj2 = new SavingsAccount(2000,0.1);
        CheckingAccount obj3 = new CheckingAccount(3000,25);


        // TEST : Account Class
        System.out.println("OBJ-1\nCurrent balance: " + obj1.getCurrentBalance());
        obj1.credit(2300.45);
        System.out.println("After credit, current balance: " + obj1.getCurrentBalance());
        obj1.debit(3300.46);
        obj1.debit(1240.30);
        System.out.println("After debit, current balance: " + df.format(obj1.getCurrentBalance()));

        // TEST : SavingsAccount Class
        System.out.println("\nOBJ-2\nCurrent balance: " + obj2.getCurrentBalance());
        System.out.println("Calculated interest is: " + obj2.calculateInterest());
        obj2.credit(2300.45);
        System.out.println("After credit, current balance: " + obj2.getCurrentBalance());
        obj2.debit(4300.46);
        obj2.debit(1240.30);
        System.out.println("After debit, current balance: " + df.format(obj2.getCurrentBalance()));

        // TEST : CheckingAccount Class
        System.out.println("\nOBJ-3\nCurrent balance: " + obj3.getCurrentBalance());
        System.out.println("Initial transaction fee: " + obj3.feeChargedPerTransaction);
        obj3.credit(obj3,2300.45);
        System.out.println("After credit, current balance: " + obj3.getCurrentBalance());
        obj3.debit(obj3,7200);
        obj3.debit(obj3,5200.30);
        System.out.println("After debit, current balance: " + df.format(obj3.getCurrentBalance()));


        // EXAMPLE OF instanceof
        Account testObj = new Account(100.25);
        if (testObj instanceof CheckingAccount) {
            System.out.println("\ntestObj is instance of CheckingAccount");
        }
        else {
            System.out.println("\ntestObj is NOT instance of CheckingAccount");
        }

        // Add interest to the SavingsAccount object
        // then passing the returned interest amount to the object’s Credit method
        obj2.credit(obj2.calculateInterest());
        System.out.println("\nAfter credit method of SavingsAccount object (obj2) current balance: " + df.format(obj2.getCurrentBalance()));
    }
}
