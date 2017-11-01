public class NonCaliforniaOrder implements Order{
  private double amount;
  private Integer code;
  public NonCaliforniaOrder() {
  }
  public NonCaliforniaOrder(Integer code, double amount) {
    this.amount=amount;
    this.code = code;
  }
  public double getOrderAmount() {
    return amount;
  }
  public void setOrderAmount(double amount) {
    this.amount = amount;
  }
  public double accept(OrderVisitor visitor) {
    return visitor.visit(this);
  }
  @Override
  public Object[] toRow() {
    return new Object[]{code, WindowManager.NONCAORDER,this.amount,"-","-","delete"};
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
