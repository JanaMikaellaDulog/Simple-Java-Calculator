package javaCalc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public class CustomButtons extends JButton {

	/**
	 * 
	 */

	
	private static final long serialVersionUID = 1L;
	private int borderRadius;  // Corner radius field
    private Color hoverColor;  // Hover background color
    private Color originalBackground;  // Original background color
    private Color borderColor;  // Border color
	
	
		public CustomButtons(String text) {
			super(text);
	        this.originalBackground = Color.gray; // Default background color
	        this.hoverColor = Color.DARK_GRAY; // Default hover background color
	        this.borderColor = Color.BLACK; // Default border color
	        
	        setUI(new javax.swing.plaf.basic.BasicButtonUI());
	        setBackground(originalBackground); // Set initial background color
	        setForeground(Color.BLACK); // Set text color
	        setOpaque(false); // Ensure transparency for rounded corners
			
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					setBackground(hoverColor); //change to hover color when mouse enters button
				}
				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(originalBackground); //change back to base color
				}
	
			});
		}
		
		public CustomButtons(Icon icon) {
			super(icon);
			this.originalBackground = Color.gray; // Default background color
	        this.hoverColor = Color.DARK_GRAY; // Default hover background color
	        this.borderColor = Color.BLACK; // Default border color
	        
	        setUI(new javax.swing.plaf.basic.BasicButtonUI());
	        setBackground(originalBackground); // Set initial background color
	        setForeground(Color.BLACK); // Set text color
	        setOpaque(false); // Ensure transparency for rounded corners
			
			
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					setBackground(hoverColor); //change to hover color when mouse enters button
				}
				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(originalBackground); //change back to base color
				}
	
			});
		}
		
		  // Method to set the background color (from outside the class)
	    public void setButtonBackground(Color color) {
	        setBackground(color);
	        this.originalBackground = color; // Store the original background color
	    }

	    // Method to set hover background color (from outside the class)
	    public void setHoverColor(Color color) {
	        this.hoverColor = color;
	    }

	    // Method to set border color (from outside the class)
	    public void setBorderColor(Color color) {
	        this.borderColor = color;
	    }

	    // Method to set corner radius (from outside the class)
	    public void setBorderRadius(int radius) {
	        this.borderRadius = radius;
	        repaint(); // Redraw the button with the new radius
	    }

	    
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); //make sure that original graphics// texts is loaded correctly
			
			//create graphics2d to create border
			Graphics2D g2d = (Graphics2D) g; //convert g from graphics class to graphics2d for smoother graphics
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// Determine button state color
	        if (getModel().isPressed()) {
	            g2d.setColor(originalBackground); // Pressed state color
	        } else {
	            g2d.setColor(getBackground());
	        }
			
			//make it so that you can change it in main class
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
			
			//make foreground visible and not blend with background
			g2d.setColor(getForeground());
			super.paintComponent(g2d);

			//create the thickness of the radius
			//set border color
			g2d.setColor(borderColor);
			g2d.setStroke(new BasicStroke (1));
			g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
			
			g2d.dispose();
			
	
		}
}		
		
		
		

	

