class OrderVisitor implements VisitorInterface {
  private static OrderVisitor visitor = new OrderVisitor();
  public OrderVisitor() {
  }
  public static OrderVisitor getVisitor() {
    return visitor;
  }
  public double visit(NonCaliforniaOrder order) {
    return order.getOrderAmount();
  }
  public double visit(CaliforniaOrder order) {
    return order.getOrderAmount()+order.getAdditionalTax();
  }
  public double  visit(OverseasOrder order) {
    return order.getOrderAmount()+order.getAdditionalSH();
  }

}
