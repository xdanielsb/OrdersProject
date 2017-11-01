public interface Order extends Cloneable {
  public double accept(OrderVisitor v);
  public Object[] toRow ();
  public Integer getCode();
  public void setCode(Integer code);
}
