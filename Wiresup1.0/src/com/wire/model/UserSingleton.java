package com.wire.model;

import java.util.Date;

/**
 * Classe implémentant l'utilisateur de l'application
 * 
 * 
 * @author dajean
 *
 */
public final class UserSingleton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static volatile UserSingleton instance = null;
	
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
	String urlPhoto;
	String sessionToken="";
	
	/**
	 * Constructeur privé du singleton
	 */
	private UserSingleton(){
		super();
	}
	
	public final static UserSingleton getInstance(){
		if (UserSingleton.instance == null) {
            synchronized(UserSingleton.class) {
              if (UserSingleton.instance == null) {
                UserSingleton.instance = new UserSingleton();
              }
            }
         }
         return UserSingleton.instance;
	}
	
	
	public boolean isConnected(){
		return (this.sessionToken.equals(""))?false:true;
	}
	
	public void forLoginSucceed(String login,String password, String sessionTok){
		setSessionToken(sessionTok);
		setLogin(login);
		setPassword(password);
		
	}
	
	private void setSessionToken(String sessionToken){
		this.sessionToken = sessionToken;
	}
	
	private void setLogin(String login){
		this.login = login;
	}
	
	private void setPassword(String passwd){
		this.password=passwd;
	}
}
