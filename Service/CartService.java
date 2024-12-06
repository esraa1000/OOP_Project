package Service;

import DAO.CartDAO;
import DAO.OrderDAO;
import Entity.Cart;
import Entity.Product;
import Entity.Customer;
import Entity.Order;
import Database.Database;

import java.util.List;

public class CartService {
    private final CartDAO cartDAO;

    //constructor
    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    //Methods
    public void addToCart(String productName, int quantity) {

        //Check if the product in the database
        Product databaseProduct = null;
        for (Product product : Database.products) {
            if (product.getName().equals(productName)) {
                databaseProduct = product;
                break;
            }
        }

        if (databaseProduct == null) {
            throw new IllegalArgumentException("Product " + productName + " not found in the supermarket.");
        }

        //get the first cart
        Cart cart = cartDAO.getAllCarts().get(0); //if we have a single cart
        List<Product> products = cart.getAddedProducts();

        //check if the product was previously added to the cart or not
        boolean inCart = false;
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.setQuantity(product.getQuantity() + quantity);
                inCart = true;
                break;
            }
        }

        if (!inCart) {
            Product newProduct = new Product();
            newProduct.setName(databaseProduct.getName());
            newProduct.setPrice(databaseProduct.getPrice());
            newProduct.setQuantity(quantity);
            products.add(newProduct);
        }

        //update the cart's total price
        double updatedPrice = 0;
        for (Product product : products) {
            updatedPrice += product.getPrice() * product.getQuantity();
        }

        cart.setTotalPrice(updatedPrice);

        cartDAO.updateCart(cart);
    }

    public void removeFromCart(String productName, int quantity) {
        Cart cart = cartDAO.getAllCarts().get(0);
        List<Product> products = cart.getAddedProducts();

        //check if the product in cart
        Product cartProduct = null;
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                cartProduct = product;
                break;
            }
        }

        if (cartProduct == null) {
            throw new IllegalArgumentException("Product " + productName + " not found in the cart.");
        }

        //if there will be some of this product left in the cart
        if (cartProduct.getQuantity() - quantity > 0)
            cartProduct.setQuantity(cartProduct.getQuantity() - quantity);
        else {
            products.remove(cartProduct);
        }

        //update the price
        double updatedPrice = 0;
        for (Product product : products) {
            updatedPrice += product.getPrice() * product.getQuantity();
        }

        cart.setTotalPrice(updatedPrice);

        cartDAO.updateCart(cart);

    }

    public List<Product> viewProducts() {
        Cart cart = cartDAO.getAllCarts().get(0);
        return cart.getAddedProducts();
    }


    public void placeOrder() {
        Cart cart = cartDAO.getAllCarts().get(0); // Assuming a single cart
        List<Product> products = cart.getAddedProducts();

        if (products.isEmpty()) {
            throw new IllegalStateException("Cannot place an order. The cart is empty.");
        }

        //calculate order totals
        double subtotal = cart.getTotalPrice();
        double discount = calculateDiscount(subtotal);
        double tax = calculateTax(subtotal - discount);
        double shippingFee = calculateShippingFee();
        double checkoutTotal = subtotal - discount + tax + shippingFee;

        //ceate the order
        Order order = new Order();
        //We can replace all this with arguments constructor??
        order.setOrderId(generateOrderId());
        order.setUserId(1); // Assuming a single user, How to handle multiple users??
        order.setDiscount(discount);
        order.setTax(tax);
        order.setShippingFee(shippingFee);
        order.setCheckOutTotal(checkoutTotal);
        order.setPaymentMethod("Credit Card"); //Default payment methods unless the user sets it in the main

        //add the order to the database
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(order);

        //clear the cart
        products.clear();
        cart.setTotalPrice(0.0);
        cartDAO.updateCart(cart);

        //print the order details
        System.out.println("Order placed successfully! Order ID: " + order.getOrderId());
        System.out.println("Checkout Total: " + checkoutTotal);
    }

    //auxillary functions for place order
    private double calculateDiscount(double subtotal) {
        return subtotal > 100 ? subtotal * 0.10 : 0.0; //if the subtotal larger than 100 the discount will be applied
    }

    private double calculateTax(double amount) {
        return amount * 0.08;
    }

    private double calculateShippingFee() {
        return 5.0;
    }

    private int generateOrderId() {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.getAllOrders().size() + 1;
    }

//I changed the arguments -> no arg, update only interests
//After placing the order we should update the balance
public void updateCustomerDetails() {
    Cart cart = cartDAO.getAllCarts().get(0); //assume single cart ???
    List<Product> products = cart.getAddedProducts();


    //check if the cart have a customer
    Customer customer = null;
    for (Customer c : Database.customers) {
        if (c.getCart() != null && c.getCart().getCartId() == cart.getCartId()) {
            customer = c;
            break;
        }
    }

    if (customer == null) {
        throw new IllegalStateException("No customer associated with the current cart.");
    }

    for (Product product : cart.getAddedProducts()) {
       // customer.addInterest(product.getCategory()); // addInterest is not in customer entity yet????
    }

    System.out.println("Customer interests updated successfully for Customer ID: " + customer.getCustomerId());
}

