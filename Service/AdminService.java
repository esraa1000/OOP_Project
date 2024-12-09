package Service;
import DAO.AdminDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Entity.Admin;
import Entity.Category;
import Entity.Product;
import Service.ProductService;
import Service.CategoryService;


import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class AdminService extends UserService {

    private final AdminDAO adminDAO= new AdminDAO();
    private final ProductService productService= new ProductService();
    private final CategoryService categoryService=new CategoryService();
    private final CategoryDAO categoryDAO=new CategoryDAO();



    public void logIn(String username, String password)  {

        if(username==null || password==null){
            System.out.println("Please make sure to fill out all fields");
            return;

        }


        Admin admin=adminDAO.getAdminByUsername(username);

        if(admin==null){
            System.out.println("Account not found, please make sure the username is correct or try to sign up if you're new here.");
            return;
        }

        if(!((admin.getPassword()).equals(password))){
            System.out.println("Wrong password entered.");
            return;
        }

        System.out.println("Login successful!");


    }



    @Override
    public void signUp(String username, String password, Date dateOfBirth) {
        if(username==null || password==null || dateOfBirth==null){
            System.out.println("Please make sure to fill out all fields");
            return;
        }

        if(!(UserService.isValidUsername(username)))return;


        if(!(UserService.isValidPassword(password)))return;



        Admin admin= adminDAO.getAdminByUsername(username);

        if(admin!=null){
            System.out.println("Username is already taken, try another one, please");
        }



        Admin newAdmin= new Admin(username, password,dateOfBirth);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter role:");
        String role = scanner.nextLine();

        System.out.println("Enter working hours:");
        int workingHours;
        try {
            workingHours = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input for working hours.");
            return;
        }

        adminDAO.createNewAdmin(username,password,dateOfBirth,role,workingHours);
        System.out.println("Signup successful!");

    }
    public void CreateProduct(String name, double price, Category category, int quantity){
       productService.createProduct(name,price,category,quantity);
    }
    public void CreateCategory(String name, List<Product> products){
        categoryService.createCategory(name,products);
    }










}