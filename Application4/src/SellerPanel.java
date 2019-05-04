import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;

public class SellerPanel extends JPanel {

	private JPanel contentPane;
	private JTextField abNameTextfield;
	private JTextField abIsbnTextfield;
	private JTextField abAuthorTextfield;
	private JTextField abPriceTextfield;
	private JTextField abQuantityTextfield;
	private JComboBox comboBox;
	private JComboBox comboBox2;
	private JComboBox comboBox3;
	private JComboBox categoryComboBox;
	private JTextField upPriceTextfield;
	private JTextField upNameTextfield;
	private String comboBoxString;
	private String comboBoxString2;
	private String comboBoxString3;
	private String categoryComboBoxString;
	private JTextField acQuantityTextfield;
	private JTextField addCategoryTestfield;

	public SellerPanel() {
		setBackground(new Color(30, 144, 255));
		setSize(650, 500);
		setLayout(null);
		
		comboBoxString="";
		comboBoxString2="";
		comboBoxString3="";
		categoryComboBoxString="";
		
		JButton bookListButton = new JButton("Book List");
		bookListButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		bookListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				BookListPanel blp1=new BookListPanel(true,false);
				BookStore.frame.getContentPane().add(blp1);
			}
		});
		bookListButton.setForeground(Color.WHITE);
		bookListButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		bookListButton.setBackground(new Color(244, 164, 96));
		bookListButton.setBounds(0, 0, 108, 31);
		add(bookListButton);
		
		JButton updateButton = new JButton("Update");
		updateButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement pst=null;
				PreparedStatement pstt=null;
				ResultSet r=null;
				try {
					
					if(!comboBoxString.equals("Select Book ----") && !comboBoxString.equals(""))
					{
						try {
							boolean f=false;
							String q="UPDATE BookList set Name=?,Price=? where name=?;";
							String q1="Select * from BookList WHERE name=?";
							pst=BookStore.connection.prepareStatement(q1);
							pst.setString(1, comboBoxString);
							r=pst.executeQuery();
							pstt=BookStore.connection.prepareStatement(q);
							if(upNameTextfield.getText().equals(""))
								pstt.setString(1,comboBoxString);
							else
							{
								pstt.setString(1,upNameTextfield.getText() );
								f=true;
							}
							if(upPriceTextfield.getText().equals(""))
								pstt.setString(2, r.getString("Price"));
							else
							{
								pstt.setString(2, upPriceTextfield.getText());
								f=true;
							}
							
							double price;	
							if(f)
							{
								if(!upPriceTextfield.getText().equals(""))
								{
									price=Double.parseDouble(upPriceTextfield.getText());
									if(price<0)
										throw new NumberFormatException();
								}
								pstt.setString(3, comboBoxString);
								pstt.executeUpdate();
								pstt.close();
								pst.close();
								r.close();
								JOptionPane.showMessageDialog(null, "Book Updated");
								setComboBox(comboBox);
								setComboBox(comboBox2);
								setComboBox(comboBox3);
								
								comboBoxString="";
								upNameTextfield.setText("");
								upPriceTextfield.setText("");
							}
							else
							{
								pstt.close();
								pst.close();
								r.close();
								JOptionPane.showMessageDialog(null, "Give Updated Value");
							}
							
						} catch (SQLException e5) {
							e5.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Select Book First");
				} catch (NumberFormatException e2) {
					{
						try {
							if(pstt!=null && pst!=null && r!=null)
							{
								pstt.close();
								pst.close();
								r.close();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					JOptionPane.showMessageDialog(null, "Enter Valid Price");
				}
				
			}
		});
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		updateButton.setBackground(new Color(30, 144, 255));  
		updateButton.setBounds(508, 251, 110, 25);
		add(updateButton);
		
		JButton addButton = new JButton("Add");
		addButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(abNameTextfield.getText().equals("") || abIsbnTextfield.getText().equals("") || abAuthorTextfield.getText().equals("") || 
						abPriceTextfield.getText().equals("") || abQuantityTextfield.getText().equals("") )
					JOptionPane.showMessageDialog(null, "You must fill all fields");
				else if(categoryComboBoxString.equals("") || categoryComboBoxString.equals("Select Category ----"))
					JOptionPane.showMessageDialog(null, "Select Category");
				else{
					try {
						double price=Double.parseDouble(abPriceTextfield.getText());
						if(price<0)
							throw new NumberFormatException();
			
						int quantity=Integer.parseInt(abQuantityTextfield.getText());
						if(quantity<0)
							throw new NumberFormatException();
						int count=0;
						try {
							String q="Select * from BookList WHERE Name=? or ISBN=?";
							PreparedStatement pstt=BookStore.connection.prepareStatement(q);
							pstt.setString(1,abNameTextfield.getText() );
							pstt.setString(2, abIsbnTextfield.getText());
							ResultSet rs=pstt.executeQuery();
							count=0;
							while(rs.next())
							{
								count++;
							}
							pstt.close();
							rs.close();
						} catch (SQLException e3) {
							e3.printStackTrace();
						}
						if(count<1)
						{
							try {
								String q1="INSERT INTO BookList (Name,ISBN,Author,Price,Available,Category) " +
						                   "VALUES (?, ?,?,?,?,?);"; 
								PreparedStatement pst=BookStore.connection.prepareStatement(q1);
								pst.setString(1,abNameTextfield.getText() );
								pst.setString(2, abIsbnTextfield.getText());
								pst.setString(3, abAuthorTextfield.getText());
								pst.setString(4, abPriceTextfield.getText());
								pst.setString(5, abQuantityTextfield.getText());
								pst.setString(6, categoryComboBoxString);
								pst.executeUpdate();
								pst.close();
								JOptionPane.showMessageDialog(null, abNameTextfield.getText()+" is added");
								abNameTextfield.setText("");
								abIsbnTextfield.setText("");
								abAuthorTextfield.setText("");
								abPriceTextfield.setText("");
								abQuantityTextfield.setText("");
								categoryComboBoxString="";
								setCategoryCombo();
								setComboBox(comboBox);
								setComboBox(comboBox2);
								setComboBox(comboBox3);
								
							} catch (SQLException e4) {
								e4.printStackTrace();
							}
						}
						else
							JOptionPane.showMessageDialog(null, "This book or the isbn already exist");
						
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Enter Valid Data");
					}
				}
			}
		});
		addButton.setForeground(Color.WHITE);
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		addButton.setBackground(new Color(30, 144, 255));
		addButton.setBounds(513, 114, 105, 24);
		add(addButton);
		
		JButton signoutButton = new JButton("Sign out");
		signoutButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		signoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BookStore.mp.setVisible(true);
			}
		});
		signoutButton.setForeground(Color.WHITE);
		signoutButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		signoutButton.setBackground(new Color(244, 164, 96));
		signoutButton.setBounds(542, 0, 108, 31);
		add(signoutButton);
		
		JLabel addBookLabel = new JLabel("Add Book");
		addBookLabel.setFont(new Font("Forte", Font.BOLD, 20));
		addBookLabel.setForeground(Color.WHITE);
		addBookLabel.setBounds(12, 71, 108, 31);
		add(addBookLabel);
		
		JLabel updateLabel = new JLabel("Update");
		updateLabel.setForeground(Color.WHITE);
		updateLabel.setFont(new Font("Forte", Font.BOLD, 20));
		updateLabel.setBounds(12, 247, 108, 31);
		add(updateLabel);
		
		JLabel deleteLabel = new JLabel("Delete");
		deleteLabel.setForeground(Color.WHITE);
		deleteLabel.setFont(new Font("Forte", Font.BOLD, 20));
		deleteLabel.setBounds(12, 325, 108, 31);
		add(deleteLabel);
		
		abNameTextfield = new JTextField();
		abNameTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		abNameTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		abNameTextfield.setBounds(121, 76, 155, 24);
		add(abNameTextfield);
		abNameTextfield.setColumns(10);
		
		abIsbnTextfield = new JTextField();
		abIsbnTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		abIsbnTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		abIsbnTextfield.setColumns(10);
		abIsbnTextfield.setBounds(282, 76, 120, 24);
		add(abIsbnTextfield);
		
		abAuthorTextfield = new JTextField();
		abAuthorTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		abAuthorTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		abAuthorTextfield.setColumns(10);
		abAuthorTextfield.setBounds(406, 76, 164, 24);
		add(abAuthorTextfield);
		
		JLabel abNameLabel = new JLabel("Name");
		abNameLabel.setForeground(Color.WHITE);
		abNameLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		abNameLabel.setBounds(163, 60, 84, 19);
		add(abNameLabel);
		
		JLabel abIsbnLabel = new JLabel("ISBN");
		abIsbnLabel.setForeground(Color.WHITE);
		abIsbnLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		abIsbnLabel.setBounds(315, 60, 84, 19);
		add(abIsbnLabel);
		
		JLabel abAuthorLabel = new JLabel("Author");
		abAuthorLabel.setBounds(434, 60, 78, 19);
		add(abAuthorLabel);
		abAuthorLabel.setForeground(Color.WHITE);
		abAuthorLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		
		JLabel abCategoryLabel = new JLabel("Category");
		abCategoryLabel.setForeground(Color.WHITE);
		abCategoryLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		abCategoryLabel.setBounds(157, 100, 84, 19);
		add(abCategoryLabel);
		
		abPriceTextfield = new JTextField();
		abPriceTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		abPriceTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		abPriceTextfield.setColumns(10);
		abPriceTextfield.setBounds(292, 115, 96, 24);
		add(abPriceTextfield);
		
		JLabel abPriceLabel = new JLabel("Price");
		abPriceLabel.setForeground(Color.WHITE);
		abPriceLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		abPriceLabel.setBounds(318, 100, 68, 19);
		add(abPriceLabel);
		
		abQuantityTextfield = new JTextField();
		abQuantityTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		abQuantityTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		abQuantityTextfield.setColumns(10);
		abQuantityTextfield.setBounds(404, 115, 97, 24);
		add(abQuantityTextfield);
		
		JLabel abQuantityLabel = new JLabel("Quantity");
		abQuantityLabel.setForeground(Color.WHITE);
		abQuantityLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		abQuantityLabel.setBounds(416, 100, 84, 19);
		add(abQuantityLabel);
		
		
		comboBox = new JComboBox();
		comboBox.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp=(JComboBox)e.getSource();
				comboBoxString=(String)temp.getSelectedItem();
			}
		});
		comboBox.setForeground(new Color(139, 69, 19));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox.setBackground(new Color(211, 211, 211));
		setComboBox(comboBox);
		comboBox.setBounds(121, 253, 155, 24);
		add(comboBox);
		
		upPriceTextfield = new JTextField();
		upPriceTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		upPriceTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		upPriceTextfield.setBounds(416, 251, 78, 26);
		add(upPriceTextfield);
		upPriceTextfield.setColumns(10);
		
		upNameTextfield = new JTextField();
		upNameTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		upNameTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		upNameTextfield.setColumns(10);
		upNameTextfield.setBounds(282, 251, 120, 26);
		add(upNameTextfield);
		
		JLabel upNameLabel = new JLabel("Name");
		upNameLabel.setForeground(Color.WHITE);
		upNameLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		upNameLabel.setBounds(315, 232, 84, 19);
		add(upNameLabel);
		
		JLabel upPriceLabel = new JLabel("Price");
		upPriceLabel.setForeground(Color.WHITE);
		upPriceLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		upPriceLabel.setBounds(426, 232, 68, 19);
		add(upPriceLabel);
		
		comboBox2 = new JComboBox();
		comboBox2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp=(JComboBox)e.getSource();
				comboBoxString2=(String)temp.getSelectedItem();
			}
		});
		comboBox2.setForeground(new Color(139, 69, 19));
		comboBox2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox2.setBackground(new Color(211, 211, 211));
		comboBox2.setBounds(121, 331, 155, 24);
		setComboBox(comboBox2);
		add(comboBox2);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxString2.equals("Select Book ----") && !comboBoxString2.equals(""))
				{
					try {
						String q="DELETE from BookList WHERE Name=?";
						PreparedStatement pst=BookStore.connection.prepareStatement(q);
						pst.setString(1, comboBoxString2);
						pst.executeUpdate();
						pst.close();
						comboBoxString2="";
						setComboBox(comboBox);
						setComboBox(comboBox2);
						setComboBox(comboBox3);
						JOptionPane.showMessageDialog(null, "Book Deleted");
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Select Book First");
			}
		});
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		deleteButton.setBackground(new Color(30, 144, 255));
		deleteButton.setBounds(312, 329, 108, 25);
		add(deleteButton);
		
		JLabel addCopyLabel = new JLabel("Add Copy");
		addCopyLabel.setForeground(Color.WHITE);
		addCopyLabel.setFont(new Font("Forte", Font.BOLD, 20));
		addCopyLabel.setBounds(12, 402, 108, 31);
		add(addCopyLabel);
		
		comboBox3 = new JComboBox();
		comboBox3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		comboBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp=(JComboBox)e.getSource();
				comboBoxString3=(String)temp.getSelectedItem();
			}
		});
		comboBox3.setBounds(121, 408, 155, 24);
		comboBox3.setForeground(new Color(139, 69, 19));
		comboBox3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox3.setBackground(new Color(211, 211, 211));
		setComboBox(comboBox3);
		add(comboBox3);
		
		acQuantityTextfield = new JTextField();
		acQuantityTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		acQuantityTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		acQuantityTextfield.setColumns(10);
		acQuantityTextfield.setBounds(306, 406, 96, 25);
		add(acQuantityTextfield);
		
		JLabel dlQuantityLabel = new JLabel("Quantity");
		dlQuantityLabel.setForeground(Color.WHITE);
		dlQuantityLabel.setFont(new Font("Cambria Math", Font.BOLD, 14));
		dlQuantityLabel.setBounds(315, 387, 84, 19);
		add(dlQuantityLabel);
		
		JButton addCopyButton = new JButton("Add");
		addCopyButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		addCopyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxString3.equals("Select Book ----") && !comboBoxString3.equals(""))
				{
					if(!acQuantityTextfield.getText().equals(""))
					{
						try {
							try {
								int temp=Integer.parseInt(acQuantityTextfield.getText());
								if(temp<0)
									throw new NumberFormatException();
								String q1="Select * from BookList Where Name=?";
								PreparedStatement pstt=BookStore.connection.prepareStatement(q1);
								pstt.setString(1, comboBoxString3);
								ResultSet rs=pstt.executeQuery();
								String q="UPDATE BookList set Available=? WHERE Name=?;";
								PreparedStatement pst=BookStore.connection.prepareStatement(q);
								temp+=rs.getInt("Available");
								pst.setString(1,""+temp);
								pst.setString(2, comboBoxString3);
								pst.executeUpdate();
								pst.close();
								pstt.close();
								rs.close();
								comboBoxString3="";
								setComboBox(comboBox);
								setComboBox(comboBox2);
								setComboBox(comboBox3);
								acQuantityTextfield.setText("");
								JOptionPane.showMessageDialog(null, acQuantityTextfield.getText()+" Copy Added");
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null,"Enter Valid Quantity");
						}
						
					}
					else
						JOptionPane.showMessageDialog(null, "You Must Enter Quantity");
				}
				else
					JOptionPane.showMessageDialog(null, "Select Book First");
			}
		});
		addCopyButton.setForeground(Color.WHITE);
		addCopyButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		addCopyButton.setBackground(new Color(30, 144, 255));
		addCopyButton.setBounds(430, 406, 108, 25);
		add(addCopyButton);
		
		categoryComboBox = new JComboBox();
		categoryComboBox.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		categoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp=(JComboBox)e.getSource();
				categoryComboBoxString=(String)temp.getSelectedItem();
			}
		});
		categoryComboBox.setForeground(new Color(139, 69, 19));
		categoryComboBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
		categoryComboBox.setBackground(new Color(211, 211, 211));
		categoryComboBox.setBounds(121, 116, 155, 24);
		setCategoryCombo();
		add(categoryComboBox);
		
		addCategoryTestfield = new JTextField();
		addCategoryTestfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addCategoryTestfield.setColumns(10);
		addCategoryTestfield.setBounds(121, 163, 155, 24);
		add(addCategoryTestfield);
		
		JButton addCategoryButton = new JButton("Add Category");
		addCategoryButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		addCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(addCategoryTestfield.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Select Category");
				else
				{
					int count=0;
					try {
						String q="select * from CategoryList where name=?";
						PreparedStatement pst=BookStore.connection.prepareStatement(q);
						pst.setString(1, addCategoryTestfield.getText());
						ResultSet rs=pst.executeQuery();
						while(rs.next())
						{
							count++;
						}
						rs.close();
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(count<1)
					{
						
						try {
							String q="INSERT INTO CategoryList (Name) " +
					                   "VALUES (?);";
							PreparedStatement pst=BookStore.connection.prepareStatement(q);
							pst.setString(1, addCategoryTestfield.getText());
							pst.executeUpdate();
							setCategoryCombo();
							pst.close();
							JOptionPane.showMessageDialog(null, "Category added");
							addCategoryTestfield.setText("");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Category already exist");
				}
			}
		});
		addCategoryButton.setForeground(Color.WHITE);
		addCategoryButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		addCategoryButton.setBackground(new Color(30, 144, 255));
		addCategoryButton.setBounds(294, 162, 146, 25);
		add(addCategoryButton);
		
		JLabel lblNewCategory = new JLabel("New Category");
		lblNewCategory.setForeground(Color.WHITE);
		lblNewCategory.setFont(new Font("Cambria Math", Font.BOLD, 14));
		lblNewCategory.setBounds(142, 144, 120, 19);
		add(lblNewCategory);
	}
	public void setComboBox(JComboBox cb)
	{
		int count=1;
		try {
			String q="Select * from BookList";
			PreparedStatement pstt=BookStore.connection.prepareStatement(q);
			ResultSet rs=pstt.executeQuery();
			while(rs.next())
			{
				count++;
			}
			pstt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String[] str=new String[count];
		str[0]="Select Book ----";
		try {
			String q="Select * from BookList";
			PreparedStatement pstt=BookStore.connection.prepareStatement(q);
			ResultSet rs=pstt.executeQuery();
			int i=1;
			while(rs.next())
			{
				str[i]=rs.getString("name");
				i++;
			}
			pstt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DefaultComboBoxModel model=new DefaultComboBoxModel(str);
		cb.setModel(model);
		cb.setSelectedIndex(0);
	}
	public void setCategoryCombo()
	{
		int count=1;
		try {
			String q="Select * from CategoryList";
			PreparedStatement pstt=BookStore.connection.prepareStatement(q);
			ResultSet rs=pstt.executeQuery();
			while(rs.next())
			{
				count++;
			}
			pstt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] str=new String[count];
		str[0]="Select Category ----";
		try {
			String q="Select * from CategoryList";
			PreparedStatement pstt=BookStore.connection.prepareStatement(q);
			ResultSet rs=pstt.executeQuery();
			int i=1;
			while(rs.next())
			{
				str[i]=rs.getString("name");
				i++;
			}
			pstt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DefaultComboBoxModel model=new DefaultComboBoxModel(str);
		categoryComboBox.setModel(model);
		categoryComboBox.setSelectedIndex(0);
	}
}
