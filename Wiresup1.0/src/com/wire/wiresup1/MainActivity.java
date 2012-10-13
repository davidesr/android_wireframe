package com.wire.wiresup1;

//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.security.NoSuchAlgorithmException;

//import org.json.JSONException;
//import org.json.JSONObject;

import com.wire.utils.Values;
//import com.wire.utils.WebServiceRequest;
import com.wire.utils.WebserviceRequestAsyntask;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	ImageView terms;
	TextView help;
	EditText login;
	EditText password;
	Button validate;

	private void init(){
		terms = (ImageView) this.findViewById(R.id.terms);
		help = (TextView) this.findViewById(R.id.help);
		login = (EditText) this.findViewById(R.id.login);
		password = (EditText) this.findViewById(R.id.password);
		validate = (Button) this.findViewById(R.id.validate);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		MainActivityListener buttonListener = new MainActivityListener(this);

		terms.setOnClickListener(buttonListener);
		help.setOnClickListener(buttonListener);
		validate.setOnClickListener(buttonListener);



	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void createAlertDialog(String title, String message) {
		// TODO Auto-generated method stub

		AlertDialog ad = new AlertDialog.Builder(this).create();
		ad.setTitle(title);
		ad.setMessage(message);
		ad.show();
	}
	private boolean checkInternetConnection() {
		ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);  
		if ( conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED
				||  conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED  ) {

			Log.v("Congr","Connection Presente");
			return true;
		}
		else {
			Log.v("conMgr"," No Connection");
			Toast.makeText(MainActivity.this, Values.ERROR_MESSAGE_CONNECTION_RESEAU,Toast.LENGTH_LONG).show();
			return false;
		}

	}
	
	private boolean checkEmptyValues() {
		// TODO Auto-generated method stub
		boolean l_flg = false ,p_flg = false;;
		if(login.getText().toString().equals("")){
			l_flg=true;
			//login.setBackgroundColor(Color.RED);
		}
		if(password.getText().toString().equals("")){
			p_flg=true;
			//password.setBackgroundColor(Color.RED);
		}

		if (l_flg || p_flg){
			createAlertDialog(Values.ERROR_TITLE_EMPTY_PARAMETERS,Values.ERROR_MESSAGE_EMPTY_PARAMETERS);
			return false;

		}
		else
			return true;


	}
	
	public class MainActivityListener implements OnClickListener{

		Context context;

		public MainActivityListener(Context c){

			this.context=c;

		}

		@Override
		public void onClick(View arg0) {
		
			Intent i = new Intent(context,HelpActivity.class);

			switch(arg0.getId()){

			case R.id.terms : 
				i.putExtra("url", Values.URL_HELP_TERMS_CONDITIONS_VIEW);
				setResult(RESULT_OK,i);
				context.startActivity(i);
				break;

			case R.id.help : 
				i.setClass(context,HomeActivity.class);
				
				i.putExtra("url", Values.URL_HELP_WEB_VIEW);
				setResult(RESULT_OK,i);
				context.startActivity(i);
				break;

			case R.id.validate :{ 
				if(!checkEmptyValues())
					return;
				
				if (!checkInternetConnection()) // Vérifier la connexion.
					return;

				

				String value_login = login.getText().toString();
				String value_password = password.getText().toString();

				WebserviceRequestAsyntask wsa = new WebserviceRequestAsyntask();
				wsa.execute(value_login,value_password);

				/*try{
    				//WebServiceRequest wsr = new WebServiceRequest();
    				//JSONObject response = wsr.loginRequest(value_login, value_password);



    				/*if(!response.has(Values.LABEL_SESSION_TOKEN)){
    					LayoutInflater inflater = getLayoutInflater();
    					View layout = inflater.inflate(R.layout.toast_layout,
    					(ViewGroup) findViewById(R.id.toast_layout_root));

    					TextView text = (TextView) layout.findViewById(R.id.text);
    					text.setText(Values.ERROR_MESSAGE_CONNECTION_SERVEUR);

    					Toast toast = new Toast(getApplicationContext());
    					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    					toast.setDuration(Toast.LENGTH_LONG);
    					toast.setView(layout);
    					toast.show();


    			}
    			catch(JSONException e){
    				Log.v("ERROR_CONNECTION_LOGIN", e.getMessage());
    			}
    			catch(NoSuchAlgorithmException e1){
    				Log.v("ERROR_CONNECTION_LOGIN", e1.getMessage());	
    			}
    			catch(MalformedURLException e2){
    				Log.v("ERROR_CONNECTION_LOGIN", e2.getMessage());
    			}
    			catch(IOException e3){
    				Log.v("ERROR_CONNECTION_LOGIN", e3.getMessage());
    			}
				 */
			}
			break;

			default:break;
			}



		}

	}
}



