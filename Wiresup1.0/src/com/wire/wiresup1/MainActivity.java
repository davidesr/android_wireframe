package com.wire.wiresup1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	ImageView terms;
	TextView help;
	private final static String URL_HELP_WEB_VIEW = "http://erems.free.fr";
	private final static String URL_HELP_TERMS_CONDITIONS_VIEW = "http://google.com";
	
	
	private void init(){
		terms = (ImageView) this.findViewById(R.id.terms);
        help = (TextView) this.findViewById(R.id.help);
        
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        init();
                            
        MainActivityListener buttonListener = new MainActivityListener(this);
        terms.setOnClickListener(buttonListener);
        help.setOnClickListener(buttonListener);
    
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    public class MainActivityListener implements OnClickListener{

    	Context context;
    	
    	public MainActivityListener(Context c){
    		
    		this.context=c;
    		
    	}
    	
    	@Override
    	public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    		Intent i = new Intent(context,HelpActivity.class);
    		
    		if(arg0==terms){
    			i.putExtra("url", URL_HELP_TERMS_CONDITIONS_VIEW);
    			setResult(RESULT_OK,i);
        		context.startActivity(i);
    			
    		}
    		else if(arg0==help){    			
    			i.putExtra("url", URL_HELP_WEB_VIEW);
    			setResult(RESULT_OK,i);
        		context.startActivity(i);
    			
    		}
    		    		
    		
    	}

    }
}
