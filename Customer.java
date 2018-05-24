import java.util.Comparator;

public class Customer implements Comparable<Customer> {

     public Customer() {
         setEmailAddress("");
         setFirstName("");
         setLastName("");
     }

     public Customer(String emailAddress, String firstName, String lastName){
         setEmailAddress(emailAddress);
         setFirstName(firstName);
         setLastName(lastName);
     }

    /**
     * Holds the customer's email address
     */
    private String emailAddress;

    /**
     * Holds the first name of the customer
     */
    private String firstName;

    /**
     * Holds the last name of the customer
     */
    private String lastName;

    /**
     * Comparator using the customer's email address
     */
    public static final CustomerEmailAddressComparator CUSTOMER_EMAIL_ADDRESS_COMPARATOR = new CustomerEmailAddressComparator();

    /**
     * Comparator using the customer's email address
     */
    private static class CustomerEmailAddressComparator implements Comparator<Customer>{
        @Override
        public int compare(Customer o1, Customer o2){return o1.getEmailAddress().compareTo(o2.getEmailAddress());}
    }

    /**
     * Comparator using the customer's first name
     */
    public static final CustomerFirstNameComparator CUSTOMER_FIRST_NAME_COMPARATOR = new CustomerFirstNameComparator();

    /**
     * Comparator using the customer's first name
     */
    private static class CustomerFirstNameComparator implements Comparator<Customer>{
        @Override
        public int compare(Customer o1, Customer o2) {return o1.getFirstName().compareTo(o2.getFirstName());}
    }

    /**
     * Gets the email address of this customer
     *
     * @return this cutomer's emaial address
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Sets the email address of this customer
     *
     * @param emailAddress the desired email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the first name of this customer
     *
     * @return this customer's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of this customer
     *
     * @param firstName the desired first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of this customer
     *
     * @return this custommer's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the last name of this customer
     *
     * @param lastName the desired last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s", getEmailAddress(), getFirstName(), getLastName());
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (getEmailAddress().equals(customer.getEmailAddress())) return false;
        if (getFirstName().equals(customer.getFirstName())) return false;
        return getLastName().equals(customer.getLastName());
    }

    @Override
    public int hashCode() {
        int result = getEmailAddress().hashCode();
        result = 37 * result + getFirstName().hashCode();
        result = 37 * result + getLastName().hashCode();
        return result;
    }

    @Override
    public int compareTo(Customer o) {
        return getLastName().compareTo(o.getLastName());
    }
}
