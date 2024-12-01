package Entity;

import java.util.Date;

public class Admin extends User{
    private int adminId;
    private String role;
    private double workingHours;

    public Admin(){}

    public Admin(int userId,String username, String password, Date dateOfBirth,int adminId, String role, double workingHours){
        super(userId,username,password,dateOfBirth);
        this.adminId=adminId;
        this.role=role;
        this.workingHours=workingHours;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
