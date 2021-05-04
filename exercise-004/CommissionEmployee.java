/**
 * This class is for the subclass of the Employee class
 * @author Okan Atas
 * created on February 14, 2021
 */
public class CommissionEmployee extends Employee {
    private double grossSales;         // gross sales of employee
    private double commissionRate;     // commission rate of employee

    /**
     * Constructor method that initializes all initial values while creating an instance
     * @param first first name of employee
     * @param last last name of employee
     * @param ssn social security number of employee
     * @param sales gross sales of employee
     * @param rate commission rate of employee
     */
    public CommissionEmployee(String first, String last, String ssn,
                              double sales, double rate) {
        super(first, last, ssn);
        setGrossSales(sales);
        setCommissionRate(rate);
    }

    /**
     * This method returns gross sales amount
     * @return gross sales of employee
     */
    public double getGrossSales() { return grossSales; }

    /**
     * This method validates the gross sales of employee and stores to grossSales variable
     * @param grossSales gross sales of employee
     */
    public void setGrossSales(double grossSales) {
        if (grossSales >= 0.0) {
            this.grossSales = grossSales;
        }
        else {
            throw new IllegalArgumentException
                    ("Gross sales should be higher than 0.0");
        }
    }

    /**
     * This method returns commission rate of employee
     * @return commission rate of employee
     */
    public double getCommissionRate() {
        return commissionRate;
    }

    /**
     * This method validates the commission rate of employee and stores to commissionRate variable
     * @param commissionRate commission rate of employee
     */
    public void setCommissionRate(double commissionRate) {
        if (commissionRate > 0.0 && commissionRate < 1.0){
            this.commissionRate = commissionRate;
        }
        else {
            throw new IllegalArgumentException
                    ("Commission rate should be higher than 0.0 " +
                            "and lower than 1.0" );
        }
    }

    /**
     * This method returns calculated payment amount, implements Payable interface method
     * @return payment amount
     */
    @Override
    public double getPaymentAmount() {
        return getCommissionRate() * getGrossSales();
    }

    /**
     * This method returns a formatted string using the given locale, specified format string and arguments
     * @return formatted string for CommissionEmployee instance
     */
    @Override
    public String toString() {
        return String.format("*** Commission Employee ***\n%s: %s\n%s: $%,.2f\n%s: %.2f",
                "Full name", super.toString(),
                "Gross sales", getGrossSales(),
                "Commission rate", getCommissionRate());
    }
}