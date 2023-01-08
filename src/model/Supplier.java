package model;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import lombok.*;
/*
 * author: sukru.okul
 * */
@NoArgsConstructor(access = AccessLevel.PUBLIC)  
@AllArgsConstructor(access = AccessLevel.PUBLIC)  
@ToString  
@Getter @Setter
public class Supplier extends User{
	private String name;
	private String password;
	private String emailAddress;
	private String phoneNumber;
	
	public Supplier(String emailAddress, String password){
		super(emailAddress, password);
	}
	
	public boolean login(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from supplier where emailAddress like '%" + emailAddress + "%' and password like '%" + password + "%'");
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
