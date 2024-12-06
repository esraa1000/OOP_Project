package Service;
import DAO.AdminDAO;
import Entity.Admin;
import Utility.ValidationUtils;


import java.util.Date;
import java.util.Scanner;


public class AdminService extends UserService {

    private final AdminDAO adminDAO= new AdminDAO();

    public void logIn(String username, String password)  {

        if(username==null || password==null){
            System.out.println("Please make sure to fill out all fields");
            return;

        }

        boolean validPassword= ValidationUtils.passwordValidator(password);
        if(!validPassword)return;



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

        adminSignUp(role,workingHours,newAdmin);

        adminDAO.add(newAdmin);

    }



    public void adminSignUp(String role, int workingHours,Admin admin) {
        admin.setRole(role);
        admin.setWorkingHours(workingHours);
    }

}

