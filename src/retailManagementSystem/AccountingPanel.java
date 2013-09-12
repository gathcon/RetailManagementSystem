package retailManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class AccountingPanel extends JPanel implements ActionListener{

	private Database database;
	
	private JTable tableOfAccounts;
	
	private DefaultTableModel model;
	
	private JButton graphButton;
	
	private JPanel panel;	
	private JPanel graphPanel;
	private JPanel newPanel;
	
	private JLabel accountListLabel;
	
	private double totalOrderCost;
	private double totalInvoiceCost;
	private DecimalFormat df = new DecimalFormat("#.##"); 
		
	private static final int maxCost = 25000;
	private static final int border = 65;
	private static final int yHatchCount = 5;
	private static final int graphPointWidth = 12;
	
	private void createConstraint(JPanel panel, JComponent component, int gridx, int gridy, int width, int height, int ipadx, int ipady,
			int top, int left, int bottom, int right, double weightx, double weighty, int anchor, int fill){
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
			constraints.anchor = anchor;
			constraints.fill = fill;
			panel.add(component, constraints);

	}
	
	
	public void buildPanel(JPanel panel, final Database db){
		
		this.panel = panel;
		this.database = db;

		
		
		tableOfAccounts = new JTable();// JTABLE code
		
		model = new DefaultTableModel();
		tableOfAccounts.setEnabled(false);
		
		tableOfAccounts.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane(tableOfAccounts);
		refreshAccount();
 		
 		graphButton = new JButton("Show Graph");
 		graphButton.setVisible(true);
 		graphButton.addActionListener(this);
 		
 		graphPanel = new JPanel();				
 		graphPanel = new graph();
 		graphPanel.setVisible(false);
 		graphPanel.setBackground(Color.WHITE);
 		 		
 		newPanel = new JPanel();
 		
 		panel.setLayout(new GridBagLayout());
 		
 		newPanel.setLayout(new GridBagLayout());
 		
 		accountListLabel = new JLabel("Account Control", SwingConstants.CENTER); 
 		accountListLabel.setOpaque(true);
 		accountListLabel.setBackground(new Color(0,51,102)); 
 		accountListLabel.setForeground(Color.WHITE);
 		accountListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
 		accountListLabel.setPreferredSize(new Dimension(150, 35)); 

 		createConstraint(newPanel, accountListLabel,					0, 0, 5, 1, 0, 0, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(newPanel, tableOfAccounts.getTableHeader(),	0, 1, 5, 1, 0, 0, 2, 20, 2, 20, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(newPanel, scrollPane, 							0, 2, 5, 1, 0, 0, 2, 20, 2, 20, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(panel, newPanel,		0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(panel, graphPanel,		0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(panel, graphButton,	0, 1, 1, 1, 0, 0, 0, 0, 20, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Show Graph")) {
			newPanel.setVisible(false);
			graphPanel.setVisible(true);
			panel.setBackground(Color.WHITE);
			graphButton.setText("Return to List");
		}
		
		else if (e.getActionCommand().equals("Return to List")) {
			newPanel.setVisible(true);
			graphPanel.setVisible(false);
			panel.setBackground(new Color(238, 238, 238));
			graphButton.setText("Show Graph");
		}
		
	}
	
	
	
	class graph extends JPanel{
        
    	protected void paintComponent(Graphics g) { 
    		
    		
    		
    		int sumInvoices = (int) Math.round(database.sumInvoiceCosts());
    		int sumOrders = (int) Math.round(database.sumOrderCosts());
    		int height = getHeight() - border*2;
    		int width = getWidth() - border*2;
    		
    		
    		
    		ArrayList<Order> orders = database.getOrders();
    	    int noOfOrders = orders.size();
    		
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
            
            
            // Draw Invoice bar
            if(sumOrders > sumInvoices){
    		double maxCost = sumOrders + 5000;
            double scale = (double)(getHeight() - 2*border)/maxCost;
            g2.setPaint(Color.green.darker());
            double a1 = border + (width/3 - 50);
            double b1 = getHeight() - border  - scale*sumInvoices;
            int a2 = 100;
            double b2 = scale*sumInvoices;
            g2.fill(new Rectangle2D.Double(a1, b1, a2, b2));
            
            g2.setPaint(Color.black);
            g2.fill(new Rectangle2D.Double(border, getHeight() - border  - scale*sumInvoices, graphPointWidth, 1));
            g2.fill(new Rectangle2D.Double(border, getHeight() - border  - scale*sumOrders, graphPointWidth, 1));
            
            String invoiceValue = String.valueOf(sumInvoices);
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(invoiceValue, border - fm.stringWidth(invoiceValue), (int) (getHeight() - border  - scale*sumInvoices));
            
            String orderValue = String.valueOf(sumOrders);
            g2.drawString(orderValue, border - fm.stringWidth(orderValue), (int) (getHeight() - border  - scale*sumOrders));
            
            
            // Draw Order bar
            g2.setPaint(Color.red.darker());
            double x1 = border + (width*2/3 - 50);
            double y1 = getHeight() - border - scale*sumOrders;
            int x2 = 100;
            double y2 = scale*sumOrders;
            g2.fill(new Rectangle2D.Double(x1, y1, x2, y2));

            }
            
            else if(sumOrders < sumInvoices){
            	double maxCost = sumInvoices + 5000;
                double scale = (double)(getHeight() - 2*border)/maxCost;
                g2.setPaint(Color.green.darker());
                double a1 = border + (width/3 - 50);
                double b1 = getHeight() - border  - scale*sumInvoices;
                int a2 = 100;
                double b2 = scale*sumInvoices;
                g2.fill(new Rectangle2D.Double(a1, b1, a2, b2));
                
                g2.setPaint(Color.black);
                g2.fill(new Rectangle2D.Double(border, getHeight() - border  - scale*sumInvoices, graphPointWidth, 1));
                g2.fill(new Rectangle2D.Double(border, getHeight() - border  - scale*sumOrders, graphPointWidth, 1));
                
                String invoiceValue = String.valueOf(sumInvoices);
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(invoiceValue, border - fm.stringWidth(invoiceValue), (int) (getHeight() - border  - scale*sumInvoices));
                
                String orderValue = String.valueOf(sumOrders);
                g2.drawString(orderValue, border - fm.stringWidth(orderValue), (int) (getHeight() - border  - scale*sumOrders));
                
                // Draw Order bar
                g2.setPaint(Color.red.darker());
                double x1 = border + (width*2/3 - 50);
                double y1 = getHeight() - border - scale*sumOrders;
                int x2 = 100;
                double y2 = scale*sumOrders;
                g2.fill(new Rectangle2D.Double(x1, y1, x2, y2));	
            }
           
        }
    }

	
	
	public void refreshAccount(){
	
		model.setRowCount(0);
		
		totalOrderCost = 0;
		totalInvoiceCost = 0;
		  
		model.setColumnIdentifiers(new String [] {"Order/Invoice ID", "Supplier/Customer Name","Debit","Credit"});
		model.addRow(new String[]{"<html><b>Order ID</html></b>","<html><b>Supplier Name</html></b>",null});
		
		for(Order p: database.getOrders()){
			model.addRow(new String[]{p.getOrderID(),p.getSupplier().getSupplierName(),p.getOrderCost()});
			
			totalOrderCost = Double.valueOf(df.format(totalOrderCost+Double.parseDouble(p.getOrderCost())));
		}
		String totalOrderCostS = String.format("%.2f", totalOrderCost);
		model.addRow(new String[]{"<html><strong>Total Order Cost</strong></html>",null,"<html><b>" + totalOrderCostS+ "</b></html>"});
		model.addRow(new String[]{});
		model.addRow(new String[]{"<html><b>Invoice ID</html></b>","<html><b>Customer Name</html></b>",null,null});
		for(Invoice p: database.getInvoices()){
			model.addRow(new String[]{p.getInvoiceID(),p.getCustomer().getCustomerName(),null,p.getInvoiceCost()});
			
			totalInvoiceCost = Double.valueOf(df.format(totalInvoiceCost+Double.parseDouble(p.getInvoiceCost())));
		}
		String totalInvoiceCostS = String.format("%.2f", totalInvoiceCost);
		model.addRow(new String[]{"<html><b>Total Invoice Cost</b></html>",null,null,"<html><b>" + totalInvoiceCostS+ "</b></html>"});
		Double total = Double.valueOf(df.format(totalInvoiceCost - totalOrderCost));
		String totalS = String.format("%.2f", total);
		model.addRow(new String[]{});
		if(total>0){
			model.addRow(new String[]{"<html><b style=\"color:#00FF00\">Total Profit</b></html>",null,"<html><b style=\"color:#00FF00\">" + totalS + "</b></html>"});
		}
		else{
			model.addRow(new String[]{"<html><b style=\"color:#FF0000\">Total Loss</b></html>",null,"<html><b style=\"color:#FF0000\">" + totalS + "</b></html>"});
		}
  } 


}

