package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.DatabaseConnection;

public class Product implements Serializable{

	//Attribute
	private int item_id;
	private String item_name;
	private int item_max;
	private int item_current;
	private String item_reorder_status;
	
	private transient String query;
	private transient PreparedStatement stmt;
	private transient ResultSet resSet;
	private static final long serialVersionUID = 643453457546675745L;
	
	//Constructors 
	public Product() {
		this.item_id = 0;
		this.item_name = "";
		this.item_max = 0;
		this.item_current = 0;
		this.item_reorder_status = "";
	}
	
	public Product(int item_id, String item_name, int item_max, int item_current, String item_reorder_status) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_max = item_max;
		this.item_current = item_current;
		this.item_reorder_status = item_reorder_status;
	}
	
	//Getters and setters
	public int getitem_id() {
		return item_id;
	}
	public void setitem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getitem_name() {
		return item_name;
	}
	public void setitem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getitem_max() {
		return item_max;
	}
	public void setitem_max(int item_max) {
		this.item_max = item_max;
	}
	public int getitem_current() {
		return item_current;
	}
	public void setitem_current(int item_current) {
		this.item_current = item_current;
	}
	public String getitem_reorder_status() {
		return item_reorder_status;
	}
	public void setitem_reorder_status(String item_reorder_status) {
		this.item_reorder_status = item_reorder_status;
	}
	
	
	@Override
	public String toString() {
		return "Product [item_id=" + item_id + ", item_name=" + item_name + ", item_max=" + item_max + ", item_current="
				+ item_current + ", item_reorder_status=" + item_reorder_status + "]";
	}
	
	
	public ArrayList getAllProducts() {
		System.out.println("[PRODUCT] : Getting all Products from Database");
		ArrayList<Product> productList = new ArrayList<Product>();
		query = "Select * from product";
		
		try {
			stmt = DatabaseConnection.conn.prepareStatement(query);
			resSet = stmt.executeQuery();
			
			while(resSet.next()) {
				Product productObj = new Product();
				productObj.setitem_id(Integer.parseInt(resSet.getString("item_id")));
				productObj.setitem_name(resSet.getString("item_name"));
				productObj.setitem_max(Integer.parseInt(resSet.getString("item_max")));
				productObj.setitem_current(Integer.parseInt(resSet.getString("item_current")));
				productObj.setitem_reorder_status(resSet.getString("item_reorder_status"));
				productList.add(productObj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("[PRODUCT] : Stream of Products being sent to requesting client");
		System.out.println("[PRODUCT] : "+ productList.toString());
		return productList;
	}
	
	public boolean updateProduct(String itemID, String itemCurrent) {	
		System.out.println("[PRODUCT] : Updating a Product in Database");
		System.out.println("[PRODUCT] : Attempting to Update Item No. "+ itemID + " with Quantity Value- " + itemCurrent);
		query = "UPDATE product SET item_current=? WHERE product.item_id=?";
		boolean rowCheck = false;
		try {
			stmt = DatabaseConnection.conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(itemCurrent));
			stmt.setInt(2, Integer.parseInt(itemID));
			
			int rowChange = stmt.executeUpdate();
			System.out.println("[DATABASE] : " + rowChange + " Rows were affected.");
			
			if(rowChange>=1) {
				rowCheck=true;
			}else {
				rowCheck=false;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return rowCheck;
	}
	
}
