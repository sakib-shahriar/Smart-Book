import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class NewPanel extends JPanel {

	private JPanel contentPane;
	private JTextField idTextfield;
	private JPasswordField passwordTextfield;
	private JButton loginButton;
	private JLabel textLabel1;
	private JLabel textLabel2;
	private JButton signupButton;
	private JButton backButton;
	private JLabel loginLabel;
	private JLabel mainLabel;
	private JPanel colorPanel2;
	private JPanel colorPanel1;
	public NewPanel() {
		setBackground(new Color(169, 169, 169));
		setSize(650, 500);
		setLayout(null);
		idTextfield = new JTextField();
		idTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		idTextfield.setFont(new Font("Times New Roman", Font.BOLD, 19));
		idTextfield.setBounds(221, 110, 198, 30);
		add(idTextfield);
		idTextfield.setColumns(10);
		
		passwordTextfield = new JPasswordField();
		passwordTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordTextfield.setFont(new Font("Dialog", Font.BOLD, 19));
		passwordTextfield.setBounds(221, 151, 198, 30);
		add(passwordTextfield);
		
		JLabel idLabel = new JLabel("Id");
		idLabel.setForeground(Color.WHITE);
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		idLabel.setBounds(105, 110, 46, 37);
		add(idLabel);
		
		JLabel passowordLabel = new JLabel("Passoword");
		passowordLabel.setForeground(Color.WHITE);
		passowordLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		passowordLabel.setBounds(105, 147, 109, 37);
		add(passowordLabel);
		
		loginLabel = new JLabel("");
		loginLabel.setForeground(new Color(139, 0, 0));
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginLabel.setBounds(221, 184, 172, 11);
		add(loginLabel);
		
		loginButton = new JButton("Log In");
		loginButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!idTextfield.getText().equals("") && !passwordTextfield.getText().equals(""))
				{
					PreparedStatement pst=null;
					ResultSet rs=null;
					try {
						String q="select * from Buyer where Id=? and Password=?";
						pst=BookStore.connection.prepareStatement(q);
						pst.setString(1, idTextfield.getText());
						pst.setString(2, passwordTextfield.getText());
						rs=pst.executeQuery();
						int count=0;
						while(rs.next())
						{
							count++;
						}
						if(count>0)
						{
							setVisible(false);
							BuyerPanel bp=new BuyerPanel(idTextfield.getText(),passwordTextfield.getText());
							BookStore.frame.getContentPane().add(bp);
							passwordTextfield.setText("");
							idTextfield.setText("");
							loginLabel.setText("");
						}
						else
							loginLabel.setText("Id and Password did'nt match");
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
					try {
						if(pst!=null)
							pst.close();
						if(rs!=null)
							rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else 
					JOptionPane.showMessageDialog(null, "Enter both Id and Password");
			}
		});
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(30, 144, 255));
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		loginButton.setBounds(221, 197, 89, 30);
		add(loginButton);
		
		textLabel1 = new JLabel("Do not have an account?");
		textLabel1.setForeground(new Color(25, 25, 112));
		textLabel1.setFont(new Font("Century Schoolbook", Font.ITALIC, 14));
		textLabel1.setBounds(221, 252, 254, 22);
		add(textLabel1);
		
		textLabel2 = new JLabel("Signup Here");
		textLabel2.setForeground(new Color(25, 25, 112));
		textLabel2.setFont(new Font("Century Schoolbook", Font.ITALIC, 14));
		textLabel2.setBounds(221, 278, 89, 22);
		add(textLabel2);
		
		signupButton = new JButton("Sign Up");
		signupButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel=new JPanel(new BorderLayout(5,5));
				JPanel panel2=new JPanel(new GridLayout(0, 1,2,2));
				panel2.add(new JLabel("Id",SwingConstants.RIGHT));
				panel2.add(new JLabel("Password",SwingConstants.RIGHT));
				panel.add(panel2, BorderLayout.WEST);
				JPanel panel3=new JPanel(new GridLayout(0, 1,2,2));
				JTextField tf=new JTextField(15);
				JPasswordField pas=new JPasswordField(15);
				panel3.add(tf);
				panel3.add(pas);
				panel.add(panel3,BorderLayout.CENTER);
				JOptionPane.showMessageDialog(contentPane,panel,"Sign Up",JOptionPane.QUESTION_MESSAGE);
				if(tf.getText().equals("") || pas.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Enter Both id and password");
				else
				{
					if(tf.getText().length()<12)
					{
						int count = 0;
						PreparedStatement pstt=null;
						ResultSet rs=null;
						try {
							String q="Select * from Buyer WHERE Id=?";
							pstt=BookStore.connection.prepareStatement(q);
							pstt.setString(1,tf.getText() );
							rs=pstt.executeQuery();
							count=0;
							while(rs.next())
							{
								count++;
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if(count<1)
						{
							try {
								String q1="INSERT INTO Buyer (Id,Password,Total,Discount) " +
						                   "VALUES (?, ?,0,0);"; 
								PreparedStatement pst=BookStore.connection.prepareStatement(q1);
								pst.setString(1,tf.getText() );
								pst.setString(2, pas.getText());
								pst.executeUpdate();
								pst.close();
								JOptionPane.showMessageDialog(null, "Account Created");
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "This id already exist");
							
						}
						try {
							if(pstt!=null)
								pstt.close();
							if(rs!=null)
								rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Id length should be less than 12");
				}
			}
		});
		signupButton.setForeground(new Color(0, 0, 0));
		signupButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		signupButton.setBackground(new Color(211, 211, 211));
		signupButton.setBounds(310, 278, 83, 22);
		add(signupButton);
		
		backButton = new JButton("Back to main window");
		backButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BookStore.mp.setVisible(true);
				idTextfield.setText("");
				passwordTextfield.setText("");
				loginLabel.setText("");
			}
		});
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(30, 144, 255));
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		backButton.setBounds(221, 337, 180, 23);
		add(backButton);
		
		mainLabel = new JLabel("User Log In");
		mainLabel.setForeground(Color.WHITE);
		mainLabel.setFont(new Font("Forte", Font.BOLD, 32));
		mainLabel.setBounds(222, 26, 228, 54);
		add(mainLabel);
		
		colorPanel2 = new JPanel();
		colorPanel2.setBackground(new Color(30, 144, 255));
		colorPanel2.setBounds(564, 0, 86, 488);
		add(colorPanel2);
		
		colorPanel1 = new JPanel();
		colorPanel1.setBackground(new Color(30, 144, 255));
		colorPanel1.setBounds(0, 0, 86, 488);
		add(colorPanel1);
		JButton jButton=new JButton("sakib");
		jButton.setBounds(98, 208, 83, 22);
		
	}

}
