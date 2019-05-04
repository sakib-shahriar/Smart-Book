import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnection {
	static Connection conn=null;
	public static Connection connector()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:E:\\BookStore.sqlite");
			return conn;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
}
