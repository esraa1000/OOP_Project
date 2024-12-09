package DAO;

import Database.Database;
import Entity.Customer;
import Entity.Order;


import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements GenericDAO<Order> {



    public Order getById(int orderId) {
        for (Order order : Database.orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        throw new IllegalArgumentException("Order with ID " + orderId + " was not found");
    }

    public void add(Order order) {
        Database.orders.add(order);
    }

    public void update(Order order) {
        for (int i = 0; i < Database.orders.size(); i++) {
            if (Database.orders.get(i).getOrderId() == order.getOrderId()) {
                Database.orders.set(i, order);
            }
        }
    }

    public void delete(int orderId) {
        Database.orders.removeIf(order -> order.getOrderId() == orderId);
    }

    public List<Order> findOrdersByCustomerUsername(String username) {
        for (Customer customer : Database.customers) {
            if (customer.getUsername().equals(username)) {
                return new ArrayList<>(customer.getOrders());
            }
        }
        throw new IllegalArgumentException("Customer with username " + username + " was not found");
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(Database.orders);
    }

    public void createNewOrder(int userId,double discount,double tax,double shippingFee,double checkOutTotal,String paymentMethod){
        Order newOrder = new Order();
        newOrder.setUserId(userId);
        newOrder.setDiscount(discount);
        newOrder.setTax(tax);
        newOrder.setShippingFee(shippingFee);
        newOrder.setCheckOutTotal(checkOutTotal);
        newOrder.setPaymentMethod(paymentMethod);

        add(newOrder);
    }

}