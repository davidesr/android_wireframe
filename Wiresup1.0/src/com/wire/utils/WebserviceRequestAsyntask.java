package com.wire.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.wire.model.UserSingleton;
import com.wire.wiresup1.MainActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WebserviceRequestAsyntask extends
		AsyncTask<String, Integer, String> {

	Context context;
	
	
	public WebserviceRequestAsyntask(Context context){
		this.context=context;
	}
	
	
	@Override
	protected String doInBackground(String... params) {
		
		String login = params[0];
		String password = params[1];
		String hash="";
		// TODO Auto-generated method stub
		String result="";
		HttpClient httpclient = new DefaultHttpClient();  
        HttpPost request = new HttpPost(Values.URL_LOGIN);  
        
        //request.addHeader("deviceId", deviceId);  
        request.addHeader(Values.LABEL_CONTENT_TYPE, Values.CONTENT_TYPE);
        
        String timestamp = System.currentTimeMillis()+"";
		try {
			hash = Sha1Utils.sha(Values.WS_CALLER_SECRET + timestamp);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Création du contenu du message
		String input = buildLogin(hash,timestamp,login,password);
        
        StringEntity se=null;
        try {
			se = new StringEntity(input);
			se.setChunked(false);
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        Log.i("WSRA", input);
        request.setEntity(se);
        
        
        
        ResponseHandler<String> handler = new BasicResponseHandler();  
        
        
        try {  
            result = httpclient.execute(request, handler); 
            Log.v("WSRA",result);
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        httpclient.getConnectionManager().shutdown();   
        Log.i("WSRA", result);
        
      
        try {
			JSONObject jresponse = readResponse(result);
			
			if(jresponse.getString(Values.LABEL_STATUS).equals("true")){
				String SessionToken = jresponse.getString(Values.LABEL_SESSION_TOKEN);
				UserSingleton.getInstance().forLoginSucceed(login,password,SessionToken);
			}
				
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
     // end callWebService()  	
		return result;
		
		
		
		
	}
	
	

	private JSONObject readResponse(String response) throws JSONException{
		JSONObject jresponse = new JSONObject(response);
		
		return jresponse;
		
	}
	
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

	
	
}
