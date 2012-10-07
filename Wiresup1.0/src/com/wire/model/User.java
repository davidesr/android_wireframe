package com.wire.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4771719750848516923L;
	String login;
	String password;
	String sessiontoken;
	String lastName;
	String firstName;
	Date birthDate;
	String streetNumber;
	String streetName;
	String zipCode;
	String city;
	String country;
	String telephone;
	String email;

	public User(String lgi,String pswd){
		this.login=lgi;
		this.password=pswd;
	}
	
	
}
