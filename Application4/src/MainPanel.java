import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setBackground(new Color(30, 144, 255));
		
		setSize(650, 500);
		setLayout(null);
		JButton buyerButton = new JButton("Buyer");
		buyerButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		buyerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BookStore.np.setVisible(true);
			}
		});
		buyerButton.setForeground(new Color(30, 144, 255));
		buyerButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		buyerButton.setBackground(SystemColor.menu);
		buyerButton.setBounds(255, 161, 138, 51);
		add(buyerButton);
		
		JButton sellerbutton = new JButton("Seller");
		sellerbutton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sellerbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BookStore.np2.setVisible(true);
			}
		});
		sellerbutton.setForeground(new Color(30, 144, 255));
		sellerbutton.setFont(new Font("Tahoma", Font.BOLD, 15));
		sellerbutton.setBackground(SystemColor.menu);
		sellerbutton.setBounds(255, 237, 138, 51);
		add(sellerbutton);
		
		JLabel lblLogInAs = new JLabel("Log In AS");
		lblLogInAs.setForeground(Color.WHITE);
		lblLogInAs.setFont(new Font("Gadugi", Font.BOLD, 38));
		lblLogInAs.setBounds(242, 82, 228, 54);
		add(lblLogInAs);
		
		JLabel lblBookStoreSoftware = new JLabel("Book Store Software");
		lblBookStoreSoftware.setForeground(Color.WHITE);
		lblBookStoreSoftware.setFont(new Font("Harlow Solid Italic", Font.BOLD, 36));
		lblBookStoreSoftware.setBounds(162, 343, 346, 51);
		add(lblBookStoreSoftware);

	}
}
