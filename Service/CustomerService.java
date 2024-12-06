package Service;

import java.util.Date;
import java.util.Scanner;

import DAO.*;
import Entity.Customer;
import Entity.Product;


public class CustomerService extends UserService{


    private final CustomerDAO customerDAO= new CustomerDAO();

    public void signUp(String username, String password, Date dateOfBirth){

        if(!(UserService.isValidUsername(username)))return;


        if(!(UserService.isValidPassword(password)))return;

        Customer customer= customerDAO.getCustomerByUsername(username);
        if(customer!=null){
            System.out.println("Username already taken");
            return;
        }

        Customer newCustomer= new Customer(username,password,dateOfBirth);

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your address? ");
        String address=scanner.nextLine();
        System.out.println();

        customerSignUp(address,newCustomer);

        customerDAO.add(newCustomer);


    }
    public void logIn(String username,String password){



        Customer customer=customerDAO.getCustomerByUsername(username);

        if(customer==null){
            System.out.println("Account not found, please make sure the username is correct or try to sign up if you're new here.");
            return;
        }

        if(!((customer.getPassword()).equals(password))){
            System.out.println("Wrong password entered.");
            return;
        }

        System.out.println("Login successful!");

    }


    public void customerSignUp(String address,Customer customer){
        customer.setAddress(address);

    }

    public void addToCart(String username,Product product){

        Customer customer= customerDAO.getCustomerByUsername(username);

        if(customer==null){
            System.out.println();
        }


    }




}
