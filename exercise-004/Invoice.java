/**
 * This class is for the subclass of the Payable interface
 * @author Okan Atas
 * created on February 14, 2021
 */
public class Invoice implements Payable{
    private final String part;            //  the part number
    private final String description;     // the part description
    private int count;              // number of item
    private double price;           // cost per item

    /**
     * Constructor method that initializes all initial values while creating an instance
     * @param part the part number
     * @param description the part description
     * @param count number of item
     * @param price cost per item
     */
    public Invoice(String part, String description, int count,
                   double price) {
        this.part = part;
        this.description = description;
        setCount(count);
        setPrice(price);
    }

    /**
     * This method returns the part number
     * @return the part number
     */
    public String getPart() {
        return part;
    }

    /**
     * This method returns the part description
     * @return the part description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns number of item
     * @return number of item
     */
    public int getCount() {
        return count;
    }

    /**
     * This method validates the number of item and stores to count variable
     * @param count number of item
     */
    public void setCount(int count) {
        if ( count >= 0 ) {
            this.count = count;
        }
        else {
            throw new IllegalArgumentException
                    ("Count should be higher than or equals to 0");
        }
    }

    /**
     * This method returns the cost per item
     * @return cost per item
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method validates the cost per item and stores to price variable
     * @param price cost per item
     */
    public void setPrice(double price) {
        if ( price >= 0.0 ) {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException
                    ("Price should be higher than or equals to 0");
        }
    }

    /**
     * This method returns a formatted string using the given locale, specified format string and arguments
     * @return formatted string for Invoice instance
     */
    @Override
    public String toString() {
        return String.format("*** Invoice ***\n%s: %s\n%s: %s\n%s: %d\n%s: $%,.2f",
                "Part", getPart(), "Description", getDescription(),
                "Count", getCount(), "Price", getPrice());
    }

    /**
     * This method returns calculated payment amount, implements Payable interface method
     * @return payment amount
     */
    @Override
    public double getPaymentAmount() {
        return getCount() * getPrice();
    }
}
