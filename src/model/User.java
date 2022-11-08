package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factories.DatabaseConnection;

public class User implements Serializable{

	//Attributes
	private String user_id;
	private String password;
	private String f_name;
	private String l_name;
	private String role;
	
	private transient String query;
	private transient PreparedStatement stmt;
	private transient ResultSet resultSet;
	private static final long serialVersionUID = 546675745643453457L;
	
	//Constructors
	public User() {
		this.user_id = "";
		this.password = "";
		this.f_name = "";
		this.l_name = "";
		this.role = "";
	}
	
	public User(String user_id, String password, String f_name, String l_name, String role) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.f_name = f_name;
		this.l_name = l_name;
		this.role = role;
	}

	//Getters and setters
	public String getuser_id() {
		return user_id;
	}

	public void setuser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getf_name() {
		return f_name;
	}

	public void setf_name(String f_name) {
		this.f_name = f_name;
	}

	public String getl_name() {
		return l_name;
	}

	public void setl_name(String l_name) {
		this.l_name = l_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", password=" + password + ", f_name=" + f_name + ", l_name=" + l_name + ", role="
				+ role + "]";
	}
	
	public boolean checkLogin(String login_id, String login_password) {	
		query = "Select * from user where user_id= ?";

		try {
			stmt = DatabaseConnection.conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(login_id));
			resultSet = stmt.executeQuery();
			
			if(resultSet.next()) {
				this.setuser_id(resultSet.getString("user_id"));
				this.setPassword(resultSet.getString("password"));
				this.setf_name(resultSet.getString("f_name"));
				this.setl_name(resultSet.getString("l_name"));
				this.setRole(resultSet.getString("role"));
			}
			
			System.out.println(this.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("INSIDE FINALLY login_password: "+login_password);
		System.out.println("INSIDE FINALLY user_password: "+this.getPassword());
		
		if(login_password.equals(this.getPassword())) {
			System.out.println("Login true final block reached");
			return true;
		}else {
			System.out.println("Login false final block reached");
			return false;
		}

	}

}
