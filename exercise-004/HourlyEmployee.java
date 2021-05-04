/**
 * This class is for the subclass of the Employee class
 * @author Okan Atas
 * created on February 14, 2021
 */
public class HourlyEmployee extends Employee {
    private double wage;     // hourly wage of employee
    private double hours;    // number of hours worked by employee

    /**
     * Constructor method that initializes all initial values while creating an instance
     * @param first first name of employee
     * @param last last name of employee
     * @param ssn social security number of employee
     * @param hourlyWage hourly wage of employee
     * @param hoursWorked number of hours worked by employee
     */
    public HourlyEmployee(String first, String last, String ssn,
                          double hourlyWage, double hoursWorked) {
        super(first, last, ssn);
        setWage(hourlyWage);
        setHours(hoursWorked);
    }

    /**
     * This method returns hourly wage of employee
     * @return hourly wage of employee
     */
    public double getWage() {
        return wage;
    }

    /**
     * This method validates the hourly wage of employee and stores to wage variable
     * @param wage hourly wage of employee
     */
    public void setWage(double wage) {
        if (wage >= 0.0) {
            this.wage = wage;
        } else {
            throw new IllegalArgumentException
                    ("Hourly wage should be higher than or equal to 0.0");
        }
    }

    /**
     * This method returns number of hours worked by employee
     * @return number of hours worked by employee
     */
    public double getHours() {
        return hours;
    }

    /**
     * This method validates the number of hours worked by employee and stores to hours variable
     * @param hours number of hours worked by employee
     */
    public void setHours(double hours) {
        if ((hours >= 0.0) && (hours <= 168.0)) {
            this.hours = hours;
        } else {
            throw new IllegalArgumentException
                    ("Number of hours worked should be higher than or equal to 0.0 " +
                            "and lower than or equal to 168.0");
        }
    }

    /**
     * This method returns calculated payment amount, implements Payable interface method
     * @return payment amount
     */
    @Override
    public double getPaymentAmount() {
        // if employee has no overtime
        if (getHours() <= 40)
            return getWage() * getHours();
        else
            return 40 * getWage() + (getHours() - 40) * getWage() * 1.5;
    }

    /**
     * This method returns a formatted string using the given locale, specified format string and arguments
     * @return formatted string for HourlyEmployee instance
     */
    @Override
    public String toString() {
        return String.format("*** Hourly Employee ***\n%s: %s\n%s: $%,.2f\n%s: %,.2f",
                "Full name", super.toString(), "Hourly wage", getWage(),
                "Hours worked", getHours());
    }
}
