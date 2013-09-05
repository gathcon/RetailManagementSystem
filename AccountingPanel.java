package retailManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class AccountingPanel extends JPanel implements ActionListener{

	private Database database;
	
	//comment

	//testing git hub here
	
	private JTable tableOfAccounts;
	private DefaultTableModel model;
	private JButton accountGraphButton;
	private JButton graphBackButton;
	private JPanel graphPanel;
	private JPanel newPanel;
	
	private double totalOrderCost;
	private double totalInvoiceCost;
	private DecimalFormat df = new DecimalFormat("#.##"); 
		
	private static final int maxCost = 25000;
	private static final int border = 50;
	private static final int yHatchCount = 5;
	private static final int graphPointWidth = 12;
	
//	DefaultTableModel model = new DefaultTableModel() {
//
//	    public boolean isCellEditable(int row, int column) {
//	    	return false;
//	    }
//	 
//	}; 
	
	private void createConstraint(JPanel panel, JComponent component,
			int gridx, int gridy, int width, int height, int ipadx, int ipady,
			int top, int left, int bottom, int right, double weightx,
			double weighty) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.ipadx = ipadx;
		constraints.ipady = ipady;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.insets = new Insets(top, left, bottom, right);
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.fill = GridBagConstraints.BOTH;
		panel.add(component, constraints);
	}
	
	
	public void buildPanel(JPanel panel, final Database db){
		this.database = db;

		
		
		tableOfAccounts = new JTable();// JTABLE code
		
		model = new DefaultTableModel();
		tableOfAccounts.setEnabled(false);
		
		tableOfAccounts.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane(tableOfAccounts);
		refreshAccount();
		

		
		graphPanel = new JPanel();
 		newPanel = new JPanel();
						
 		graphPanel = new graph();
 		panel.add(graphPanel);
 		graphPanel.setVisible(false);
 		graphPanel.setBackground(Color.WHITE);
 					
 		accountGraphButton = new JButton("Graph");
 		graphBackButton = new JButton("Back");
 		accountGraphButton.setVisible(true);
 		graphBackButton.setVisible(true);
 		accountGraphButton.addActionListener(this);
 		graphBackButton.addActionListener(this);
 					
 		newPanel.add(accountGraphButton);
 		graphPanel.add(graphBackButton);
 		panel.setLayout(new GridBagLayout());	
 		panel.setLayout(new GridBagLayout());
 		newPanel.setLayout(new GridBagLayout());

		createConstraint(newPanel, tableOfAccounts.getTableHeader(),	0, 0, 5, 1, 0, 0, 2, 2, 2, 2, 1, 1);
		createConstraint(newPanel, scrollPane, 						0, 1, 5, 1, 0, 0, 2, 2, 2, 2, 1, 7);
		
		createConstraint(panel, newPanel,		0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1);
		createConstraint(panel, graphPanel,	0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Graph")) {
			newPanel.setVisible(false);
			graphPanel.setVisible(true);
			System.out.println("works");
			
		}
		
		else if(e.getActionCommand().equals("Back")) {
			newPanel.setVisible(true);
			graphPanel.setVisible(false);
			System.out.println("works");
			
		}
		
	}
	
class graph extends JPanel{
        
    	protected void paintComponent(Graphics g) { 
    		
    		double sumInvoices = sumInvoiceCosts();
    		double sumOrders = sumOrderCosts();
    		int height = getHeight() - border*2;
    		int width = getWidth() - border*2;
    		
    		
    		
    		ArrayList<Order> orders = database.getOrders();
    	    int noOfOrders = orders.size();
    	   
//    	    String cost1 = orders.get(0).getOrderCost();
//    	    String cost2 = orders.get(1).getOrderCost();
//    	    String cost3 = orders.get(2).getOrderCost();
//    	    String cost4 = orders.get(3).getOrderCost();
//    	    String cost5 = orders.get(4).getOrderCost();
//    	    String cost6 = orders.get(5).getOrderCost();
//    	    String cost7 = orders.get(6).getOrderCost();
//    	    String cost8 = orders.get(7).getOrderCost();
//    	    String cost9 = orders.get(8).getOrderCost();
//    	    String cost10 = orders.get(9).getOrderCost();
//    	    
//    	    int [] orderCosts  = {Integer.parseInt(cost1), Integer.parseInt(cost2), Integer.parseInt(cost3), Integer.parseInt(cost4),
//    	    		Integer.parseInt(cost5), Integer.parseInt(cost6), Integer.parseInt(cost7), Integer.parseInt(cost8), Integer.parseInt(cost9),
//    	    		Integer.parseInt(cost10)};
    		
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw x and y axis
            g2.draw(new Line2D.Double(border, border, border, getHeight()-border));
            g2.draw(new Line2D.Double(border, getHeight()-border, getWidth()-border, getHeight()-border));
            
            // draw opaque grid
            g2.setColor(new Color(255, 0, 0, 64));
            g2.draw(new Line2D.Double(border, border, border, (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width/9), border, (border + width/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*2/9), border, (border + width*2/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*3/9), border, (border + width*3/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*4/9), border, (border + width*4/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*5/9), border, (border + width*5/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*6/9), border, (border + width*6/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*7/9), border, (border + width*7/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*8/9), border, (border + width*8/9), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width), border, (border + width), (getHeight()-border)));
            
            
            g2.draw(new Line2D.Double(border, border, getWidth() - border, border));
            g2.draw(new Line2D.Double(border, (border + height/25), getWidth() - border, (border + height/25)));
            g2.draw(new Line2D.Double(border, (border + height*2/25), getWidth() - border, (border + height*2/25)));
            g2.draw(new Line2D.Double(border, (border + height*3/25), getWidth() - border, (border + height*3/25)));
            g2.draw(new Line2D.Double(border, (border + height*4/25), getWidth() - border, (border + height*4/25)));
            g2.draw(new Line2D.Double(border, (border + height*5/25), getWidth() - border, (border + height*5/25)));
            g2.draw(new Line2D.Double(border, (border + height*6/25), getWidth() - border, (border + height*6/25)));
            g2.draw(new Line2D.Double(border, (border + height*7/25), getWidth() - border, (border + height*7/25)));
            g2.draw(new Line2D.Double(border, (border + height*8/25), getWidth() - border, (border + height*8/25)));
            g2.draw(new Line2D.Double(border, (border + height*9/25), getWidth() - border, (border + height*9/25)));
            g2.draw(new Line2D.Double(border, (border + height*10/25), getWidth() - border, (border + height*10/25)));
            g2.draw(new Line2D.Double(border, (border + height*11/25), getWidth() - border, (border + height*11/25)));
            g2.draw(new Line2D.Double(border, (border + height*12/25), getWidth() - border, (border + height*12/25)));
            g2.draw(new Line2D.Double(border, (border + height*13/25), getWidth() - border, (border + height*13/25)));
            g2.draw(new Line2D.Double(border, (border + height*14/25), getWidth() - border, (border + height*14/25)));
            g2.draw(new Line2D.Double(border, (border + height*15/25), getWidth() - border, (border + height*15/25)));
            g2.draw(new Line2D.Double(border, (border + height*16/25), getWidth() - border, (border + height*16/25)));
            g2.draw(new Line2D.Double(border, (border + height*17/25), getWidth() - border, (border + height*17/25)));
            g2.draw(new Line2D.Double(border, (border + height*18/25), getWidth() - border, (border + height*18/25)));
            g2.draw(new Line2D.Double(border, (border + height*19/25), getWidth() - border, (border + height*19/25)));
            g2.draw(new Line2D.Double(border, (border + height*20/25), getWidth() - border, (border + height*20/25)));
            g2.draw(new Line2D.Double(border, (border + height*21/25), getWidth() - border, (border + height*21/25)));
            g2.draw(new Line2D.Double(border, (border + height*22/25), getWidth() - border, (border + height*22/25)));
            g2.draw(new Line2D.Double(border, (border + height*23/25), getWidth() - border, (border + height*23/25)));
            g2.draw(new Line2D.Double(border, (border + height*24/25), getWidth() - border, (border + height*24/25)));
            g2.draw(new Line2D.Double(border, (border + height), getWidth() - border, (border + height)));
            
            
            g2.setPaint(Color.black);
            
            
             
            for (int i = 0; i < yHatchCount; i++) {
                 int x0 = border;
                 int x1 = graphPointWidth + border;
                 int y0 = getHeight() - (((i) * (getHeight() - border * 2)) / yHatchCount + border);
                 int y1 = y0;
                 g2.drawLine(x0, y0, x1, y1);
                 FontMetrics fm = g2.getFontMetrics();
                 String [] values = {"", "5000", "10000", "15000", "20000", ""};
                 g2.drawString(values[i], x0 - fm.stringWidth(values[i]), y0 + (fm.getAscent() / 2));
                 
              }
             
         // and for x axis
              for (int i = 0; i < 4; i++) { 
                 int x0 = (i) * (getWidth() - border * 2) / (4 - 1) + border;
                 int x1 = x0;
                 int y0 = getHeight() - border;
                 int y1 = y0 - graphPointWidth;
                 g2.drawLine(x0, y0, x1, y1);
                 FontMetrics fm = g2.getFontMetrics();
                 String [] months = {"", "Invoice Costs", "Order Costs", ""};
                 g2.drawString(months[i], x0 - (fm.stringWidth(months[i]) / 2), y0 + fm.getAscent());
              }
             
            // Draw labels
            Font font = g2.getFont();
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = font.getLineMetrics("0", frc);
            float sh = lm.getAscent() + lm.getDescent(); 
             
            // Ordinate label.
            String Label = "AMOUNT";
            float sy = getHeight() - height - Label.length()*sh + lm.getAscent();
            
            for(int i = 0; i < Label.length(); i++) {
                String letter = String.valueOf(Label.charAt(i));
                float sw = (float)font.getStringBounds(letter, frc).getWidth();
                float sx = (border - sw)/6;
                g2.drawString(letter, sx, sy);
                sy += sh;
            }
             
            // Abcissa label.
//            Label = "Number of Orders";
//            sy = getHeight() - border + (border - sh)/2 + lm.getAscent();
//            float sw = (float)font.getStringBounds(Label, frc).getWidth();
//            float sx = (getWidth() - sw)/2;
//            g2.drawString(Label, sx, sy);
            
            
            // legend predicted stock levels
//            Label = "Predicted Stock Levels";
//            g2.setPaint(Color.red.darker());
//            sy = getHeight() - (getHeight() - border/2);
//            float sx2 = getWidth() - (border*3);
//            g2.drawString(Label, sx1, sy);
            
            // Draw Invoice bar
            double scale = (double)(getHeight() - 2*border)/maxCost;
            g2.setPaint(Color.green.darker());
            double a1 = border + (width/3 - 50);
            double b1 = getHeight() - border - scale*sumInvoices;
            double a2 = 100;
            double b2 = scale*sumInvoices;
            g2.fill(new Rectangle2D.Double(a1, b1, a2, b2));
            
            // Draw Order bar
            g2.setPaint(Color.red.darker());
            double x1 = border + (width*2/3 - 50);
            double y1 = getHeight() - border - scale*sumOrders;
            double x2 = 100;
            double y2 = scale*sumOrders;
            g2.fill(new Rectangle2D.Double(x1, y1, x2, y2));
             
            // Draw predicted stock level lines.
//            double xInc2 = (double)(getWidth() - 2*border)/(12-1);
//            double scale2 = (double)(getHeight() - 2*border)/maxStock;
//            g2.setPaint(Color.red.darker());
//            for(int i = 0; i < 11; i++) {
//                double a1 = border + i*xInc2;
//                double b1 = getHeight() - border - scale*predictedStockLevels[i];
//                double a2 = border + (i+1)*xInc2;
//                double b2 = getHeight() - border - scale2*predictedStockLevels[i+1];
//                g2.draw(new Line2D.Double(a1, b1, a2, b2));
//            }
            
            
            
             
            // Mark data points.
//            g2.setPaint(Color.blue);
//            for(int i = 0; i < orderCosts.length; i++) {
//                double x = border + i*xInc;
//                double y = getHeight() - border - scale*orderCosts[i];
//                g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
//            }
             
            // Mark predicted data points.
//            g2.setPaint(Color.black);
//            for(int i = 0; i < 12; i++) {
//                double x = border + i*xInc2;
//                double y = getHeight() - border - scale2*predictedStockLevels[i];
//                g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
//            }
           
        }
    	
    	
    	
    	
    }


	public double sumOrderCosts(){

		ArrayList<Order> orders = database.getOrders();
    
		double sum = 0;
		for(int i = 0; i < orders.size(); i++){
			sum = sum + Double.parseDouble(orders.get(i).getOrderCost());
		}
		return sum;
   }
	
	public double sumInvoiceCosts(){
		ArrayList<Invoice> invoices = database.getInvoices();
    
		double sum = 0;
		for(int i = 0; i < invoices.size(); i++){
			sum = sum + Double.parseDouble(invoices.get(i).getInvoiceCost());
		}
		return sum;
   }


<<<<<<< HEAD
	public void refreshAccount(){
=======
public void refreshAccount(){

	    
>>>>>>> 5ca03a0fd8ac30b8ee6c3d0b7b147a617957adfd
	
	model.setRowCount(0);
	
	totalOrderCost = 0;
	totalInvoiceCost = 0;
	  
	model.setColumnIdentifiers(new String [] {"Order/Invoice ID", "Supplier/Customer Name","Debit","Credit"});
	model.addRow(new String[]{"<html><b>Order ID</html></b>","<html><b>Supplier Name</html></b>",null});
	
	for(Order p: database.getOrders()){
		model.addRow(new String[]{p.getOrderID(),p.getSupplier().getSupplierName(),p.getOrderCost()});
		
		totalOrderCost = Double.valueOf(df.format(totalOrderCost+Double.parseDouble(p.getOrderCost())));
	}
	String totalOrderCostS = Double.toString(totalOrderCost);
	model.addRow(new String[]{"<html><strong>Total Order Cost</strong></html>",null,"<html><b>" + totalOrderCostS+ "</b></html>"});
	model.addRow(new String[]{});
	model.addRow(new String[]{"<html><b>Invoice ID</html></b>","<html><b>Customer Name</html></b>",null,null});
	for(Invoice p: database.getInvoices()){
		model.addRow(new String[]{p.getInvoiceID(),p.getCustomer().getCustomerName(),null,p.getInvoiceCost()});
		
		totalInvoiceCost = Double.valueOf(df.format(totalInvoiceCost+Double.parseDouble(p.getInvoiceCost())));
	}
	String totalInvoiceCostS = Double.toString(totalInvoiceCost);
	model.addRow(new String[]{"<html><b>Total Invoice Cost</b></html>",null,null,"<html><b>" + totalInvoiceCostS+ "</b></html>"});
	Double total = Double.valueOf(df.format(totalInvoiceCost - totalOrderCost));
	String totalS = Double.toString(total);
	model.addRow(new String[]{});
	if(total>0){
		model.addRow(new String[]{"<html><b style=\"color:#00FF00\">Total Profit</b></html>",null,"<html><b style=\"color:#00FF00\">" + totalS + "</b></html>"});
	}
	else{
		model.addRow(new String[]{"<html><b style=\"color:#FF0000\">Total Loss</b></html>",null,"<html><b style=\"color:#FF0000\">" + totalS + "</b></html>"});
	}
  } 


}

