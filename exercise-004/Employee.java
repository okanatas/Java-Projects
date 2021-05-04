/**
 * This class is for the main class of the inherited structure and also subclass for Payable interface
 * @author Okan Atas
 * created on February 14, 2021
 */
public abstract class Employee implements Payable {
    private final String first;    // first name of employee
    private final String last;     // last name of employee
    private final String ssn;      // SSN (social security number) of employee

    /**
     * Constructor method that initializes all initial values while creating an instance
     * @param first first name of employee
     * @param last last name of employee
     * @param ssn social security number of employee
     */
    public Employee(String first, String last, String ssn) {
        this.first = first;
        this.last = last;
        this.ssn = ssn;
    }

    /**
     * This method returns first name of employee
     * @return first name of employee
     */
    public String getFirst() {
        return first;
    }

    /**
     * This method returns last name of employee
     * @return last name of employee
     */
    public String getLast() {
        return last;
    }

    /**
     * This method returns SSN (social security number) of employee
     * @return SSN (social security number)
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * This method returns a formatted string using the given locale, specified format string and arguments
     * @return formatted string for Employee instance
     */
    @Override
    public String toString() {
        return String.format( "%s %s\nSSN: %s",
                getFirst(), getLast(), getSsn());
    }
}