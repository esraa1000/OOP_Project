package Entity;

import java.util.List;

public class Cart {
    private static int idCounter = 0; // static counter for generating IDs
    private List<Product> addedProducts;
    private double totalPrice;
    private int cartId;

    public Cart(){
        this.cartId=++idCounter;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<Product> getAddedProducts() {
        return addedProducts;
    }

    public void setAddedProducts(List<Product> addedProducts) {
        this.addedProducts = addedProducts;
    }
}
