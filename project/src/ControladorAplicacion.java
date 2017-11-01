import javax.swing.*;
import javax.swing.UIManager.*;

public class ControladorAplicacion {
  
  public ControladorAplicacion() {
  }
  
  public void ConfigureLookFeel() {
    try {
      for (LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (Exception e) {
    }
  }
  
  public void createGUI(OrderManager manager) {
    WindowManager wmanager = new WindowManager(1000,500);
    wmanager.initialize(manager);
  }

}
