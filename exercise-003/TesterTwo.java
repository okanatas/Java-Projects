/**
 * This class is for testing of the inheritance example and see polymorphic behaviors
 *
 * @author Okan Atas
 * created on 2021/07/02
 */

public class TesterTwo {

    /**
     * This main method includes tests prepared for each object.
     * For the concept of inheritance and polymorphism.
     *
     * The program expects the user to enter some values.
     *
     * @param args was not used in this test
     */
    public static void main(String[] args) {

        Account[] arrAccount = new Account[2];

        Scanner input = new Scanner(System.in);

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("Type 1 for Checking Account, type 2 for Saving Account");
        System.out.print("Determine the account type: ");
        int type = isInSpecifiedRange(input);

        System.out.print("Enter the balance: ");
        double userInput = ifPositive(input);

        if(type == 1)
        {
            System.out.print("Enter the transaction fee: ");
            double transactionFee = ifPositive(input);

            arrAccount[0] = new CheckingAccount(userInput, transactionFee);

            System.out.println("\nCurrent balance: " + arrAccount[0].getCurrentBalance());
            System.out.println("Transaction fee: " + ((CheckingAccount)arrAccount[0]).feeChargedPerTransaction);
            System.out.print("\nEnter credit: ");
            userInput = input.nextDouble();

            ((CheckingAccount)arrAccount[0]).credit((CheckingAccount)arrAccount[0],userInput);
            System.out.println("After credit, current balance: " + df.format(arrAccount[0].getCurrentBalance()));

            System.out.print("\nEnter debit: ");
            userInput = input.nextDouble();

            ((CheckingAccount)arrAccount[0]).debit((CheckingAccount)arrAccount[0],userInput);
            System.out.println("After debit, current balance: " + df.format(arrAccount[0].getCurrentBalance()));
        }
        else
        {
            System.out.print("Enter the interest rate in percentage: ");
            double interestRate = ifPositive(input);

            arrAccount[1] = new SavingsAccount(userInput, interestRate / 100);

            System.out.println("\nCurrent balance: " + arrAccount[1].getCurrentBalance());

            double calcInterest = ((SavingsAccount)arrAccount[1]).calculateInterest();

            System.out.println("Interest calculated at a rate of %" + interestRate + ": " + df.format(calcInterest));
            arrAccount[1].credit(calcInterest);
            System.out.println("After interest, current balance: " + df.format(arrAccount[1].getCurrentBalance()));

            System.out.print("\nEnter credit: ");
            userInput = input.nextDouble();

            arrAccount[1].credit(userInput);
            System.out.println("After credit, current balance: " + df.format(arrAccount[1].getCurrentBalance()));

            System.out.print("\nEnter debit: ");
            userInput = input.nextDouble();

            arrAccount[1].debit(userInput);
            System.out.println("After debit, current balance: " + df.format(arrAccount[1].getCurrentBalance()));

        }

        input.close();
    }

    /**
     * This method ensures that the correct value which is integer is taken during
     * the Scanner process.
     *
     * @param sc Scanner object
     * @return approved integer value
     */
    public static int getAnInteger(Scanner sc)
    {
        while (true)
        {
            try
            {
                return sc.nextInt();
            }
            catch (InputMismatchException e)
            {
                sc.next();
                System.out.print("Please enter an integer value: ");
            }
        }
    }

    /**
     * This function ensures that the value 1 or 2 required for determining
     * the Account type is correctly entered by the user.
     *
     * @param sc Scanner object
     * @return approved integer value
     */
    public static int isInSpecifiedRange(Scanner sc){
        int userInput;
        boolean correct = false;
        do{
            userInput = getAnInteger(sc);
            if(userInput == 1 || userInput == 2)
            {
                correct = true;
            }
            else {
                System.out.print("Only 1 or 2 acceptable, please enter again: ");
            }
        }while (!correct);
        return userInput;
    }

    /**
     * This function ensures that the entered value is positive.
     *
     * @param sc Scanner object
     * @return approved double value
     */
    public static double ifPositive(Scanner sc){
        double userInput;
        do {
            userInput = sc.nextDouble();
            if (userInput <= 0) {
                System.out.print("Please enter a positive value: ");
            }
        }while (userInput <= 0);
        return userInput;
    }
}
