/**
 * This class is for the subclass of the Employee class
 * @author Okan Atas
 * created on February 14, 2021
 */
public class SalariedEmployee extends Employee {
    private double weeklySalary;    // weekly salary of employee

    /**
     * Constructor method that initializes all initial values while creating an instance
     * @param first first name of employee
     * @param last last name of employee
     * @param ssn social security number of employee
     * @param salary weekly salary of employee
     */
    public SalariedEmployee(String first, String last, String ssn, double salary) {
        super(first, last, ssn);
        setWeeklySalary(salary);
    }

    /**
     * This method returns the weekly salary of employee
     * @return weekly salary of employee
     */
    public double getWeeklySalary() {
        return weeklySalary;
    }

    /**
     * This method validates and stores the weekly salary of employee
     * @param weeklySalary weekly salary of employee
     */
    public void setWeeklySalary(double weeklySalary) {
        if ( weeklySalary >= 0.0 ) {
            this.weeklySalary = weeklySalary;
        }
        else {
            throw new IllegalArgumentException
                    ("Weekly salary should be higher than or equal to 0.0");
        }
    }

    /**
     * This method returns calculated payment amount, implements Payable interface method
     * @return payment amount
     */
    @Override
    public double getPaymentAmount() {
        return getWeeklySalary();
    }

    /**
     * This method returns a formatted string using the given locale, specified format string and arguments
     * @return formatted string for SalariedEmployee instance
     */
    @Override
    public String toString() {
        return String.format("*** Salaried Employee ***\n%s: %s\n%s: $%,.2f",
                "Full name", super.toString(), "Weekly salary", getWeeklySalary());
    }
}
