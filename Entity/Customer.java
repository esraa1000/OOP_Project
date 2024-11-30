package Entity;

import java.util.Date;
import java.util.List;

public class Customer extends User{
    private double balance;
    private String address;
    private List<String> interests;
    private enum gender{
        MALE,
        FEMALE;

    }
    //private Cart cart;

    public Customer(){}

    public Customer(String username, String password, Date dateOfBirth,double balance, String address){
        super(username,password,dateOfBirth);
        this.balance=balance;
        this.address=address;
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
}
