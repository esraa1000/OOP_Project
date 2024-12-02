package Service;
import DAO.ProductDAO;
import Database.Database;
import Entity.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public List<Product> getallProducts(){
        if(Database.products.isEmpty()){
        System.out.println("There are no Products available");
    }else{
        System.out.println("The products available are :");
    }
        return Database.products;

    }
    public void addProduct(Product product){
        if(product!=null && product.getId()>=0 && product.getName()!=null ) {
            if (ProductDAO.getProductbyId(product.getId()) == null) {
                ProductDAO.addProduct(product);
                System.out.println("Product is added successfully");
            } else {
                System.out.println("This product already exists ");
            }
        }else{
            System.out.println("Product details are invalid");
        }
    }
    public void updateProduct(Product updatedProduct){
        if(updatedProduct!=null && updatedProduct.getId()>=0 && updatedProduct.getName()!=null ) {
            if (ProductDAO.getProductbyId(updatedProduct.getId()) != null) {
                ProductDAO.updateProduct(updatedProduct);
                System.out.println("Product is updated successfully");
            } else {
                System.out.println("Product does not exist");
            }
        }else {
            System.out.println("Uptaded Product details are invalid ");
        }
    }
    public void deleteProduct(Product product){
        if(ProductDAO.getProductbyId(product.getId())!=null){
            ProductDAO.deleteProduct(product);
            System.out.println("Product is deleted successfully");
        }else{
            System.out.println("The product you are trying to delete does not already exist");

        }

    }
    //need to figure out how we can link it to notify the admin or whatever
    public List<Product> CheckforRestock(){
        List<Product> needsRestock= new ArrayList<>();
        for(Product product:Database.products){
            if(product.getQuantity()==0){
                needsRestock.add(product);
            }
        }
        if(needsRestock.isEmpty()){
            System.out.println("All Products are sufficiently stocked");
        }else{
            System.out.println("The following products needs restocking");
            for (Product product : needsRestock) {
                System.out.println("Product ID: " + product.getId() +
                        ", Name: " + product.getName() +
                        ", Current Quantity: " + product.getQuantity());
            }
        }
        return needsRestock;
    }




}
