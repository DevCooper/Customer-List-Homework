import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CustomerDAO {

    public CustomerDAO(){

    }

    /**
     * Holds the file name where the Customer data is stored
     */
    private static String fileName = "Customers.txt";

    /**
     * Writes a list of customers to the specified data file. Throws an exception if the list is empty.
     *
     * @param customerList the list of customers to be written to the file
     *
     * @return returns true if the file is successfully written
     */
    public static boolean write (List<Customer> customerList){

        if (customerList.isEmpty()) {
            throw new IllegalArgumentException("No customers.");
        }

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
            out.print(toFileString(customerList));
            return true;
        } catch (IOException iOEx){
            return false;
        }
    }

    /**
     * Reads data from the specified data file and creates a new list of Customers.
     *
     * @return the list of Customers read from the file
     */
    public static List<Customer> read() {

        List<Customer> customerList = new ArrayList<>();

        try (Scanner in = new Scanner(new BufferedReader((new FileReader(fileName))))){
            while(in.hasNext()) {
                String emailAddress = in.nextLine();
                String firstName = in.nextLine();
                String lastName = in.nextLine();
                Customer tempCustomer = new Customer(emailAddress, firstName, lastName);
                customerList.add(tempCustomer);

            }
        } catch (IOException iOEx){
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
                out.print(toFileString(customerList));
            } catch (IOException iOExTwo){
                System.out.printf("Unable to create new file.");
            }
        }

        return customerList;
    }

    /**
     * Formats each Customer object into a string
     *
     * @param customerList the list of Customers
     *
     * @return the string that contains the fields of the Customer object
     */
    public static String toFileString(List<Customer> customerList){
        StringBuilder builder = new StringBuilder();
        for (Customer customer : customerList){
            builder.append(customer.getEmailAddress()).append('\n');
            builder.append(customer.getFirstName()).append('\n');
            builder.append(customer.getLastName()).append('\n');
        }
        return builder.toString();
    }

}
