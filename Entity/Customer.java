package Entity;

import java.util.Date;
import java.util.List;

public class Customer extends User{
    private int customerId;
    private double balance;
    private String address;
    private List<String> interests;
    private enum gender{
        MALE,
        FEMALE;

    }
    //private Cart cart;

    public Customer(){}

    public Customer(int userId,String username, String password, Date dateOfBirth,int customerId,double balance, String address){
        super(userId, username,password,dateOfBirth);
        this.customerId=customerId;
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
