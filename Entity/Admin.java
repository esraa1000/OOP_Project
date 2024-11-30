package Entity;

import java.util.Date;

public class Admin extends User{
    private String role;
    private double workingHours;

    public Admin(){}

    public Admin(String username, String password, Date dateOfBirth, String role, double workingHours){
        super(username,password,dateOfBirth);
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
}
