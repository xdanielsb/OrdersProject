import javax.swing.*;
import javax.swing.UIManager.*;

public class Launcher {
  public static void main(String[] args) {
     ControladorAplicacion con = new ControladorAplicacion(); 
     OrderManager omanager = OrderManager.getRegister(); 
     con.ConfigureLookFeel();
     con.createGUI(omanager);
  }
}
