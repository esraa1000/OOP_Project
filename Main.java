import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import DAO.*;
import Entity.*;

public class Main {
    public static void main(String[] args) {
        // Initializing DAO instances
        AdminDAO adminDAO = new AdminDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        OrderDAO orderDAO = new OrderDAO();
        CartDAO cartDAO = new CartDAO();

        // Date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Parsing the date safely
        Date dateOfBirth = null;
        try {
            dateOfBirth = sdf.parse("1990-01-01");
            System.out.println("Parsed dateOfBirth: " + dateOfBirth);
        } catch (ParseException e) {
            System.err.println("Error parsing date. Please ensure the format is yyyy-MM-dd.");
            e.printStackTrace();
            return; // Exit program if date parsing fails
        }

        try {
            // Adding Admin
            Admin admin1 = new Admin("admin1", "admin123", dateOfBirth, "Manager", 40);
            System.out.println("Created Admin: " + admin1.getUsername() + ", dateOfBirth: " + admin1.getDateOfBirth());
            adminDAO.add(admin1);
            System.out.println("Admin added successfully: " + admin1.getUsername());

            // Adding Customer
            Customer customer1 = new Customer("john_doe", "password123", dateOfBirth, "123 Main St");
            System.out.println("Created Customer: " + customer1.getUsername() + ", dateOfBirth: " + customer1.getDateOfBirth());
            customerDAO.add(customer1);
            System.out.println("Customer added successfully: " + customer1.getUsername());

            // Adding Category
            Category category1 = new Category("Electronics", null);
            System.out.println("Created Category: " + category1.getName());
            categoryDAO.add(category1);
            System.out.println("Category added successfully: " + category1.getName());

            // Adding Product with the created category
            Product product1 = new Product("Laptop", 1200.99, category1, 10);
            System.out.println("Created Product: " + product1.getName() + ", Category: " + product1.getCategory().getName());
            productDAO.add(product1);
            System.out.println("Product added successfully: " + product1.getName());

            // Adding Order
            Order order1 = new Order("CreditCard", 10.0, 5.0, 15.0, 1);
            System.out.println("Created Order: ID=" + order1.getOrderId() + ", Payment Type=" + order1.getPaymentMethod());
            orderDAO.add(order1);
            System.out.println("Order added successfully: ID=" + order1.getOrderId());

            // Adding Cart
            Cart cart1 = new Cart();
            cart1.setUserId(1);
            cart1.setCartId(1);
            System.out.println("Created Cart for User ID: " + cart1.getUserId());
            cartDAO.add(cart1);
            System.out.println("Cart added successfully for User ID: " + cart1.getUserId());

            System.out.println("\nAll entities added and saved successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred during execution:");
            e.printStackTrace();
        }
    }
}
