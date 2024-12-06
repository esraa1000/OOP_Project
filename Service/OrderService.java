package Service;
import DAO.OrderDAO;
import Entity.Order;

public class OrderService {
  private final OrderDAO orderDAO;

  //constructor
  public OrderService(OrderDAO orderDAO ){
      this.orderDAO = orderDAO;
  }
  public void placeOrder(Order order){
      if (order == null || order.getOrderId() < 0 || order.getPaymentMethod() == null) {
          System.out.println("Order details are invalid.");
          return;
      }

      if (orderDAO.getById(order.getOrderId()) == null) {
          orderDAO.add(order);
          System.out.println("Order has been placed successfully.");
      } else {
          System.out.println("An order with this ID already exists.");
      }
  }

  public void updateOrder(Order order){
      ////
  }
  public void cancelOrder(int orderId){
     //
  }
  //public List<Order> getAllOrders(){
      //
  }



