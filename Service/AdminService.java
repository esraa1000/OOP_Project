package Service;
import DAO.AdminDAO;
import Entity.Admin;


import java.util.Date;
import java.util.Scanner;

public class AdminService extends UserService {

    private final AdminDAO adminDAO= new AdminDAO();


    @Override
    public void logIn(String username, String password)  {

        if(username==null || password==null){
            //handle invalid

        }

        Admin admin=adminDAO.getAdminByUsername(username);

        if(admin==null){
            //handle username not being found
        }

        if(!((admin.getPassword()).equals(password))){
            //handle wrong password being entered
        }

        //handle successful login


    }

    @Override
    public void signUp(String username, String password, Date dateOfBirth) { //will call adminSignUp
        if(username==null || password==null){
            //handle missing input
        }

        Admin admin= adminDAO.getAdminByUsername(username);

        if(admin!=null){
            //handle repeated username
        }

        Admin newAdmin= new Admin(username, password,dateOfBirth);

        System.out.println("Enter role and working hours");
        Scanner scanner = new Scanner(System.in);
        String role= scanner.nextLine();
        scanner.nextLine();
        int workingHours=scanner.nextInt();

        adminSignUp(role,workingHours,admin);

        adminDAO.addAdmin(admin);

    }



    public void adminSignUp(String role, int workingHours,Admin admin) {
        admin.setRole(role);
        admin.setWorkingHours(workingHours);
    }

}

