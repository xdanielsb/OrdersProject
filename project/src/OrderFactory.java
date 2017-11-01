import java.util.*;

public class OrderFactory {
  private static HashMap<String,Order> orders = new HashMap<String, Order>(); 
  private static OrderFactory factory = new OrderFactory();
  private OrderFactory() {
    orders.put(WindowManager.CAORDER,new CaliforniaOrder());
    orders.put(WindowManager.NONCAORDER,new NonCaliforniaOrder());
    orders.put(WindowManager.OVERSEASORDER,new OverseasOrder());
  }
  public static OrderFactory getFactory() {
    return factory;
  }
  public Order createOrder(Integer code, String type, double amount, double tax, double sh) {
    if (type.equalsIgnoreCase(WindowManager.CAORDER)) {
      CaliforniaOrder old = (CaliforniaOrder) orders.get(WindowManager.CAORDER);
      CaliforniaOrder order = (CaliforniaOrder) old.clone();
      order.setCode(code);
      order.setOrderAmount(amount);
      order.setOrderTax(tax);
      return order;
    }
    else if (type.equalsIgnoreCase(WindowManager.NONCAORDER)) {
      NonCaliforniaOrder old  = (NonCaliforniaOrder) orders.get(WindowManager.NONCAORDER);
      NonCaliforniaOrder order = (NonCaliforniaOrder) old.clone();
      order.setCode(code);
      order.setOrderAmount(amount);
      return order;
    }
    else if (type.equalsIgnoreCase(WindowManager.OVERSEASORDER)) {
      OverseasOrder old  = (OverseasOrder)orders.get(WindowManager.OVERSEASORDER);
      OverseasOrder order = (OverseasOrder)old.clone();
      order.setAdditionalSH(sh);
      order.setCode(code);
      order.setOrderAmount(amount);
      return order;
    }
    else return null;
  }
}
