import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.border.BevelBorder;

public class BookListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel contentPane;
	public BookListPanel(boolean sellerWindow,boolean buyerWindow) {
		setSize(650, 500);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 10, 630, 400);
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		JButton bookListBackButton=new JButton("Back");
		bookListBackButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		bookListBackButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(sellerWindow)
				{
					setVisible(false);
					BookStore.sp.setVisible(true);
				}
				else
				{
					setVisible(false);
					BuyerPanel bp=new BuyerPanel(BuyerPanel.lastId, BuyerPanel.lastPassword);
					BookStore.frame.getContentPane().add(bp);
				}
			}
		});
		bookListBackButton.setBounds(285, 410, 108, 31);
		bookListBackButton.setForeground(Color.WHITE);
		bookListBackButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		bookListBackButton.setBackground(new Color(30, 144, 255));
		add(scrollPane);
		add(bookListBackButton);
		try {
			String q="Select * from BookList";
			PreparedStatement pst=BookStore.connection.prepareStatement(q);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			setVisible(true);
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
