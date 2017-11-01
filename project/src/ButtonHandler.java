import java.awt.event.*;

public class ButtonHandler implements ActionListener {
  private WindowManager wmanager;
  private OrderManager omanager;
  public ButtonHandler() {
  }
  public ButtonHandler(WindowManager manager, OrderManager omanager) {
    this.wmanager=manager;
    this.omanager =  omanager;
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals(WindowManager.EXIT)) System.exit(0);
    if (e.getActionCommand().equals(WindowManager.CREATEORDER)) {
      
      // Get data
      String type=wmanager.getOrderType();
      String amount=wmanager.getOrderAmount();
      String tax=wmanager.getTax();
      String sh=wmanager.getSH();
      double damount=new Double(amount).doubleValue(),dtax=new Double(tax).doubleValue(),dsh=new Double(sh).doubleValue();


      // Create the order
      int nrows =wmanager.getTable().getRowCount();
      Integer code= nrows==0?0:(Integer)wmanager.getTable().getValueAt(nrows-1,0);
      
      // Get the Factory of orders
      OrderFactory factory = omanager.getFactory();
      Order order=factory.createOrder(code+1,type,damount,dtax,dsh);
      
      // Update the table
      wmanager.getModel().addRow(order.toRow());
      wmanager.getTable().repaint();

      // Add the order
      omanager.addOrder(order);

      // Logic clean and message
      wmanager.setTotalValue(" Order Created Successfully ");
      wmanager.setVisibleTotalValue(true);
      wmanager.getUpdateButton().setVisible(false);
      wmanager.cleanFields();
    }
    if (e.getActionCommand().equals(WindowManager.GETTOTAL)) { 
      String totalResult=new Double(omanager.getOrderTotal()).toString();
      totalResult=" Orders Total = "+totalResult;
      wmanager.setTotalValue(totalResult);
    }
    if (e.getActionCommand().equals(WindowManager.UPDATE)) {
      // Get data
      String type=wmanager.getOrderType();
      String amount=wmanager.getOrderAmount();
      String tax=wmanager.getTax();
      String sh=wmanager.getSH();
      double damount=new Double(amount).doubleValue(),dtax=new Double(tax).doubleValue(),dsh=new Double(sh).doubleValue();
      omanager.updateOrder(type,damount,dtax,dsh);
      
      wmanager.setTotalValue(" Order Updated Successfully ");
      wmanager.getUpdateButton().setVisible(false);
      wmanager.cleanFields();
      
      //Update the table
      wmanager.updateRow(omanager.getCurrentOrder().toRow(),omanager.getCurrentRow());
      
    }
  }
}
