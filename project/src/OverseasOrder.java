public class OverseasOrder implements Order {
  private double amount;
  private double additionalSH;
  private Integer code;
  public OverseasOrder() {
  }
  public OverseasOrder(Integer code, double amount, double sh) {
    this.amount=amount;
    additionalSH=sh;
    this.code = code;
  }
  public double getOrderAmount() {
    return amount;
  }
  public void setOrderAmount(double amount) {
    this.amount = amount;
  }
  public double getAdditionalSH() {
    return additionalSH;
  }
  public void setAdditionalSH(double sh) {
    this.additionalSH = sh;
  }
  
  public double accept(OrderVisitor visitor) {
    return visitor.visit(this);
  }
  @Override
  public Object[] toRow() {
    return new Object[]{this.code,WindowManager.OVERSEASORDER,this.amount,"-",this.additionalSH,"delete"};
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
