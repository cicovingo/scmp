package model;

import java.sql.DriverManager;

import lombok.*;
/*
 * author: sukru.okul
 * */
@Getter @Setter @NoArgsConstructor
public class User {
	private Long userId;
	private String name;
	private String password;
	private String emailAddress;
	private String phoneNumber;
	
	public User(String emailAddress, String password){
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	public User(String name, String password, String emailAddress, String phoneNumber) {
		this.name = name;
		this.password = password;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
}
