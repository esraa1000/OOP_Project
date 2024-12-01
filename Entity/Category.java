package Entity;
import java.util.List;

public class Category {

    String name;
    List<Product> productsInCategory;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(){}
    public Category(String name,List<Product> productsInCategory,int id){
        this.name=name;
        this.productsInCategory=productsInCategory;
        this.id=id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductsInCategory() {
        return productsInCategory;
    }

    public void setProductsInCategory(List<Product> productsInCategory) {
        this.productsInCategory = productsInCategory;
    }
}
