package Service;


import java.util.Date;

public abstract class UserService {

    public abstract void signUp(String username, String password, Date dateOfBirth);
    public abstract void logIn(String username, String password);

}
