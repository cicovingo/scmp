package model;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import lombok.*;
/*
 * author: sukru.okul
 * */
@Getter @Setter @NoArgsConstructor
public class Supplier extends User{
	private String name;
	private String password;
	private String emailAddress;
	private String phoneNumber;
	
	public Supplier(String emailAddress, String password){
		super(emailAddress, password);
	}
	
	public Supplier(String name, String password, String emailAddress, String phoneNumber) {
		super(name,password,emailAddress,phoneNumber);
	}
	
	public boolean login(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from supplier where emailAddress='" + emailAddress + "' and password='" + password + "'");
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean register(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into supplier(name,emailAddress,password,phone)values('" + name + "','" + emailAddress+ "','" + password + "','" + phoneNumber + "')");
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public void updateProducts(){
		
	}
}
