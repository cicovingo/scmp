package model;

import lombok.*;
/*
 * author: sukru.okul
 * */
 @NoArgsConstructor(access = AccessLevel.PUBLIC)
 @AllArgsConstructor(access = AccessLevel.PUBLIC)
 @Getter @Setter
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
}
