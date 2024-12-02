package DAO;
import Database.Database;
import Entity.Product;

import java.util.List;

public class ProductDAO {

    // getting a certain product by id
    public static Product getProductbyId(int id ){
        for(Product product:Database.products){
            if(product.getId()==id){
                return product;
            }

        }
        return null;
    }
    //adding a product to our database
    public static void addProduct(Product product){
        Database.products.add(product);
    }
    //To find a category in the categories list by its id and replace it with a new version (updatedCategory)
    //If the category with the matching id is found, the update is performed, and the method returns true.
    //If no matching category is found, the method returns false
    public static boolean updateProduct(Product updatedProduct){
     for(Product product: Database.products){
         if(product.getId()==updatedProduct.getId()){
             int index= Database.products.indexOf(product);
             Database.products.set(index,updatedProduct);
             return true;
         }

     }
     return false;
    }
    //deleting a certain product from the database
    public static void deleteProduct(Product product){
        Database.products.remove(product);
    }




}
