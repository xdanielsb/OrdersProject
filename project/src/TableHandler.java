import javax.swing.*;
import javax.swing.event.*;

public class TableHandler  implements ListSelectionListener{
  private WindowManager manager;
  private OrderManager register;
  public TableHandler(WindowManager manager,  OrderManager register) {
    this.manager=manager;
    this.register = register;
  }
  public void valueChanged(ListSelectionEvent e) {
    JTable table =manager.getTable();
    int row=table.getSelectedRow();
    int col = table.getSelectedColumn();
    if (row>=0 && col!=table.getColumnCount()-1) {
      manager.getUpdateButton().setVisible(true);
      Integer code= (Integer)table.getValueAt(row,0);
      register.setCurrentOrder(code);
      register.setCurrentRow(row);
      String type=(String)table.getValueAt(row,1);
      Double amount = (Double)table.getValueAt(row,2);
      manager.getTxtAmount().setText(amount.toString());
      if (type.equalsIgnoreCase(WindowManager.CAORDER)) {
        Double tax = (Double)table.getValueAt(row,3);
        manager.getTxtTax().setText(tax.toString());
        manager.getCmbType().setSelectedItem(WindowManager.CAORDER);
      }
      if (type.equalsIgnoreCase(WindowManager.OVERSEASORDER)) {
        Double sh = (Double)table.getValueAt(row,4);
        manager.getTxtSH().setText(sh.toString());
        manager.getCmbType().setSelectedItem(WindowManager.OVERSEASORDER);
      }
      if (type.equalsIgnoreCase(WindowManager.NONCAORDER)) {
        manager.getCmbType().setSelectedItem(WindowManager.NONCAORDER);
      }
      
    }
  }
}
