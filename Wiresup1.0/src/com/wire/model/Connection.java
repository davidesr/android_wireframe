package com.wire.model;

import java.lang.Object;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;



public class Connection {

	public static String WS_CALLER_ID="";
	
	public Connection() {
		// TODO Auto-generated constructor stub
	}
	
	public void authentification(String login,String password){
		JSONObject authRequest = new JSONObject();
		try {
			authRequest.put("wsCallerId", WS_CALLER_ID);
			
			authRequest.put("timestamp", Calendar.getInstance().getTime().toString());
			authRequest.put("hash", "");
			authRequest.put("identifiant",login);
			authRequest.put("password", password);
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	
	
	public static String encodeStringHexSHA1(String stringtoEncode) throws Exception{
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(stringtoEncode.getBytes());
		
		byte byteData[] = md.digest();
		
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }		     
		
        return sb.toString();
	}
	
	
	
}
