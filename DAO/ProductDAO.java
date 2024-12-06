package DAO;
import Database.Database;
import Entity.Product;

import java.util.List;

public class ProductDAO implements GenericDAO<Product> {

    // getting a certain product by id
    public Product getById(int id ){
        for(Product product:Database.products){
            if(product.getId()==id){
                return product;
            }

        }
        return null;
    }
    //adding a product to our database
    public void add(Product product){
        Database.products.add(product);
    }
    //To find a category in the categories list by its id and replace it with a new version (updatedCategory)
    //If the category with the matching id is found, the update is performed, and the method returns true.
    //If no matching category is found, the method returns false
    public void update(Product updatedProduct){
     for(Product product: Database.products){
         if(product.getId()==updatedProduct.getId()){
             int index= Database.products.indexOf(product);
             Database.products.set(index,updatedProduct);

         }

     }

    }
    //deleting a certain product from the database
    public void delete(int id){
        Database.products.removeIf(product -> product.getId() == id);
    }




}
