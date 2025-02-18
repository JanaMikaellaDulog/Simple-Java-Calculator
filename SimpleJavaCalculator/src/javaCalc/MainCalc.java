package javaCalc;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;


//------------------------------------------------ Dependencies ------------------------------------------------------//

public class MainCalc extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton[] numButtons = new JButton [10]; //create array of number of num buttons
	private JButton[] funcButtons = new JButton [9]; //create array of number of function buttons
	private JButton addButton, subButton, mulButton, divButton, 
	delButton, clrButton, negButton, decButton, equButton;
	private JTextField textField;
	
	// instantiate values
	Font myFont = new Font("Arial Black", Font.PLAIN, 35); 
	
	double num1 = 0, num2 = 0, result = 0;
	char operator;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainCalc frame = new MainCalc();
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
	public MainCalc() {
		setTitle("Java Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial Black", Font.PLAIN, 42));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setDisabledTextColor(Color.black);
		textField.setBorder(BorderFactory.createLineBorder(Color.black));
		textField.setBounds(10, 10, 346, 79);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("×");
		divButton = new JButton("÷");
		delButton = new JButton("<");
		clrButton = new JButton("C");
		negButton = new JButton("+/-");
		decButton = new JButton(".");
		equButton = new JButton("=");
		
		
		funcButtons[0] = addButton;
		funcButtons[1] = subButton;
		funcButtons[2] = mulButton;
		funcButtons[3] = divButton;
		funcButtons[4] = delButton;
		funcButtons[5] = clrButton;
		funcButtons[6] = negButton;
		funcButtons[7] = decButton;
		funcButtons[8] = equButton;
		
		
		for (int i = 0; i < 9; i++) {
		    funcButtons[i].addActionListener(this);
		    funcButtons[i].setFont(myFont);
		    funcButtons[i].setFocusable(false);
		}
		
		for (int i = 0; i < 10; i++) {
			numButtons[i] = new JButton(String.valueOf(i));
			numButtons[i].addActionListener(this);
			numButtons[i].setFont(myFont);
		    numButtons[i].setFocusable(false);
		}
		
		
		//Placing buttons in contentpane
		negButton.setBounds(99, 103, 79, 70);
		contentPane.add(negButton);
		
		delButton.setBounds(188, 103, 79, 70);
		contentPane.add(delButton);
		
		clrButton.setBounds(277, 103, 79, 70);
		contentPane.add(clrButton);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 183, 346, 302);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		
		
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
//-------------------------------------- ACTION PERFORMED EVENTS FOR BUTTONS -------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int i = 0; i<10; i++) {
			//Number Buttons 
			//when clicked, will show value of i at textfield converted into string
			if (e.getSource() == numButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(i)));
				
			}
		}
			//Function Buttons
			//Decimal
			if (e.getSource() == decButton) {
				if (!textField.getText().contains(".")) {
				textField.setText(textField.getText().concat("."));
				}
			}
			//Operator Buttons
			if (e.getSource() == addButton) {
				if (!textField.getText().contains(" + ")) {
				num1 = Double.parseDouble(textField.getText());
				operator = '+';
				textField.setText(textField.getText() + " + ");
				}
			}
			if (e.getSource() == subButton) {
				if (!textField.getText().contains(" - ")) {
				num1 = Double.parseDouble(textField.getText());
				operator = '-';
				textField.setText(textField.getText() + " - ");
				}
			}
			if (e.getSource() == mulButton) {
				if (!textField.getText().contains(" × ")) {
				num1 = Double.parseDouble(textField.getText());
				operator = '*';
				textField.setText(textField.getText() + " × ");
				}
			}
			if (e.getSource() == divButton) {
				if (!textField.getText().contains(" ÷ ")) {
				num1 = Double.parseDouble(textField.getText());
				operator = '/';
				textField.setText(textField.getText() + " ÷ ");
				}
			}
			//Equals and handle operators
			if (e.getSource() == equButton) {
				//Splits num1, operator and num2 where there is a space; ex. 5 + 3; "5", "+", "3"
				String [] parts = textField.getText().split(" ");
				
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
						textField.setText("Error");
					
				}
				//Shows result and result becomes num1 for continuous operations
				textField.setText(String.valueOf(result));
				num1 = result;
			}
			
			if (e.getSource() == clrButton) {
				textField.setText("");
			}
			
			if (e.getSource() == delButton) {
				String string = textField.getText();
				//Clears textfield
				textField.setText("");
				//Loop that reads each character of the string
				//Will stop one character before the last one in the string
				for (int i=0; i<string.length()-1; i++) {
					//The charAt(i) method extracts the character at index i of the string, when looping, it will 
					//rewrite the indexes until it reaches the condition, removing 1 character off the whole string
					textField.setText(textField.getText() + string.charAt(i));
				}
				
			}
			
			 //Negative num button
		    if (e.getSource() == negButton) {
		        String currentText = textField.getText();

		        try {
		            //Check if the input is before the operator (num1)
		            if (currentText.contains(" + ") || currentText.contains(" - ") || currentText.contains(" × ") || currentText.contains(" ÷ ")) {
		                //Handling num2: if num2 is entered after the operator, make it negative
		                String[] parts = currentText.split(" ");
		                if (parts.length == 3) {  // If num2 is entered
		                    num2 = Double.parseDouble(parts[2]);
		                    num2 = -num2;  // Negate num2
		                    textField.setText(parts[0] + " " + parts[1] + " " + num2); // Update text field with negated num2
		                }
		            } else {
		                //If no operator is entered yet, negate num1
		                double num = Double.parseDouble(currentText);
		                num = -num;  //Negate the current number
		                textField.setText(String.valueOf(num));
		            }
		        } catch (NumberFormatException num) {
		            textField.setText("No num entered");
		        }
		    }
	}
	
}

