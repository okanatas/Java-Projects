/**
 * This class is for the sub class of Account class of the inheritance example
 *
 * @author Okan Atas
 * created on 2021/07/02
 */

public class CheckingAccount extends Account {

    double feeChargedPerTransaction;

    /**
     * This method is for the constructor of the CheckingAccount class with an initial transaction fee and balance.
     * The initial transaction fee value is being allocated to the feeChargedPerTransaction variable.
     *
     * @param balance current balance of account
     * @param initialTransactionFee initial transaction fee
     */
    public CheckingAccount(double balance, double initialTransactionFee){
        super(balance);
        this.feeChargedPerTransaction = initialTransactionFee;
    }

    /**
     * This method is for to add the received credit to the current balance
     * by calling the main class credit method.
     *
     * @param obj passed object
     * @param amount credit amount
     */
    public void credit(CheckingAccount obj, double amount){

        obj.credit(amount - feeChargedPerTransaction);
    }

    /**
     * This method is for to deduct the withdrawn debit from the current balance
     * by calling the main class debit method.
     *
     * @param obj passed object
     * @param amount debit amount
     */
    public void debit(CheckingAccount obj, double amount){

        obj.debit(amount + feeChargedPerTransaction);
    }
}
