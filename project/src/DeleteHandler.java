import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class DeleteHandler extends AbstractAction {
  private WindowManager wmanager;
  private OrderManager omanager;
  public DeleteHandler (WindowManager manager, OrderManager register) {
    this.wmanager = manager;
    this.omanager = register;
  }
  public void actionPerformed(ActionEvent e) {
    JTable table = wmanager.getTable();
    int modelRow=Integer.valueOf(e.getActionCommand());
    int dialogButton=JOptionPane.YES_NO_OPTION;
    int dialogResult=JOptionPane.showConfirmDialog(null,"Would You Like to delete the register?","Warning",dialogButton);
    int row=table.getSelectedRow();
    if (row>=0) {
      if (dialogResult==JOptionPane.YES_OPTION) {
        Integer code=(Integer)table.getValueAt(row,0);
        omanager.deleteOrder(code);
        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
        if (omanager.getCurrentOrder().getCode() == code) {
          wmanager.cleanFields();
          wmanager.getUpdateButton().setVisible(false);
        }
        if(omanager.getCurrentRow()  < row) {
          omanager.setCurrentOrder(omanager.getCurrentRow()-1);
        }
      }
    }
  }
}
