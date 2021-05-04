/**
 * This class created for testing purpose
 * @author Okan Atas
 * created on February 14, 2021
 */
public class PayrollSystemTest {
    /**
     * Main method to test the program
     * @param args no arguments
     */
    public static void main(String[] args) {

        // create object of SalariedEmployee        (subclass object 1)
        SalariedEmployee salariedEmployee =
                new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);

        // create object of CommissionEmployee    (subclass object 2 )
        CommissionEmployee commissionEmployee =
                new CommissionEmployee("Jack", "Barney", "111-11-2222", 25000, .12);

        // create object of HourlyEmployee      (subclass object 3 )
        HourlyEmployee hourlyEmployee =
                new HourlyEmployee("Okan", "Atas", "111-11-3333", 101.99, 43);

        // create object of BasePlusCommissionEmployee      (subclass object 4 )
        BasePlusCommissionEmployee basePlusCommissionEmployee =
                new BasePlusCommissionEmployee("Simon", "Sinek", "111-11-4444", 2300, .09, 1100);

        // Title
        System.out.println("Printing All Information About Each Object Individually\n");

        System.out.printf("%s\nEarned: $%,.2f\n\n", salariedEmployee, salariedEmployee.getPaymentAmount());
        System.out.printf("%s\nEarned: $%,.2f\n\n", commissionEmployee, commissionEmployee.getPaymentAmount());
        System.out.printf("%s\nEarned: $%,.2f\n\n", hourlyEmployee, hourlyEmployee.getPaymentAmount());
        System.out.printf("%s\nEarned: $%,.2f\n\n", basePlusCommissionEmployee, basePlusCommissionEmployee.getPaymentAmount());

        // creating four object Employee array
        Employee[] employees = new Employee[4];

        // initializing each subclass to each index of the employees object
        employees[0] = salariedEmployee;
        employees[1] = commissionEmployee;
        employees[2] = hourlyEmployee;
        employees[3] = basePlusCommissionEmployee;

        // Title
        System.out.println("Printing All Information About Each Object Polymorphically\n");

        for (Employee employee : employees) {
            System.out.printf("%s\nEarned $%,.2f\n\n",employee ,employee.getPaymentAmount());
        }

        //Title
        System.out.println("EXTRA   --->  Invoice test : individually\n");
        Payable payable = new Invoice("000001", "payment desc", 11, 15.90);

        System.out.printf("%s \n%s: $%,.2f\n\n",
                payable.toString(),
                "Total payment", payable.getPaymentAmount());

        //Title
        System.out.println("EXTRA   --->  Invoice test : polymorphically\n");
        Invoice invoice = new Invoice("000001", "payment desc", 11, 15.90);

        System.out.printf("%s \n%s: $%,.2f\n\n",
                invoice.toString(),
                "Total payment", invoice.getPaymentAmount());

        // Title
        System.out.println("Find Out The Specific Class for Each Object\n");

        for (Employee employee : employees) {
            System.out.printf("Employee: %s is of class %s\n", employee.getFirst() + " " + employee.getLast(),
                    employee.getClass().getName());
        }
    }
}