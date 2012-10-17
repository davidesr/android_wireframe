package com.wire.wiresup1;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapviewActivity extends MapActivity {

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		//ajout d'un commentaire
		return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_mapview);
	   
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	}
}
