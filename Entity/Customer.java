package Entity;

import java.util.Date;
import java.util.List;

public class Customer extends User {
    private static int idCounter;
    private int customerId;
    private double balance;
    private String address;
    private List<String> interests;
    enum Gender {
        MALE,
        FEMALE;
    }
    private Gender gender;
    private Cart cart;
    private List<Order> orders; // Added list of orders

    public Customer() {this.customerId=++idCounter;}

    public Customer(String username,String password,Date dateOfBirth){
        super(username,password,dateOfBirth);
        this.customerId=++idCounter;

    }



    public Customer( String username, String password, Date dateOfBirth, double balance, String address, List<Order> orders) {
        super(username, password, dateOfBirth);
        this.balance = balance;
        this.address = address;
        this.orders = orders; // Initialize orders
        this.customerId=++idCounter;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
