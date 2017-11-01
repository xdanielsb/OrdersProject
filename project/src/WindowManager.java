
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class WindowManager extends JFrame {

  public static final String NEWLINE="\n";
  public static final String GETTOTAL="Get Total";
  public static final String CREATEORDER="Create";
  public static final String EXIT="Exit";
  public static final String UPDATE="Update";
  public static final String CAORDER="California Order";
  public static final String NONCAORDER="Non-California Order";
  public static final String OVERSEASORDER="Overseas Order";

  private JComboBox<String> orderType;
  private JTextField orderAmount,taxOrder,orderSH;
  private JLabel lblOrderType,lblOrderAmount,lblAdditionalTax,lblAdditionalSH;
  private JLabel lblTotal,lblTotalValue;
  private JButton getTotalButton,createOrderButton,exitButton, updateButton;
  private DefaultTableModel model;
  private JTable table;

  public WindowManager(int width, int height) {
    super("Order Creation");
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    this.setSize(width,height);
    this.setVisible(true);
  }
  
  public void initialize(OrderManager omanager) {
    orderType=new JComboBox<String>();
    List<String> ordertypes=Arrays.asList(WindowManager.CAORDER,WindowManager.NONCAORDER,WindowManager.OVERSEASORDER);
    for (String aux:ordertypes)orderType.addItem(aux);
    ItemHandler  itemh = new ItemHandler(this);
    orderType.addItemListener(itemh);
    List<Component> elementsIn=Arrays.asList(lblOrderType=new JLabel("Order Type:"),orderType,lblOrderAmount=new JLabel("Order Amount:"),orderAmount=new JTextField(10),lblAdditionalTax=new JLabel("Additional Tax(CA Orders Only):"),taxOrder=new JTextField(10),lblAdditionalSH=new JLabel("Additional S & H(Overseas Orders Only):"),orderSH=new JTextField(10));
    List<Component> elementsListen=Arrays.asList(getTotalButton=new JButton(WindowManager.GETTOTAL),createOrderButton=new JButton(WindowManager.CREATEORDER),exitButton=new JButton(WindowManager.EXIT), updateButton=new JButton(WindowManager.UPDATE));
    List<Component> results=Arrays.asList(lblTotal=new JLabel("Result:"),lblTotalValue=new JLabel("Click Create or GetTotal Button"));
    ButtonHandler buttonh=new ButtonHandler(this, omanager);
    getTotalButton.addActionListener(buttonh);
    createOrderButton.addActionListener(buttonh);
    exitButton.addActionListener(buttonh);
    updateButton.addActionListener(buttonh);
    JPanel inputElements=new JPanel(new GridLayout(5,2)), buttonElements=new JPanel(new FlowLayout()), resultElements=new JPanel(new FlowLayout()), tableElements=new JPanel();
    for (Component a:elementsIn) inputElements.add(a);
    for (Component a:results)resultElements.add(a);
    for (Component a:elementsListen) a.setPreferredSize(new Dimension(100,40));
    for (Component a:elementsListen) buttonElements.add(a);
    String columns[]={"Code","Type","Amount","Tax","S & H","delete"};
    model=new DefaultTableModel();
    for (String column:columns)  model.addColumn(column);
    table=new JTable(model);
    TableHandler tableh = new TableHandler(this, omanager);
    table.getSelectionModel().addListSelectionListener(tableh);
    DeleteHandler deleteh = new DeleteHandler(this, omanager);
    ButtonColumn buttonColumn=new ButtonColumn(table,deleteh,5);
    buttonColumn.setMnemonic(KeyEvent.VK_D);
    tableElements.add(new JScrollPane(table));
    this.getContentPane().setLayout(new GridLayout(1,2));
    JPanel pInput=new JPanel(new GridLayout(2,1));
    pInput.setBorder(new EmptyBorder(10,10,10,10));
    pInput.add(inputElements);
    JPanel pRes=new JPanel(new GridLayout(2,1));
    pRes.add(buttonElements);
    pRes.add(resultElements);
    pInput.add(pRes);
    Font myFont=new Font("Serif",Font.BOLD,30);
    lblTotal.setFont(myFont);
    lblTotalValue.setFont(myFont);
    lblTotal.setVisible(false);
    lblTotalValue.setVisible(false);
    lblTotalValue.setForeground(Color.GREEN);
    updateButton.setVisible(false);
    this.getContentPane().add(pInput);
    this.getContentPane().add(tableElements);
    this.setVisible(true);
    orderSH.setEnabled(false);
    taxOrder.setEnabled(true);
  }
  public void cleanFields() {
    orderAmount.setText("");
    taxOrder.setText("");
    orderSH.setText("");
  }
  public void setTotalValue(String msg) {
    lblTotalValue.setText(msg);
  }
  public void setVisibleTotalValue(boolean aux) {
    lblTotalValue.setVisible(aux);
  }
  public String getOrderType() {
    return (String)orderType.getSelectedItem();
  }
  public JComboBox<String> getCmbType(){
    return orderType;
  }
  public String getOrderAmount() {
    if (orderAmount.getText().trim().length()==0) return "0.0";
    return orderAmount.getText();
  }
  public String getTax() {
    if (taxOrder.getText().trim().length()==0) return "0.0";
    return taxOrder.getText();
  }
  public String getSH() {
    if (orderSH.getText().trim().length()==0) return "0.0";
    return orderSH.getText();
  }
  public JTextField getTxtAmount() {
    return orderAmount;
  }
  public JTextField getTxtTax() {
    return taxOrder;
  }
  public JTextField getTxtSH() {
    return orderSH;
  }
  public void setAmount(String aux) {
    orderAmount.setText(aux);
  }
  public void setTax(String aux) {
    taxOrder.setText(aux);
  }
  public void setSH(String aux) {
    orderSH.setText(aux);
  }
  public DefaultTableModel getModel() {
    return model;
  }
  public JTable getTable() {
    return table;
  }
  public void updateRow(Object[] obj, int row) {
    for (int i = 0; i< obj.length; i++) table.setValueAt(obj[i],row,i);
  }
  public JButton getUpdateButton() {
    return updateButton;
  }
}


