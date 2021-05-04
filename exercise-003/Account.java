/**
 * This class is for the main class of the inheritance example
 *
 * @author Okan Atas
 * created on 2021/07/02
 */

public class Account {

    private double accountBalance;

    /**
     * This method is for the constructor of the Account class with an initial balance.
     * The initial balance value is being allocated to the private accountBalance variable.
     *
     * @param initialBalance initial balance
     */
    public Account(double initialBalance){

        if(initialBalance >= 0.0f){
            accountBalance = initialBalance;
        }
        else {
            System.err.println("Error");
        }
    }

    /**
     * This method is for to add the received credit to the current balance.
     *
     * @param amount credit amount
     */
    public void credit(double amount){

        accountBalance += amount;
    }

    /**
     * This method is for to deduct the withdrawn debit from the current balance.
     *
     * @param amount debit amount
     */
    public void debit(double amount){

        if(amount > accountBalance){
            System.out.println("Debit amount exceeded account balance.");
        } else {
            accountBalance -= amount;
        }
    }

    /**
     * This method is for to provide access by getting the private value of this class.
     *
     * @return account balance which is private value
     */
    public double getCurrentBalance() {
        return accountBalance;
    }
}
