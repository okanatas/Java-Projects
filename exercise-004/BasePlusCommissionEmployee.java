/**
 * This class is for the subclass of the CommissionEmployee class
 * @author Okan Atas
 * created on February 14, 2021
 */
public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;    // base salary of employee

    /**
     * Constructor method that initializes all initial values while creating an instance
     *
     * @param first first name of employee
     * @param last last name of employee
     * @param ssn social security number of employee
     * @param sales gross sales of employee
     * @param rate commission rate of employee
     * @param baseSalary base salary of employee
     */
    public BasePlusCommissionEmployee(String first, String last,
                                      String ssn, double sales, double rate, double baseSalary) {
        super(first, last, ssn, sales, rate);
        setBaseSalary(baseSalary);
    }

    /**
     * This method returns baseSalary which is instance variable
     * @return base salary of employee
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * This method validates the base salary of employee and stores to baseSalary variable
     * @param baseSalary base salary of employee
     */
    public void setBaseSalary(double baseSalary) {
        if (baseSalary >= 0.0)
            this.baseSalary = baseSalary;
        else
            throw new IllegalArgumentException(
                    "Base salary should be higher than or equal to 0.0" );
    }

    /**
     * This method returns calculated payment amount, implements Payable interface method
     * @return payment amount
     */
    @Override
    public double getPaymentAmount() {
        return getBaseSalary() + super.getPaymentAmount();
    }

    /**
     * This method returns a formatted string using the given locale, specified format string and arguments
     * @return formatted string for BasePlusCommissionEmployee instance
     */
    @Override
    public String toString() {
        return String.format("*** Base Plus Commission Employee ***\n%s\n%s: $%,.2f",
                super.toString(),
                "Base salary", getBaseSalary());

    }
}