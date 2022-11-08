package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection conn = null;
	
	static {
		String url= "jdbc:mysql://localhost:3306/purchase_order_db";
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(url, "root", "");
				System.out.println("Database connection successfully made.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
