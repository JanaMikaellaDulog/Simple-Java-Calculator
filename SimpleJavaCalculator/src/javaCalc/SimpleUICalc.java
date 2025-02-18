package javaCalc;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.awt.GridLayout;

public class SimpleUICalc extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private CustomRoundTextField textfield;
	private CustomButtons[] numButtons = new CustomButtons[10];
	private CustomButtons[] funcButtons = new CustomButtons[7];
	private CustomButtons addButton, subButton, mulButton, divButton,
	delButton, clrButton, equButton, negButton, decButton;
	private JPanel panel;
	
	private double num1 = 0, num2 = 0, result = 0;
	private char operator;
	
	private Font viewFont;
	private Font buttonFont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleUICalc frame = new SimpleUICalc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimpleUICalc() {
		//surround with try catch, so that when it doesn't load, there is a back up font
		try {
            viewFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Poppins-Light.ttf")).deriveFont(42f);
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Poppins-Light.ttf")).deriveFont(20f);
        } 
		catch (FontFormatException | IOException e) {
            e.printStackTrace(); // Handle font loading error
            viewFont = new Font("Arial", Font.PLAIN, 20);  // Fallback font
            buttonFont = new Font("Arial", Font.PLAIN, 18);
        }
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Simple Java Calculator");
		setBounds(100, 100, 390, 600);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		//TextField for Results
		textfield = new CustomRoundTextField();
		textfield.setEditable(false);
		textfield.setHorizontalAlignment(SwingConstants.RIGHT);
		textfield.setFont(viewFont);
		textfield.setCaretPosition(textfield.getText().length());
		textfield.setPadding(new Insets(0, 0, 0, 10));
		textfield.setBackground(new Color(217, 217, 217));
		textfield.setBorderColor(new Color(217, 217, 217));
		textfield.setBorderRadius(30);
		textfield.setFocusable(true);
		textfield.setBounds(18, 22, 341, 95);
		contentPane.add(textfield);
		
		
		//Adding texts and Icons to function buttons
		addButton = new CustomButtons("+");
		subButton = new CustomButtons("-");
		mulButton = new CustomButtons("×");
		divButton = new CustomButtons("÷");
		equButton = new CustomButtons("=");
		decButton = new CustomButtons(".");
		negButton = new CustomButtons("+/-");
		clrButton = new CustomButtons("C");
		Icon del = new ImageIcon("src/delete.png");
		delButton = new CustomButtons(del);
		
		equButton.addActionListener(this);
		equButton.setFocusable(false);
		equButton.setButtonBackground(new Color(240, 69, 109)); 
		equButton.setHoverColor(new Color(138, 39, 62));  
		equButton.setBorderRadius(20);  
		equButton.setBorderColor(new Color(240, 69, 109));  
		equButton.setForeground(Color.black);  
		equButton.setFont(buttonFont);  
		
		decButton.addActionListener(this);
		decButton.setFocusable(false);
		decButton.setButtonBackground(new Color(240, 69, 109)); 
		decButton.setHoverColor(new Color(138, 39, 62));  
		decButton.setBorderRadius(20);  
		decButton.setBorderColor(new Color(240, 69, 109));  
		decButton.setForeground(Color.black);  
		decButton.setFont(buttonFont);  
		
		//Make funcButton Array get the value of the buttons
		funcButtons[0] = addButton;
		funcButtons[1] = subButton;
		funcButtons[2] = mulButton;
		funcButtons[3] = divButton;
		funcButtons[4] = negButton;
		funcButtons[5] = clrButton;
		funcButtons[6] = delButton;
		
		
		
		
		for (int i=0; i<7; i++) {
			funcButtons[i].addActionListener(this);
			funcButtons[i].setFocusable(false);
			funcButtons[i].setButtonBackground(new Color(255, 125, 69)); 
			funcButtons[i].setHoverColor(new Color(153, 74, 41));  
			funcButtons[i].setBorderRadius(20);  
			funcButtons[i].setBorderColor(new Color(255, 125, 69));  
			funcButtons[i].setForeground(Color.black);  
			funcButtons[i].setFont(buttonFont);  
		}
		
		for (int i=0; i<10; i++) {
			numButtons[i] = new CustomButtons(String.valueOf(i));
			numButtons[i].addActionListener(this);
			numButtons[i].setFocusable(false);
			numButtons[i].setButtonBackground(new Color(240, 69, 109)); 
			numButtons[i].setHoverColor(new Color(138, 39, 62));  
			numButtons[i].setBorderRadius(20);  
			numButtons[i].setBorderColor(new Color(240, 69, 109));  
			numButtons[i].setForeground(Color.black);  
			numButtons[i].setFont(buttonFont);  
		}
		 
		
		//Placing buttons in contentpane
		negButton.setBounds(105, 140, 78, 74);
		contentPane.add(negButton);
		
		delButton.setBounds(192, 140, 78, 74);
		contentPane.add(delButton);
		
		clrButton.setBounds(279, 140, 78, 74);
		contentPane.add(clrButton);
		
		
		
		panel = new JPanel();
		panel.setBounds(18, 223, 341, 316);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		
		//Placement of buttons
		panel.add(numButtons[7]);
		panel.add(numButtons[8]);
		panel.add(numButtons[9]);
		panel.add(divButton);
		
		panel.add(numButtons[4]);
		panel.add(numButtons[5]);
		panel.add(numButtons[6]);
		panel.add(mulButton);
		
		panel.add(numButtons[1]);
		panel.add(numButtons[2]);
		panel.add(numButtons[3]);
		panel.add(subButton);
		
		panel.add(numButtons[0]);
		panel.add(decButton);
		panel.add(equButton);
		panel.add(addButton);
		
		
		
		
	}

////////////////////////////// EVENT HANDLING /////////////////////////	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i<10; i++) {
			//Number Buttons 
			//when clicked, will show value of i at textfield converted into string
			if (e.getSource() == numButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
				
			}
		}
			//Operator Buttons
			if (e.getSource() == addButton) {
				if (!textfield.getText().contains(" + ")) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '+';
				textfield.setText(textfield.getText() + " + ");
				}
			}
			if (e.getSource() == subButton) {
				if (!textfield.getText().contains(" - ")) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '-';
				textfield.setText(textfield.getText() + " - ");
				}
			}
			if (e.getSource() == mulButton) {
				if (!textfield.getText().contains(" × ")) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '*';
				textfield.setText(textfield.getText() + " × ");
				}
			}
			if (e.getSource() == divButton) {
				if (!textfield.getText().contains(" ÷ ")) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '/';
				textfield.setText(textfield.getText() + " ÷ ");
				}
			}
			//Equals and handle operators
			if (e.getSource() == equButton) {
				//Splits num1, operator and num2 where there is a space; ex. 5 + 3; "5", "+", "3"
				String [] parts = textfield.getText().split(" ");
				
				//Checks if the split resulted in fewer than three parts, doesnt proceed further
				if (parts.length < 3) return; 
				
				//Parts[2] refers as 3rd element after split
				//Reads as num2 is 3rd element in the array
				num2 = Double.parseDouble(parts[2]);
				
				switch(operator) {
				case '+':
					result = num1 + num2;
					break;
					
				case '-':
					result = num1 - num2;
					break;
					
				case '*':
					result = num1 * num2;
					break;
				
				case '/':
					if (num2 != 0) {
						result = num1 / num2;
						break;
					}
					//If num2 is 0, it shows error
					else
						textfield.setText("Error");
					
				}
				//Shows result and result becomes num1 for continuous operations
				textfield.setText(String.valueOf(result));
				num1 = result;
			}
			
			if (e.getSource() == clrButton) {
				textfield.setText("");
			}
			
			//Decimal
			if (e.getSource() == decButton) {
				String currentText = textfield.getText();
				
				if (currentText.contains(" + ") || currentText.contains(" - ") || currentText.contains(" × ") || currentText.contains(" ÷ ")) {
					//Handling num2: if num2 is entered after the operator, make it able to get decimal
	                String[] parts = currentText.split(" ");
	                if (parts.length == 3 && !parts[2].contains(".")) {  // If num2 is entered and handles if there is already a decimal
	                    textfield.setText(parts[0] + " " + parts[1] + " " + parts[2] + "."); // Update text field with negated num2
	                }
				}
	                else {
					
					if (!currentText.contains(".")) {
						textfield.setText(currentText + ".");
					}
				}
			}
			
			
			if (e.getSource() == delButton) {
				String string = textfield.getText();
				//Clears textfield
				textfield.setText("");
				//Loop that reads each character of the string
				//Will stop one character before the last one in the string
				for (int i=0; i<string.length()-1; i++) {
					//The charAt(i) method extracts the character at index i of the string, when looping, it will 
					//rewrite the indexes until it reaches the condition, removing 1 character off the whole string
					textfield.setText(textfield.getText() + string.charAt(i));
				}
				
			}
			
			 //Negative num button
		    if (e.getSource() == negButton) {
		        String currentText = textfield.getText();

		        try {
		            //Check if the input is before the operator (num1)
		            if (currentText.contains(" + ") || currentText.contains(" - ") || currentText.contains(" × ") || currentText.contains(" ÷ ")) {
		                //Handling num2: if num2 is entered after the operator, make it negative
		                String[] parts = currentText.split(" ");
		                if (parts.length == 3) {  // If num2 is entered
		                    num2 = Double.parseDouble(parts[2]);
		                    num2 = -num2;  // Negate num2
		                    textfield.setText(parts[0] + " " + parts[1] + " " + num2); // Update text field with negated num2
		                }
		            } else {
		                //If no operator is entered yet, negate num1
		                double num = Double.parseDouble(currentText);
		                num = -num;  //Negate the current number
		                textfield.setText(String.valueOf(num));
		            }
		        } catch (NumberFormatException num) {
		            textfield.setText("No num entered");
		        }
		    }
		
	}
}
