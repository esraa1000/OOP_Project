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
      if (order == null || order.getOrderId() < 0 || order.getPaymentMethod() == null){
          System.out.println("order details are invalid");
          return;
      }
      if (orderDAO.getById((order.getOrderId())) != null){
          orderDAO.update(order);
          System.out.println("order has been updated successfully");
      }
      else {
          System.out.println("order with this ID does not exist");
      }
  }
  public void cancelOrder(int orderId){
     if (orderId < 0){
         System.out.println("invalid order ID");
     }
     if (orderDAO.getById(orderId) != null){
         orderDAO.delete(orderId);
         System.out.println("order has been cancelled successfully");
     }
     else {
         System.out.println("Order with this ID does not exist");
     }
  }
  public Order getOrderById(int orderId){
  if (orderId < 0){
      System.out.println("invalid Order ID");
  }
 Order order = orderDAO.getById((orderId));
  if (order != null){
      return order;
  } else{
      System.out.println("Order with this ID does not exist");
      return null;
     }
  }

    public List<Order> getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        if (!orders.isEmpty()) {
            return orders;
        } else {
            System.out.println("No orders found.");
            return new ArrayList<>();
        }
    }

}



