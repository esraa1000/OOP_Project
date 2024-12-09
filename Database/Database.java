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
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    static {
        try {
            loadCustomersFromFile("customers.txt");
            loadProductsFromFile("products.txt");
            loadOrdersFromFile("orders.txt");
            loadCategoriesFromFile("categories.txt");
            loadCartsFromFile("carts.txt");
        } catch (IOException e) {
            System.out.println("Couldn't load files: " + e.getMessage());
        }
    }

    // ==================== Reading Methods ====================
    public static void loadCustomersFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0];
                    String password = parts[1];
                    Date dateOfBirth = sdf.parse(parts[2]);
                    String address = parts[3];
                    customers.add(new Customer(username, password, dateOfBirth, address));
                }
            }
        } catch (java.text.ParseException e) {
            System.out.println("Error parsing customer date: " + e.getMessage());
        }
    }

    public static void loadAdminsFromFile(String filePath) throws IOException {
        BufferedReader reader = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // username, password, dateOfBirth, role, workingHours
                    String username = parts[0];
                    String password = parts[1];
                    Date dateOfBirth = sdf.parse(parts[2]);
                    String role = parts[3];
                    double workingHours = Double.parseDouble(parts[4]);
                    admins.add(new Admin(username, password, dateOfBirth, role, workingHours));
                }
            }
        } catch (IOException | java.text.ParseException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) reader.close();
        }
    }



    public static void loadProductsFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int categoryId = Integer.parseInt(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    products.add(new Product(name, price, null, quantity));
                }
            }
        }
    }

    public static void loadOrdersFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String paymentMethod = parts[0];
                    double discount = Double.parseDouble(parts[1]);
                    double tax = Double.parseDouble(parts[2]);
                    double shippingFee = Double.parseDouble(parts[3]);
                    int userId = Integer.parseInt(parts[4]);
                    orders.add(new Order(paymentMethod, discount, tax, shippingFee, userId));
                }
            }
        }
    }

    public static void loadCategoriesFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    String name = parts[0];
                    List<Product> productsInCategory = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        int productId = Integer.parseInt(parts[i]);
                        productsInCategory.add(findProductById(productId));
                    }
                    categories.add(new Category(name, productsInCategory));
                }
            }
        }
    }

    public static void loadCartsFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    int userId = Integer.parseInt(parts[0]);
                    List<Product> addedProducts = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        int productId = Integer.parseInt(parts[i]);
                        addedProducts.add(findProductById(productId));
                    }
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    cart.setAddedProducts(addedProducts);
                    carts.add(cart);
                }
            }
        }
    }

    // ==================== Writing Methods ====================
    public static void saveCustomerToFile(Customer customer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true)); // Append mode
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        writer.write(customer.getUsername() + "," +
                customer.getPassword() + "," +
                sdf.format(customer.getDateOfBirth()) + "," +
                customer.getAddress() + "\n");

        writer.close();
    }



    public static void saveAdminToFile(Admin admin) throws IOException {
        if (admin.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Admin dateOfBirth must not be null");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("admins.txt"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        writer.write(admin.getUsername() + "," +
                admin.getPassword() + "," +
                sdf.format(admin.getDateOfBirth()) + "," +
                admin.getRole() + "," +
                admin.getWorkingHours() + "\n");

        writer.close();
    }

    public static void saveProductToFile(Product product) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt", true)); // Append mode

        writer.write(product.getName() + "," +
                product.getPrice() + "," +
                product.getCategory().getId() + "," +
                product.getQuantity() + "\n");

        writer.close();
    }


    public static void saveOrderToFile(Order order) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true)); // Append mode

        writer.write(order.getPaymentMethod() + "," +
                order.getDiscount() + "," +
                order.getTax() + "," +
                order.getShippingFee() + "," +
                order.getCheckOutTotal() + "," +
                order.getUserId() + "\n");

        writer.close();
    }


    public static void saveCategoryToFile(Category category) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("categories.txt", true)); // Append mode

        String productsInCategory = category.getProductsInCategory().isEmpty() ? "" :
                category.getProductsInCategory().stream()
                        .map(product -> String.valueOf(product.getId()))
                        .reduce((a, b) -> a + "," + b)
                        .get();

        writer.write(category.getName() + "," +
                category.getId() + "," +
                productsInCategory + "\n");

        writer.close();
    }


    public static void saveCartToFile(Cart cart) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("carts.txt", true)); // Append mode

        String addedProducts = cart.getAddedProducts().isEmpty() ? "" :
                cart.getAddedProducts().stream()
                        .map(product -> String.valueOf(product.getId()))
                        .reduce((a, b) -> a + "," + b)
                        .get();

        writer.write(cart.getUserId() + "," +
                cart.getCartId() + "," +
                addedProducts + "\n");

        writer.close();
    }


    // ==================== Helper Methods ====================
    private static Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

}