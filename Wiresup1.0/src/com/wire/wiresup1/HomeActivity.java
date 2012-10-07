package com.wire.wiresup1;

import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class HomeActivity extends Activity {

	ImageView backImageView;
	MapView map;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        backImageView = (ImageView) this.findViewById(R.id.back);
        map = (MapView) this.findViewById(R.id.mapview);
        
        
        
    }


}
