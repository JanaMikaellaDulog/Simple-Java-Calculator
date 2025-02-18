package javaCalc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class CustomRoundTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int borderRadius;
	private Color borderColor;
	private Insets padding;
	
	
		public CustomRoundTextField() {
			//call the parent class jtextfield
			super();
			setOpaque(false); //non transparent background
			setBorder(null);
			padding = new Insets(0,0,0,0); // Default padding (top, left, bottom, right
		}
		
		public void setBorderRadius(int radius) {
			this.borderRadius = radius;
			repaint();
		}
		
		public void setBorderColor(Color color) {
			this.borderColor = color;
			repaint();
		}
		
		public void setPadding(Insets padding) {
			this.padding = padding;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); //make sure that original graphics// texts is loaded correctly
			
			//create graphics2d to create border
			Graphics2D g2d = (Graphics2D) g; //convert g from graphics class to graphics2d for smoother graphics
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			//make it so that you can change it in main class
			g2d.setColor(getBackground());
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
			
			//make foreground visible and not blend with background
			g2d.setColor(getForeground());
			super.paintComponent(g2d);

			//create the thickness of the radius
			//set border color
			g2d.setColor(borderColor);
			g2d.setStroke(new BasicStroke (1));
			g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
		}
		
		// Override getInsets to apply custom padding
	    @Override
	    public Insets getInsets() {
	        return padding;
	    }

	
}
