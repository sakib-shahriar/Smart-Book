import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

public class BuyerPanel extends JPanel {

	private JPanel contentPane;
	private String id;
	private String password;
	private double total;
	private double discount;
	private double money;
	public static String lastId;
	public static String lastPassword;
	private JTextField searchTextfield;
	private JTextField nameTextfiled;
	private JTextField availableTextfield;
	private JTextField isbnTextfield;
	private JTextField priceTextfield;
	private JTextField authortextField;
	private JTextField categoryTextfield;
	private JTextField quantityTextfield;
	private String comboBoxString;
	public BuyerPanel(String id,String password) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setForeground(new Color(139, 0, 0));
		setBackground(new Color(30, 144, 255));
		PreparedStatement pstt=null;
		ResultSet rs=null;
		try {
			this.id=id;
			this.password=password;
			lastId=id;
			lastPassword=password;
			String q="Select * from Buyer WHERE Id=?";
			pstt=BookStore.connection.prepareStatement(q);
			pstt.setString(1,this.id);
			rs=pstt.executeQuery();
			total=rs.getDouble("Total");
			discount=rs.getDouble("Discount");
		}catch(SQLException e)
		{
			e.printStackTrace(); 
		}
		try {
			if(pstt!=null)
				pstt.close();
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setSize(650,500);
		setLayout(null);
		
		comboBoxString="";
		JLabel idLabel = new JLabel(id);
		idLabel.setForeground(new Color(255, 255, 255));
		idLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		idLabel.setBounds(472, 5, 101, 14);
		add(idLabel);
		
		JButton signOutButton = new JButton("Sign Out");
		signOutButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		signOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				BookStore.np.setVisible(true);
			}
		});
		signOutButton.setForeground(Color.WHITE);
		signOutButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		signOutButton.setBackground(new Color(244, 164, 96));
		signOutButton.setBounds(561, 5, 91, 31);
		add(signOutButton);
		
		JLabel bookListLabel = new JLabel("All Book");
		bookListLabel.setForeground(new Color(255, 255, 255));
		bookListLabel.setFont(new Font("Forte", Font.BOLD, 20));
		bookListLabel.setBounds(0, 47, 108, 31);
		add(bookListLabel);
		
		JButton bookListButton = new JButton("Book List");
		bookListButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		bookListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				BookListPanel blp2=new BookListPanel(false,true);
				BookStore.frame.getContentPane().add(blp2);
			}
		});
		bookListButton.setForeground(Color.WHITE);
		bookListButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		bookListButton.setBackground(new Color(30, 144, 255));
		bookListButton.setBounds(130, 48, 108, 31);
		add(bookListButton);
		
		JLabel sraechBookLabel = new JLabel("Search Book");
		sraechBookLabel.setForeground(new Color(255, 255, 255));
		sraechBookLabel.setFont(new Font("Forte", Font.BOLD, 20));
		sraechBookLabel.setBounds(0, 103, 122, 31);
		add(sraechBookLabel);
		
		searchTextfield = new JTextField();
		searchTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		searchTextfield.setColumns(10);
		searchTextfield.setBounds(130, 108, 174, 24);
		add(searchTextfield);
		
		
		
		nameTextfiled = new JTextField();
		nameTextfiled.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		nameTextfiled.setFont(new Font("Dialog", Font.BOLD, 12));
		nameTextfiled.setForeground(new Color(0, 0, 0));
		nameTextfiled.setBackground(new Color(30, 144, 255));
		nameTextfiled.setBounds(130, 157, 221, 20);
		nameTextfiled.setEditable(false);
		add(nameTextfiled);
		nameTextfiled.setColumns(10);
		
		availableTextfield = new JTextField();
		availableTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		availableTextfield.setForeground(new Color(0, 0, 0));
		availableTextfield.setFont(new Font("Dialog", Font.BOLD, 12));
		availableTextfield.setBackground(new Color(30, 144, 255));
		availableTextfield.setColumns(10);
		availableTextfield.setBounds(130, 311, 221, 20);
		availableTextfield.setEditable(false);
		add(availableTextfield);
		
		isbnTextfield = new JTextField();
		isbnTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		isbnTextfield.setForeground(new Color(0, 0, 0));
		isbnTextfield.setFont(new Font("Dialog", Font.BOLD, 12));
		isbnTextfield.setBackground(new Color(30, 144, 255));
		isbnTextfield.setColumns(10);
		isbnTextfield.setBounds(130, 189, 221, 20);
		isbnTextfield.setEditable(false);
		add(isbnTextfield);
		
		priceTextfield = new JTextField();
		priceTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		priceTextfield.setFont(new Font("Dialog", Font.BOLD, 12));
		priceTextfield.setForeground(new Color(0, 0, 0));
		priceTextfield.setBackground(new Color(30, 144, 255));
		priceTextfield.setColumns(10);
		priceTextfield.setBounds(130, 279, 221, 20);
		priceTextfield.setEditable(false);
		add(priceTextfield);
		
		authortextField = new JTextField();
		authortextField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		authortextField.setForeground(new Color(0, 0, 0));
		authortextField.setFont(new Font("Dialog", Font.BOLD, 12));
		authortextField.setBackground(new Color(30, 144, 255));
		authortextField.setColumns(10);
		authortextField.setBounds(130, 221, 221, 20);
		authortextField.setEditable(false);
		add(authortextField);
		
		categoryTextfield = new JTextField();
		categoryTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		categoryTextfield.setForeground(new Color(0, 0, 0));
		categoryTextfield.setFont(new Font("Dialog", Font.BOLD, 12));
		categoryTextfield.setBackground(new Color(30, 144, 255));
		categoryTextfield.setColumns(10);
		categoryTextfield.setBounds(130, 251, 221, 20);
		categoryTextfield.setEditable(false);
		add(categoryTextfield);
		
		JLabel searchLabel = new JLabel("");
		searchLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		searchLabel.setForeground(new Color(139, 0, 0));
		searchLabel.setBounds(130, 132, 174, 16);
		add(searchLabel);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!searchTextfield.getText().equals(""))
				{
					int count=0;
					ResultSet rs=null;
					PreparedStatement pst=null;
					try {
						String q="select * from BookList where Name=?";
						pst=BookStore.connection.prepareStatement(q);
						pst.setString(1, searchTextfield.getText());
						rs=pst.executeQuery();
						while(rs.next())
						{
							count++;
						}
						if(count>0)
						{
							ResultSet r=pst.executeQuery();
							nameTextfiled.setText(r.getString("name"));
							isbnTextfield.setText(r.getString("ISBN"));
							authortextField.setText(r.getString("Author"));
							categoryTextfield.setText(r.getString("Category"));
							priceTextfield.setText(""+r.getInt("Price"));
							availableTextfield.setText(""+r.getInt("Available"));
							searchLabel.setText("");
							r.close();
						}
						else
						{
							searchLabel.setText("Book not found");
							nameTextfiled.setText("");
							isbnTextfield.setText("");
							authortextField.setText("");
							categoryTextfield.setText("");
							priceTextfield.setText("");
							availableTextfield.setText("");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if(pst!=null)
							pst.close();
						if(rs!=null)
							rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Enter book name");
			}
		});
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		searchButton.setBackground(new Color(30, 144, 255));
		searchButton.setBounds(316, 104, 108, 31);
		add(searchButton);
		
		JLabel lblBuyBook = new JLabel("Buy Book");
		lblBuyBook.setForeground(Color.WHITE);
		lblBuyBook.setFont(new Font("Forte", Font.BOLD, 20));
		lblBuyBook.setBounds(0, 356, 101, 31);
		add(lblBuyBook);
		
		JComboBox comboBox = new JComboBox();
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
		comboBox.setBounds(130, 362, 155, 24);
		setComboBox(comboBox);
		add(comboBox);
		
		quantityTextfield = new JTextField();
		quantityTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		quantityTextfield.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quantityTextfield.setColumns(10);
		quantityTextfield.setBounds(316, 362, 91, 24);
		add(quantityTextfield);
		
		JButton buyButton = new JButton("Buy");
		buyButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement pst=null;
				PreparedStatement pstt=null;
				ResultSet r=null;
				try {
					
					if(!comboBoxString.equals("Select Book ----") && !comboBoxString.equals(""))
					{
						if(!quantityTextfield.getText().equals(""))
						{
							try {
								String q1="Select * from BookList WHERE name=?";
								pst=BookStore.connection.prepareStatement(q1);
								pst.setString(1, comboBoxString);
								r=pst.executeQuery();
								int temp=Integer.parseInt(quantityTextfield.getText());
								if(temp<0)
									throw new Exception();
								int qnt=r.getInt("Available");
								if(qnt==0)
									throw new IllegalArgumentException();
								boolean f;
								if(temp>qnt)
								{
									JPanel panel=new JPanel(new GridLayout(0, 1,2,2));
									JLabel label = new JLabel("Sorry, we do not have "+temp+" copies ");
									label.setForeground(Color.BLACK);
									label.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC, 15));
									panel.add(label);
									JLabel label1 = new JLabel("we have total "+qnt+" copies ");
									label1.setForeground(Color.BLACK);
									label1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
									panel.add(label1);
									JLabel label2 = new JLabel("would you like to buy all copy?");
									label2.setForeground(Color.BLACK);
									label2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
									panel.add(label2);
									if(JOptionPane.showConfirmDialog(null, panel, " ",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
									{

										money+=r.getDouble("Price")*r.getInt("Available");
										JOptionPane.showMessageDialog(null, "Book purchased");
										String s="UPDATE BookList set Available=0 where name=?";
										PreparedStatement p=BookStore.connection.prepareStatement(s);
										p.setString(1,comboBoxString);
										p.executeUpdate();
										p.close();
										comboBoxString="";
										setComboBox(comboBox);
										quantityTextfield.setText("");
									}
									
								}
								else
								{
									money+=r.getDouble("Price")*temp;
									int a=r.getInt("Available")-temp;
									String s="UPDATE BookList set Available=? where name=?";
									PreparedStatement p=BookStore.connection.prepareStatement(s);
									p.setString(1, ""+a);
									p.setString(2, comboBoxString);
									p.executeUpdate();
									p.close();
									comboBoxString="";
									setComboBox(comboBox);
									JOptionPane.showMessageDialog(null, "Book Purchased");
									quantityTextfield.setText("");
								}
								pst.close();
								r.close();
							}

							catch (SQLException e5) {
								e5.printStackTrace();
							}
						}
						else
							JOptionPane.showMessageDialog(null, "Enter Quantity");
						
					}
					else
						JOptionPane.showMessageDialog(null, "Select Book First");
				} catch (NumberFormatException e2) {
						try {
							if(pst!=null && r!=null)
							{
								pst.close();
								r.close();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Enter Valid Quantity");
				}
				catch(IllegalArgumentException e2) {
					JOptionPane.showMessageDialog(null, "Stock Out");
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Valid Quantity");
				}
				
			}
		});
		buyButton.setForeground(Color.WHITE);
		buyButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		buyButton.setBackground(new Color(30, 144, 255));
		buyButton.setBounds(429, 357, 122, 31);
		add(buyButton);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(75, 158, 47, 16);
		add(lblName);
		
		JLabel isbnLabel = new JLabel("ISBN");
		isbnLabel.setForeground(Color.BLACK);
		isbnLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		isbnLabel.setBounds(82, 189, 55, 16);
		add(isbnLabel);
		
		JLabel authorLabel = new JLabel("Author");
		authorLabel.setForeground(Color.BLACK);
		authorLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		authorLabel.setBounds(67, 222, 55, 16);
		add(authorLabel);
		
		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setForeground(new Color(0, 0, 0));
		categoryLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		categoryLabel.setBounds(55, 251, 74, 19);
		add(categoryLabel);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setForeground(Color.BLACK);
		priceLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		priceLabel.setBounds(82, 280, 55, 16);
		add(priceLabel);
		
		JLabel availableLabel = new JLabel("Available");
		availableLabel.setForeground(Color.BLACK);
		availableLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		availableLabel.setBounds(55, 312, 69, 16);
		add(availableLabel);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblQuantity.setBounds(327, 343, 69, 16);
		add(lblQuantity);
		
		JLabel lblDsicount = new JLabel("Discount");
		lblDsicount.setForeground(Color.WHITE);
		lblDsicount.setFont(new Font("Forte", Font.BOLD, 32));
		lblDsicount.setBounds(429, 157, 155, 54);
		add(lblDsicount);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Forte", Font.BOLD, 32));
		lblTotal.setBounds(429, 228, 144, 54);
		add(lblTotal);
		
		JLabel percentLabel = new JLabel(""+discount+" %");
		percentLabel.setForeground(Color.WHITE);
		percentLabel.setFont(new Font("Forte", Font.BOLD, 32));
		percentLabel.setBounds(462, 189, 122, 54);
		add(percentLabel);
		
		JLabel moneyLabel = new JLabel(""+total);
		moneyLabel.setForeground(Color.WHITE);
		moneyLabel.setFont(new Font("Forte", Font.BOLD, 32));
		moneyLabel.setBounds(462, 266, 122, 54);
		add(moneyLabel);
		
		JButton billButton = new JButton("Get Bill");
		billButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		billButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a=money-((money/100)*discount);
				total+=a;
				JPanel panel=new JPanel(new GridLayout(0, 1,2,2));
				JLabel label=new JLabel("Total : "+money);
				JLabel label1=new JLabel("Discount : "+discount+"%");
				JLabel label2=new JLabel("Amount with discount : "+a);
				panel.add(label);
				panel.add(label1);
				panel.add(label2);
				JOptionPane.showMessageDialog(null,panel,"",JOptionPane.QUESTION_MESSAGE);
				if(total>2000)
					discount=5;
				if(total>5000)
					discount=10;
				if(total>10000)
					discount=20;
				percentLabel.setText(""+discount+" %");
				moneyLabel.setText(""+total);
				
				try {
					String q="UPDATE Buyer set Total=?,Discount=? where Id=?";
					PreparedStatement pst=BookStore.connection.prepareStatement(q);
					pst.setString(1, ""+total);
					pst.setString(2, ""+discount);
					pst.setString(3, id);
					pst.executeUpdate();
					pst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				money=0;
			}
		});
		billButton.setForeground(Color.WHITE);
		billButton.setFont(new Font("Sitka Subheading", Font.BOLD, 17));
		billButton.setBackground(new Color(244, 164, 96));
		billButton.setBounds(226, 438, 183, 31);
		add(billButton);
		
		
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
}
