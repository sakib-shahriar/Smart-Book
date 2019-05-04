import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class NewPanel2 extends JPanel {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton backButton;
	private JLabel mainLabel;
	public NewPanel2() {
		setBackground(new Color(169, 169, 169));
		setSize(650, 500);
		setLayout(null);
		
		
		textField = new JTextField();
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setFont(new Font("Times New Roman", Font.BOLD, 19));
		textField.setBounds(249, 179, 172, 28);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 19));
		passwordField.setBounds(249, 219, 172, 28);
		add(passwordField);
		
		JLabel idLabel = new JLabel("Id");
		idLabel.setForeground(new Color(255, 255, 255));
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		idLabel.setBounds(131, 173, 46, 37);
		add(idLabel);
		
		JLabel passwordLabel = new JLabel("Passoword");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		passwordLabel.setBounds(131, 214, 109, 37);
		add(passwordLabel);
		
		JLabel textLabel = new JLabel("");
		textLabel.setForeground(new Color(139, 0, 0));
		textLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textLabel.setBounds(249, 247, 172, 11);
		add(textLabel);
		
		loginButton = new JButton("Log In as Admin");
		loginButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals("") || passwordField.getText().equals(""))
					textLabel.setText("Enter both id and password");
				else
				{
					if(textField.getText().equals("sakib138") && passwordField.getText().equals("1234"))
					{
						setVisible(false);
						BookStore.sp.setVisible(true);
						textField.setText("");
						passwordField.setText("");
						textLabel.setText("");
					}
					else {
						textLabel.setText("Id and Password did'nt match");
					}
				}
			}
		});
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(30, 144, 255));
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		loginButton.setBounds(249, 259, 145, 28);
		add(loginButton);
		
		backButton = new JButton("Back to main window");
		backButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BookStore.mp.setVisible(true);
				textField.setText("");
				passwordField.setText("");
				textLabel.setText("");
			}
		});
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		backButton.setBackground(new Color(30, 144, 255));
		backButton.setBounds(249, 314, 172, 28);
		add(backButton);
		
		mainLabel = new JLabel("Admin Log In");
		mainLabel.setForeground(new Color(255, 255, 255));
		mainLabel.setFont(new Font("Forte", Font.BOLD, 32));
		mainLabel.setBounds(231, 101, 228, 54);
		add(mainLabel);
		
		JPanel colorPanel2 = new JPanel();
		colorPanel2.setBackground(new Color(30, 144, 255));
		colorPanel2.setBounds(564, 0, 86, 488);
		add(colorPanel2);
		
		JPanel colorPanel1 = new JPanel();
		colorPanel1.setBackground(new Color(30, 144, 255));
		colorPanel1.setBounds(0, 0, 86, 488);
		add(colorPanel1);
	}
}
