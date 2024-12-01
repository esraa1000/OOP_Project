package DAO;

import Database.Database;
import Entity.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDAO{

public List<Cart> getAllCarts() {
    return new ArrayList<>(Database.carts);
}
    public void deleteCart(int cartId) {
        Database.carts.removeIf(cart -> cart.getCartId() == cartId);
    }

    public void updateCart(Cart cart){
    for (int i = 0 ; i < Database.carts.size(); i++){
        if (Database.carts.get(i).getCartId() == cart.getCartId()){
            Database.carts.set(i, cart);
        }
      }
    }
    public void addCart(Cart cart){
    for (Cart ExistingCart : Database.carts){      //check if the cart exists
        if(ExistingCart.getCartId() == cart.getCartId()){
            throw new IllegalArgumentException("cart with ID " + cart.getCartId() + "already exists");
        } else{
            Database.carts.add(cart);
            }
        }
    }
}