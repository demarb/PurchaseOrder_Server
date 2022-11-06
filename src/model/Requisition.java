package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.DatabaseConnection;

public class Requisition  implements Serializable{

	//Attributes
	private int req_id;
	private int item_id;
	private String item_name;
	private double quantity;
	private double unit_price;
	private double total_price;
	private String supplier_name;
	private String supplier_tel;
	private String supplier_email;
	private String associated_emp;
	private String req_status;
	
//	private transient Connection dbConn;

	private transient String query;
	private transient PreparedStatement stmt;
	private transient ResultSet resSet;
	private static final long serialVersionUID = 754667564345345745L;
	
	
	public Requisition() {
//		this.dbConn = null;
//		this.setDbConn(null);
//		this.setDbConn(DatabaseConnection.getDBConnection());
		
//		this.dbConn = new DatabaseConnection().getDBConnection();
		
		this.req_id = 0;
		this.item_id = 0;
		this.item_name = "";
		this.quantity = 0.0;
		this.unit_price = 0.0;
		this.total_price = 0.0;
		this.supplier_name = "";
		this.supplier_tel = "";
		this.supplier_email = "";
		this.associated_emp = "";
		this.req_status = "";
	}
	
//	public Connection getDbConn() {
//		return dbConn;
//	}
//
//	public void setDbConn(Connection dbConn) {
//		this.dbConn = dbConn;
//	}

	public Requisition(int req_id, int item_id, String item_name, float quantity, float unit_price, float total_price,
			String supplier_name, String supplier_tel, String supplier_email, String associated_emp,
			String req_status) {
		this.req_id = req_id;
		this.item_id = item_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.total_price = total_price;
		this.supplier_name = supplier_name;
		this.supplier_tel = supplier_tel;
		this.supplier_email = supplier_email;
		this.associated_emp = associated_emp;
		this.req_status = req_status;
	}

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_tel() {
		return supplier_tel;
	}

	public void setSupplier_tel(String supplier_tel) {
		this.supplier_tel = supplier_tel;
	}

	public String getSupplier_email() {
		return supplier_email;
	}

	public void setSupplier_email(String supplier_email) {
		this.supplier_email = supplier_email;
	}

	public String getAssociated_emp() {
		return associated_emp;
	}

	public void setAssociated_emp(String associated_emp) {
		this.associated_emp = associated_emp;
	}

	public String getReq_status() {
		return req_status;
	}

	public void setReq_status(String req_status) {
		this.req_status = req_status;
	}

	@Override
	public String toString() {
		return "Requisition [getReq_id()=" + getReq_id() + ", getItem_id()=" + getItem_id() + ", getItem_name()="
				+ getItem_name() + ", getQuantity()=" + getQuantity() + ", getUnit_price()=" + getUnit_price()
				+ ", getTotal_price()=" + getTotal_price() + ", getSupplier_name()=" + getSupplier_name()
				+ ", getSupplier_tel()=" + getSupplier_tel() + ", getSupplier_email()=" + getSupplier_email()
				+ ", getAssociated_emp()=" + getAssociated_emp() + ", getReq_status()=" + getReq_status() + "]";
	}
	
	public boolean addRequisition(Requisition requisitionObj) {
		query = "INSERT into requisition (item_id, quantity, unit_price, total_price, supplier_name, supplier_tel, supplier_email, "
				+ "associated_emp, req_status) VALUES(?,?,?,?,?,?,?,?,?)";
		boolean rowCheck = false;
//		this.dbConn = new DatabaseConnection().getDBConnection();
		try {
//			this.dbConn = new DatabaseConnection().getDBConnection();
			stmt = DatabaseConnection.conn.prepareStatement(query);
//			stmt = dbConn.prepareStatement(query);
			stmt.setInt(1, requisitionObj.getItem_id());
			stmt.setDouble(2, requisitionObj.getQuantity());
			stmt.setDouble(3, requisitionObj.getUnit_price());
			stmt.setDouble(4, requisitionObj.getTotal_price());
			stmt.setString(5, requisitionObj.getSupplier_name());
			stmt.setString(6, requisitionObj.getSupplier_tel());
			stmt.setString(7, requisitionObj.getSupplier_email());
			stmt.setString(8, requisitionObj.getAssociated_emp());
			stmt.setString(9, requisitionObj.getReq_status());
			
			
			int rowChange = stmt.executeUpdate();
			System.out.println("Row Change: "+rowChange+ " rows");
			
			if(rowChange>=1) {
				rowCheck=true;
			}else {
				rowCheck=false;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}//finally {
//			try {
//				this.dbConn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return rowCheck;
	}
	

	public ArrayList getAllRequisitions() {
		ArrayList<Requisition> requisitionList = new ArrayList<Requisition>();
		
//		query = "Select * from requisition";
		query = "SELECT requisition.*, product.item_name FROM requisition INNER JOIN product ON requisition.item_id=product.item_id";
//		this.dbConn = new DatabaseConnection().getDBConnection();
		try {
//			this.dbConn = new DatabaseConnection().getDBConnection();
			stmt = DatabaseConnection.conn.prepareStatement(query);
//			stmt = dbConn.prepareStatement(query);
			resSet = stmt.executeQuery();
			
			
			while(resSet.next()) {
				Requisition requisitionObj = new Requisition();
				requisitionObj.setReq_id(Integer.parseInt(resSet.getString("req_id")));
				requisitionObj.setItem_id(Integer.parseInt(resSet.getString("item_id")));
				
				requisitionObj.setItem_name(resSet.getString("item_name"));
				
				requisitionObj.setQuantity(Double.parseDouble(resSet.getString("quantity")));
				requisitionObj.setUnit_price(Double.parseDouble(resSet.getString("unit_price")));
				requisitionObj.setTotal_price(Double.parseDouble(resSet.getString("total_price")));
				requisitionObj.setSupplier_name(resSet.getString("supplier_name"));
				requisitionObj.setSupplier_tel(resSet.getString("supplier_tel"));
				requisitionObj.setSupplier_email(resSet.getString("supplier_email"));
				requisitionObj.setAssociated_emp(resSet.getString("associated_emp"));
				requisitionObj.setReq_status(resSet.getString("req_status"));
				requisitionList.add(requisitionObj);
				
				System.out.println(requisitionObj.toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//finally {
//			this.dbConn= null;
				
		
			
//			try {
//				this.dbConn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		System.out.println("product list size: "+ requisitionList.size());
		System.out.println("product list print: "+ requisitionList.toString());
		return requisitionList;
	}



	
}
