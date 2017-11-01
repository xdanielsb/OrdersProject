import java.util.*;

public class OrderManager {
  private static HashMap<Integer,Order> orderHash=new HashMap<Integer,Order>();
  private Order currentOrder;
  private Integer currentRow;
  private OrderVisitor visitor;
  private static OrderManager omanager = new OrderManager();
  private static OrderFactory factory;
  public OrderManager() {
    visitor = OrderVisitor.getVisitor();
    factory = OrderFactory.getFactory();
  }
  public static OrderManager getRegister() {
     return omanager;
  }
  public double getOrderTotal() {
    double total=0.0;
    for (Order or:orderHash.values()) total += or.accept(visitor);
    return total;
  }
  public void addOrder(Order order) {
    orderHash.put(order.getCode(),order);
  }
  public void updateOrder(String type, double amount, double tax, double sh) {
    Integer code  = omanager.currentOrder.getCode();
    if (type.equalsIgnoreCase(WindowManager.CAORDER)) {
       CaliforniaOrder a = new CaliforniaOrder(code, amount, tax);
       currentOrder =a;
       orderHash.put(code,a);
    }
    else if (type.equalsIgnoreCase(WindowManager.NONCAORDER)) {
       NonCaliforniaOrder a = new NonCaliforniaOrder(code, amount);
       currentOrder =a;
       orderHash.put(code,a);
    }
    else if (type.equalsIgnoreCase(WindowManager.OVERSEASORDER)) {
      OverseasOrder a = new OverseasOrder(code, amount, sh);
      currentOrder =a;
      orderHash.put(code,a);
    }
  }
  public synchronized void deleteOrder(Integer code) {
    orderHash.remove(code);
  }
  public void setCurrentOrder(Integer code) {
    currentOrder = orderHash.get(code);
  }
  public Order getCurrentOrder() {
    return currentOrder;
  }
  public void setCurrentRow(int current) {
    currentRow = current;
  }
  public Integer getCurrentRow() {
    return currentRow;
  }
  
  public OrderFactory getFactory() {
    return factory;
  }
}
