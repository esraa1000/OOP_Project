package Service;

import java.util.Date;
import java.util.Scanner;

import DAO.*;
import Entity.Customer;
import Entity.Product;


public class CustomerService extends UserService{


    private final CustomerDAO customerDAO= new CustomerDAO();
    private CartService cartService=new CartService();

    public void signUp(String username, String password, Date dateOfBirth){

        if(!(UserService.isValidUsername(username)))return;


        if(!(UserService.isValidPassword(password)))return;

        Customer customer= customerDAO.getCustomerByUsername(username);
        if(customer!=null){
            System.out.println("Username already taken");
            return;
        }



        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your address? ");
        String address=scanner.nextLine();


        while (true) {
            System.out.print("Enter gender (" + Customer.Gender.getValidValues() + "): ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                Customer.Gender gender = Customer.Gender.valueOf(input);
                customerDAO.createNewCustomer(username, password, dateOfBirth, address,gender);
                break; // Exit loop if input is valid
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter one of the following: " + Customer.Gender.getValidValues());
            }




        }

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


    public void deleteCustomer(int id){
        Customer customer=customerDAO.getById(id);
        if(customer!=null){
            customerDAO.delete(id);
        }

    }

    public void addCustomer(Customer customer){
        customerDAO.add(customer);
    }




//place order needs to be done
    public void addToCart(Customer customer,String productName,int quantity){
        cartService.addToCart(customer.getCustomerId(),productName,quantity);



    }

    public void placeOrder(){
    }

    public void removeFromCart(Customer customer,String productName,int quantity){

        cartService.removeFromCart(customer.getCustomerId(),productName,quantity);


    }





}