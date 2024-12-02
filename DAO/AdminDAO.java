package DAO;
import Database.Database;
import Entity.Admin;

import java.util.List;

public class AdminDAO  {

    public AdminDAO(){}

    public void addAdmin(Admin admin){
        Database.admins.add(admin);
    }

    public Admin getAdminById(int id){
        for(Admin a: Database.admins){
            if(a.getAdminId()==id){
                return a;
            }
        }
        return null;
    }

    public List<Admin> getAllAdmins(){
        return Database.admins;
    }

    public void updateAdmin(Admin admin){
        for(Admin a: Database.admins){
            if(a.getAdminId()==admin.getAdminId()){
                a.setRole(admin.getRole());
                a.setWorkingHours(admin.getWorkingHours());

            }
        }
    }

    public Admin getAdminByUsername(String username){
        for(Admin a: Database.admins){
            if(a.getUsername().equals(username))return a;

        }
        return null;
    }


    public void deleteAdmin(int id){
        Database.admins.removeIf(a -> a.getAdminId() == id);
    }


}
