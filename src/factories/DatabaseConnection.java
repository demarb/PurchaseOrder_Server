package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

//	public static Connection dbConn = null;
	public static Connection conn = null;
	
//	public static Connection getDBConnection() {		
//		
//		String url= "jdbc:mysql://localhost:3306/purchase_order_db";
//		try {
//			if (conn == null) {
//				conn = DriverManager.getConnection(url, "root", "");
//				System.out.println("Database connection successfully made.");
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		return conn;
//		
//	}
	
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
	
//	public static void closeDbConnection() {
//		if (conn != null){
//            try{
//                conn.close();
//                System.out.println("Database Connection closed");
//            }
//            catch(SQLException s){
//                s.printStackTrace();
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//	}
	
}
