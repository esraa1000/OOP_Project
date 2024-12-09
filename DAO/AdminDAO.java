package DAO;
import Database.Database;
import Entity.Admin;

import java.util.Date;
import java.util.List;

public class AdminDAO implements GenericDAO<Admin> {

    public AdminDAO(){}

    public  void add(Admin admin){
        Database.admins.add(admin);
    }

    public Admin getById(int id){
        for(Admin a: Database.admins){
            if(a.getAdminId()==id){
                return a;
            }
        }
        return null;
    }

    public void update(Admin admin){
        for(Admin a: Database.admins){
            if(a.getAdminId()==admin.getAdminId()){
                a.setRole(admin.getRole());
                a.setWorkingHours(admin.getWorkingHours());

            }
        }
    }

    public void delete(int id){
        Database.admins.removeIf(a -> a.getAdminId() == id);
    }




    public static List<Admin> getAllAdmins(){
        return Database.admins;
    }



    public Admin getAdminByUsername(String username){
        for(Admin a: Database.admins){
            if(a.getUsername().equals(username))return a;

        }
        return null;
    }

    public String getPassword(Admin admin){
        return admin.getPassword();
    }

    public void createNewAdmin(String username, String password, Date dateOfBirth,String role,int workingHours){
        Admin admin=new Admin(username,password,dateOfBirth,role,workingHours);
        Database.admins.add(admin);


    }


}
