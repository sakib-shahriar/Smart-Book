import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
public class BookStore {
	static Connection connection=null;
	static JFrame frame;
	static NewPanel np;
	static NewPanel2 np2;
	static MainPanel mp;
	static SellerPanel sp;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookStore window = new BookStore();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame()
	{
		return frame;
	}
	public BookStore() {
		initialize();
	}

	private void initialize() {
		connection=DatabaseConnection.connector();
		np=new NewPanel();
		np2=new NewPanel2();
		mp=new MainPanel();
		sp=new SellerPanel();
		frame = new JFrame("UIU Book Store");
		frame.setResizable(false);
		frame.setBounds(400, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(np);
		frame.getContentPane().add(np2);
		frame.getContentPane().add(mp);
		frame.getContentPane().add(sp);
		mp.setVisible(true);
		np.setVisible(false);
		np2.setVisible(false);
		sp.setVisible(false);

		
	}
}
