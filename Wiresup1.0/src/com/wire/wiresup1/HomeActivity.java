package com.wire.wiresup1;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends MapActivity {

	//ImageView backImageView;
	MapView map;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(true);
        
        
        map = (MapView) this.findViewById(R.id.mapview);
        map.setBuiltInZoomControls(true);
        
        
        
    }
    @Override
    public boolean isRouteDisplayed(){
    	return false;
    }


    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_action_bar, menu);
		return true;
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menu_add:
    		Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
    		// Comportement du bouton "A Propos"
    		return true;
    	case R.id.menu_reminder:
    		Toast.makeText(this, "Reminder", Toast.LENGTH_SHORT).show();
    		// Comportement du bouton "Aide"
    		return true;
    	case R.id.menu_logout:
    		Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
    		// Comportement du bouton "Rafraichir"
    		return true;
    	case R.id.menu_search:
    		// Comportement du bouton "Recherche"
    		Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
    		return true;
    	case android.R.id.home:
    		Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
}
