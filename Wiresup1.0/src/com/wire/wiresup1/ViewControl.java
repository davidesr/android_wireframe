package com.wire.wiresup1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class ViewControl implements OnClickListener{

	Context context;
	Class<?> act;
	
	public ViewControl(Context c,Class<? extends Activity> a){
		this.act = a;
		this.context=c;
		
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i = new Intent(context,act);
		context.startActivity(i);
		
		
	}

}
