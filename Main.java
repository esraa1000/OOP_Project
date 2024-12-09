import Entity.Customer;

public class Main {
    public static void main(String[] args) {
        // Path to the customers.txt file (make sure this file is in your project directory)
        String filePath = "customers.txt";

        // Load customer data from the file


        // Print all customers that were loaded into the static list
        for (Customer customer : Database.Database.customers) {
            System.out.println("Username: " + customer.getUsername());
            System.out.println("Password: " + customer.getPassword());
            System.out.println("Date of Birth: " + customer.getDateOfBirth());
            System.out.println("Address: " + customer.getAddress());
            System.out.println();
        }
    }
}
