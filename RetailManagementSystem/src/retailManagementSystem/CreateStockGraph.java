package retailManagementSystem;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
 
import javax.swing.*;
 
public class CreateStockGraph extends JPanel {
     
    private static final int maxStock = 150;
    private static final int border = 50;
    private static final int yHatchCount = 15;
    private static final int graphPointWidth = 12;
    private int width = 800;
    private int height = 550;
    
    
    int [] stockLevels  = {40, 50, 60, 40, 50, 30, 40, 50, 60, 70, 50, 60};

    int average1 = stockLevels[0];
    int average2 = (stockLevels[0]+stockLevels[1])/2;
    int average3 = (stockLevels[0]+stockLevels[1]+stockLevels[2])/3;
    int average4 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3])/4;
    int average5 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4])/5;
    int average6 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4]+stockLevels[5])/6;
    int average7 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4]+stockLevels[5]+stockLevels[6])/7;
    int average8 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4]+stockLevels[5]+stockLevels[6]+stockLevels[7])/8;
    int average9 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4]+stockLevels[5]+stockLevels[6]+stockLevels[7]+stockLevels[8])/9;
    int average10 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4]+stockLevels[5]+stockLevels[6]+stockLevels[7]+stockLevels[8]+stockLevels[9])/10;
    int average11 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4]+stockLevels[5]+stockLevels[6]+stockLevels[7]+stockLevels[8]+stockLevels[9]+stockLevels[10])/11;
    int average12 = (stockLevels[0]+stockLevels[1]+stockLevels[2]+stockLevels[3]+stockLevels[4]+stockLevels[5]+stockLevels[6]+stockLevels[7]+stockLevels[8]+stockLevels[9]+stockLevels[10]+stockLevels[11])/12;
        
    double [] predictedStockLevels = {average1, average2, average3, average4, average5, average6, average7, average8, average9, average10, average11, average12};
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw x and y axis
        g2.draw(new Line2D.Double(border, border, border, height-border));
        g2.draw(new Line2D.Double(border, height-border, width-border, height-border));
        
         
        for (int i = 0; i < yHatchCount; i++) {
             int x0 = border;
             int x1 = graphPointWidth + border;
             int y0 = height - (((i) * (height - border * 2)) / yHatchCount + border);
             int y1 = y0;
             g2.drawLine(x0, y0, x1, y1);
             FontMetrics fm = g2.getFontMetrics();
             String [] values = {"", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150"};
             g2.drawString(values[i], x0 - fm.stringWidth(values[i]), y0 + (fm.getAscent() / 2));
             
          }
         
     // and for x axis
          for (int i = 0; i < 12; i++) { 
             int x0 = (i) * (width - border * 2) / (12 - 1) + border;
             int x1 = x0;
             int y0 = height - border;
             int y1 = y0 - graphPointWidth;
             g2.drawLine(x0, y0, x1, y1);
             FontMetrics fm = g2.getFontMetrics();
             String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
             g2.drawString(months[i], x0 - (fm.stringWidth(months[i]) / 2), y0 + fm.getAscent());
          }
         
        // Draw labels
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent(); 
         
        // Ordinate label.
        String Label = "STOCK";
        float sy = border + ((height - 2*border) - Label.length()*sh)/2 + lm.getAscent();
        
        for(int i = 0; i < Label.length(); i++) {
            String letter = String.valueOf(Label.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (border - sw)/4;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
         
        // Abcissa label.
        Label = "MONTHS";
        sy = height - border + (border - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(Label, frc).getWidth();
        float sx = (width - sw)/2;
        g2.drawString(Label, sx, sy);
         
        // Draw stock level lines.
        double xInc = (double)(width - 2*border)/(12-1);
        double scale = (double)(height - 2*border)/maxStock;
        g2.setPaint(Color.green.darker());
        for(int i = 0; i < stockLevels.length-1; i++) {
            double x1 = border + i*xInc;
            double y1 = height - border - scale*stockLevels[i];
            double x2 = border + (i+1)*xInc;
            double y2 = height - border - scale*stockLevels[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
         
        // Draw predicted stock level lines.
        double xInc2 = (double)(width - 2*border)/(12-1);
        double scale2 = (double)(height - 2*border)/maxStock;
        g2.setPaint(Color.blue.darker());
        for(int i = 0; i < predictedStockLevels.length-1; i++) {
            double a1 = border + i*xInc2;
            double b1 = height - border - scale*predictedStockLevels[i];
            double a2 = border + (i+1)*xInc2;
            double b2 = height - border - scale2*predictedStockLevels[i+1];
            g2.draw(new Line2D.Double(a1, b1, a2, b2));
        }
         
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < stockLevels.length; i++) {
            double x = border + i*xInc;
            double y = height - border - scale*stockLevels[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
         
        // Mark predicted data points.
        g2.setPaint(Color.black);
        for(int i = 0; i < predictedStockLevels.length; i++) {
            double x = border + i*xInc2;
            double y = height - border - scale2*predictedStockLevels[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
       
    }
        
    public CreateStockGraph(){
    
    }
    
    public void buildPanel(JPanel graphPanel, JPanel panel, Database database){
        
    }
	
}


    
    
     
     
     

 

