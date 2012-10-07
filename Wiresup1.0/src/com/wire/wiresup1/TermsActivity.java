package com.wire.wiresup1;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class TermsActivity extends Activity {
	ImageView backImageView; 
	WebView webView;
	
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_terms_conditions);
	        
	        backImageView = (ImageView) this.findViewById(R.id.back);
	        backImageView.setOnClickListener(new ViewControl(this,MainActivity.class));
	        
	        webView = (WebView) this.findViewById(R.id.webview_terms);
	        webView.loadUrl("http://google.com");
	        
	        
	        
	    }
	
}
