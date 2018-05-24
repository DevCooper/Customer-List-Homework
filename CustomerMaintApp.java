import java.util.*;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

public class CustomerMaintApp implements CustomerConstants {

    /**
     * Holds the current list of customers
     */
    private List<Customer> customerList;

    public CustomerMaintApp(){
        customerList = new ArrayList<Customer>();
        fillCustomerList();
        displayWelcomeMessage();
        displayMenu();
        userSelection();
    }

    /**
     * Displays a welcome message on the console
     */
    public static void displayWelcomeMessage(){
        System.out.println("Welcome to Customer Maintenance 9000!");
    }

    /**
     * Displays the menu options on the console
     */
    public void displayMenu(){
        System.out.println("MENU");
        System.out.println("list \t -List all customers");
        System.out.println("add \t -Add a customer");
        System.out.println("del \t -Delete a customer");
        System.out.println("upd \t -Update a customer");
        System.out.println("help \t -Show this menu");
        System.out.println("exit \t -Exit this application");
    }

    /**
     * Prompts the user for a command and only accepts list, add, del, upd, help, and exit as commands.  All other
     * commands display invalid command and prompt the user for another command. Will continue to loop until user
     * enters exit command. Each command calls the appropriate function to complete the command. Changes to the customerList
     *  are not saved until the exit command is entered.
     */
    public void userSelection(){
        boolean isAgain = true;

        while(isAgain) {
            System.out.printf("\nEnter a command : ");
            String userCommand = Validator.stringValidator();
            if (userCommand.contentEquals("list")) {
                listCustomers();
            } else if (userCommand.contentEquals("add")) {
                addCustomer();
            } else if (userCommand.contentEquals("del")) {
                deleteCustomer();
            } else if (userCommand.contentEquals("upd")) {
                updateCustomer();
            } else if (userCommand.contentEquals("help")) {
                displayMenu();
            } else if (userCommand.contentEquals("exit")) {
                CustomerDAO.write(customerList);
                isAgain = false;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
    }

    /**
     * Lists all customers currently contained in customerList.  User is prompted to choose how the list is ordered.
     * 1 is entered to order customers by email address, 2 for first name, and 3 for last name.  Only numbers between
     * 1 and 3 are accepted and any other number is not accepted and prompts the user for a valid number. Email addresses
     * will display the first 30 characters. Padding is added to addresses shorter than 30 characters
     * to display all addresses as 30 characters long.  First and last name fields will display the first 15 characters.
     * Names shorter than 15 are padded with spaces when displayed on the console.
     */
    public void listCustomers(){
        if(!customerList.isEmpty()) {
            System.out.printf("\nList Customers by: \n1. Email Address \n2. First Name \n3. Last Name\n");
            System.out.printf("Enter a number between 1 and 3 : ");
            int userSelection = Validator.integerValidator(1,3);
            switch (userSelection) {
                case 1:
                    Collections.sort(customerList, Customer.CUSTOMER_EMAIL_ADDRESS_COMPARATOR);
                    break;
                case 2:
                    Collections.sort(customerList, Customer.CUSTOMER_FIRST_NAME_COMPARATOR);
                    break;
                case 3:
                    Collections.sort(customerList);
                    break;
            }
            for (Customer customer : customerList){
                StringBuilder builder = new StringBuilder();
                String emailAddress = StringUtils.rightPad(customer.getEmailAddress(), maximumEmailAddressLength);
                emailAddress = emailAddress.substring(0, maximumEmailAddressLength);
                builder.append(emailAddress);
                String firstName = StringUtils.rightPad(customer.getFirstName(), maximumFirstNameLength);
                firstName = firstName.substring(0, maximumFirstNameLength);
                builder.append(firstName);
                String lastName = StringUtils.rightPad(customer.getLastName(), maximumLastNameLength);
                builder.append(lastName);
                System.out.println(builder.toString());
            }
        } else{
            System.out.println("No customers to list.");
        }
    }

    /**
     * Adds a new customer to customerList.  Prompts the user to enter to enter an email address, first name, and last
     * name for the customer. All fields require valid entry.  Empty strings will not be accepted and user will be prompted
     * until valid input is received.
     */
    public void addCustomer(){
        System.out.printf("Enter customer email address: ");
        String customerEmailAddress = Validator.stringValidator();
        System.out.printf("Enter first name : ");
        String customerFirstName = Validator.stringValidator();
        System.out.printf("Enter last name : ");
        String customerLastName = Validator.stringValidator();
        Customer tempCustomer = new Customer(customerEmailAddress, customerFirstName, customerLastName);
        customerList.add(tempCustomer);
        System.out.printf("\n%s added.\n", customerEmailAddress);

    }

    /**
     * Deletes a specified customer from customerList.  User is prompted for an email address that matches the customer
     * to be deleted.  If no matches are found then 'No matches found' is outputted to the console.  If a match is found,
     * the corresponding customer is deleted from customerList.
     */
    public void deleteCustomer(){
        System.out.printf("Enter customer email address : ");
        String customerEmail = Validator.stringValidator();
        if (customerList.isEmpty()){
            System.out.println("No customers.");
        } else {
            boolean isFound = false;
            for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext(); ) {
                Customer tempCustomer = iterator.next();
                if (tempCustomer.getEmailAddress().contentEquals(customerEmail)) {
                    isFound = true;
                    iterator.remove();
                    System.out.printf("%s deleted. \n", customerEmail);
                }
            }
            if(!isFound){
                System.out.println("No matches found.");
            }
        }
    }

    /**
     * Updates an existing customer in customerList.  User is prompted for the email address of the customer to be
     * updated.  If customerList is empty 'No customers.' is outputted to the console.  If no matches are found 'No
     * matches found.' is outputted to console.  If a match is found, the user is prompted for the updated first name
     * and last name. Both fields are required.  Empty strings are not accepted and the user will be prompted until valid
     * input is received. The old customer is deleted from customerList and a new customer with the updated information is
     * added to the list.  Upon successful update, '@emailAddress updated.' is outputted to the console.
     */
    public void updateCustomer(){
        System.out.printf("Enter customer email address to update : ");
        String customerEmail = Validator.stringValidator();
        if (customerList.isEmpty()){
            System.out.println("No customers.");
        } else {
            boolean isFound = false;
            String firstName = "";
            String lastName = "";
            for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext(); ) {
                Customer tempCustomer = iterator.next();
                if (tempCustomer.getEmailAddress().contentEquals(customerEmail)) {
                    isFound = true;
                    System.out.printf("Enter updated first name : ");
                    firstName = Validator.stringValidator();
                    System.out.printf("Enter updated last name : ");
                    lastName = Validator.stringValidator();
                    iterator.remove();
                    System.out.printf("%s updated. \n", customerEmail);
                }
            }
            Customer updatedCustomer = new Customer(customerEmail, firstName, lastName);
            customerList.add(updatedCustomer);
            if (!isFound){
                System.out.println("No matches found.");
            }
        }
    }

    /**
     * Fills customerList from the file "Customers.txt"
     */
    private void fillCustomerList(){
        customerList = CustomerDAO.read();
    }


}
