package com.wire.wiresup1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;


public class HelpActivity extends Activity {
	ImageView backImageView; 
	WebView webView;
	String url;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);


		backImageView = (ImageView) this.findViewById(R.id.back);
		backImageView.setOnClickListener(new HelpActivityListener(this));

		webView = (WebView) this.findViewById(R.id.webview_help);
		webView.loadUrl(url);


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
		    if (data.hasExtra("url")) {
		      this.url=data.getExtras().getString("url");
		    }
		}
	}

	
	
	class HelpActivityListener implements OnClickListener {
		Context context;
		
		
		public HelpActivityListener(Context c){
			this.context=c;
			
		}
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			setResult(RESULT_OK);
			finish();
			
			
		}
		
		
	}

}
