package com.wire.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
//import org.json.simple.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;




/**
 * 
 */

/**
 * @author dajean
 *
 */
public class WebServiceRequest{

	
	
	/**
	 * Envoi d'une requête de connexion au format JSON
	 * @param login
	 * @param password
	 * @return résultat de la requete.
	 * @throws NoSuchAlgorithmException
	 */
	public JSONObject loginRequest(String login,String password) throws JSONException,NoSuchAlgorithmException,MalformedURLException,IOException{
		String timestamp = System.currentTimeMillis()+"";
		String hash = Sha1Utils.sha(Values.WS_CALLER_SECRET + timestamp);
		
		//Création du contenu du message
		String input = buildLogin(hash,timestamp,login,password);
		Log.v("WS_REQ",input);
		//Création d'une connexion à l'url serveur
		URL url = new URL(Values.URL_LOGIN);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Log.v("LoginRequest","Connexion");
		
		conn.setDoOutput(true);
		conn.setRequestProperty("content-type", "application/json");
		conn.setRequestProperty("Content-Type", "application/json");
		//Paramètre de la methode de requete d'envoi 
		conn.setRequestProperty("Method",Values.REQUEST_METHOD);
		//System.out.println("Method");
		
		
		
		Log.v("Content",input);
		System.out.println("Input : "+input); 
		
		//
		// Envoi du contenu de la requete au serveur
		
		Log.v("WS_REQ","OK");
		if(conn.getDoOutput()){
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
		}
		else return null;
		
		InputStreamReader is = new InputStreamReader(conn.getInputStream());
		Log.v("WS_REQ","InputStreamReader");
		BufferedReader br = new BufferedReader(is);
		Log.v("WS_REQ","BufferedReader");
		String output;
		Log.v("WS_REQ","Output from Server .... ");
		
		while ((output = br.readLine()) != null) {
		}
 
		Log.v("WS_REQ",output);
		
		//conn.disconnect();
 
		Log.v("WS_REQ","Déconnexion");

		//JSONObject response = new JSONObject(output);
		JSONObject response = new JSONObject(output);
		return response;
	}
	
	
	
	/**
	 * 
	 * Construit la requete permettant de se connecter au serveur
	 * @param hash : hash codé en SHA1 pour l'authentification
	 * @param timestamp : date de création du message
	 * @param login : identifiant de l'utilisateur
	 * @param password : mot de passe de l'utilisateur
	 * 
	 * 
	 */
	private String buildLogin(String hash,String timestamp,String login,String password){
		Map<String,String> param=new HashMap<String,String>();
		
		
		param.put(Values.LABEL_WS_CALLER_ID, Values.WS_CALLER_ID);
		param.put(Values.LABEL_WS_TIMESTAMP, timestamp);
		param.put(Values.LABEL_HASH, hash);
		param.put(Values.LABEL_LOGIN, login);
		param.put(Values.LABEL_PASSWORD, password);
		
		JSONObject jso = new JSONObject(param);
		return jso.toString();
	}
	

	/**
	 * 
	 * Construit la requete permettant la déconnexion au serveur
	 * @param id : identifiant de la requete
	 * @param hash : hash codé en SHA1 pour l'authentification
	 * 
	 * 
	 */
	private String buildLogout(String id,String timestamp,String hash){
		Map<String,String> param=new HashMap<String,String>();
		
		
		param.put(Values.LABEL_WS_CALLER_ID, id);
		param.put(Values.LABEL_WS_TIMESTAMP, timestamp);
		param.put(Values.LABEL_HASH, hash);
		JSONObject jso = new JSONObject(param);
		return jso.toString();
		
	}
}
