package Database;

import Entity.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
    public static List<Customer> customers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static List<Cart> carts = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();

    static {

        try{
            loadCustomersFromFile("customers.txt");
        }catch(IOException e ){
            System.out.println("Couldn't load file");
        }

    }



    public static void loadCustomersFromFile(String filePath) throws IOException{
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Read each line of the file and create a Customer object
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0];
                    String password = parts[1];
                    Date dateOfBirth = sdf.parse(parts[2]);
                    String address = parts[3];

                    // Create Customer and add to the static list
                    Customer customer = new Customer(username, password, dateOfBirth, address);
                    customers.add(customer);
                }
            }
        } catch (IOException | java.text.ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {

                System.out.println("File not found!");

            }
        }

    }
    public static void appendCustomerToFile(Customer customer) throws IOException {
        // Prepare the string to append to the file
        String customerData = customer.getUsername() + "," +
                customer.getPassword() + "," +
                customer.getDateOfBirth() + "," +
                customer.getAddress() + "\n";

        // Use BufferedWriter to append the data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true))) {
            writer.write(customerData);
        }
    }
}
