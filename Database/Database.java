package Database;

import Entity.*;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Customer> customers= new ArrayList<>();
    public static List<Product> products= new ArrayList<>();
    public static List<Order> orders= new ArrayList<>();
    public static List<Category>categories= new ArrayList<>();
    public static List<Admin>admins=new ArrayList<>();

    static{
        //This block can be used to initialize dummy data
    }


}
