public class CaliforniaOrder implements Order {
  private Integer code;
  private double amount;
  private double tax;
  public CaliforniaOrder() {
  }
  public CaliforniaOrder(Integer code, double amount, double tax) {
    this.amount=amount;
    this.tax=tax;
    this.code=code;
  }
  public double getOrderAmount() {
    return amount;
  }
  public double getAdditionalTax() {
    return tax;
  }
  
  public void setOrderAmount(double amount) {
    this.amount = amount;
  }
  public void setOrderTax(double tax) {
    this.tax = tax;
  }
  public double accept(OrderVisitor visitor) {
    return visitor.visit(this);
  }
  @Override
  public Object[] toRow() {
    return new Object[]{this.code,WindowManager.CAORDER,this.amount,this.tax,"-","delete"};
  }
  @Override
  public Integer getCode() {
    return code;
  }
  public Object clone() {
    try {
      return super.clone();
    }
    catch (CloneNotSupportedException e) {
      return null;
    }
  }
  @Override
  public void setCode(Integer code) {
    this.code =code;
  }
}
