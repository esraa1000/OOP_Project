package DAO;
import Entity.Customer;
import Database.Database;

import java.util.List;

public class CustomerDAO {

    public void addCustomer(Customer customer){
        Database.customers.add(customer);

    }

    public Customer getCustomerById(int id){
        for(Customer c: Database.customers){
            if(c.getCustomerId()==id){
                return c;
            }
        }
        return null;
    }

    public List<Customer> getAllCustomers(){
        return Database.customers;
    }

    public void updateCustomer(Customer customer){
        for(Customer c: Database.customers){
            if(c.getCustomerId()==customer.getCustomerId()){
                //need to figure out a way to update the username, password and dataOfBirth as well
                c.setGender(customer.getGender());
                c.setAddress(customer.getAddress());
                c.setBalance(customer.getBalance());
                c.setInterests(customer.getInterests());
                c.setCart(customer.getCart());
            }
        }
    }

    public void deleteCustomer(int customerId){
        Database.customers.removeIf(c -> c.getCustomerId() == customerId);
    }


}
