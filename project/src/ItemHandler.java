import java.awt.event.*;

public class ItemHandler implements ItemListener {
  WindowManager manager;
  public ItemHandler(WindowManager manager) {
    this.manager=manager;
  }
  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange()==ItemEvent.SELECTED) {
      if(!manager.getUpdateButton().isVisible()) {
        manager.cleanFields();
      }
      String item=(String)e.getItem();
      if (item.equalsIgnoreCase(WindowManager.CAORDER)) {
        manager.getTxtSH().setEnabled(false);
        manager.getTxtTax().setEnabled(true);
      }else if (item.equalsIgnoreCase(WindowManager.NONCAORDER)) {
        manager.getTxtSH().setEnabled(false);
        manager.getTxtTax().setEnabled(false);
      }else if (item.equalsIgnoreCase(WindowManager.OVERSEASORDER)) {
        manager.getTxtSH().setEnabled(true);
        manager.getTxtTax().setEnabled(false);
      }
    }
  }
}
