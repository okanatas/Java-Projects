/**
 * This class is for the sub class of Account class of the inheritance example
 *
 * @author Okan Atas
 * created on 2021/07/02
 */

public class SavingsAccount extends Account{

    double interestRate;

    /**
     * This method is for the constructor of the SavingsAccount class with an initial interest rate and balance.
     * The initial interest rate value is being allocated to the interestRate variable.
     *
     * @param balance current balance of account
     * @param initialInterestRate initial interest rate
     */
    public SavingsAccount(double balance,double initialInterestRate){

        super(balance);
        this.interestRate = initialInterestRate;
    }

    /**
     * This method allows to calculate how much interest will be calculated
     * using the current balance and interest rate.
     *
     * @return calculated interest
     */
    public double calculateInterest(){

        double currentBalance = getCurrentBalance();

        return currentBalance * interestRate;
    }
}
