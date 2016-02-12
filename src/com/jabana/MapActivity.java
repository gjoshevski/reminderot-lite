package com.jabana;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jabana.Objects.ToDoList;
import com.jabana.sqllite.ToDoListsDAO;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends FragmentActivity{
	
	private static GoogleMap map;
	private static Button btnSaveList;
	private static String ListTitle;
	private LatLng coordinates;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.map);			
		btnSaveList = (Button) findViewById(R.id.btnSaveListToSql);
		
		getMapAndSetMap();
		
		setBtnListener();
		
	}

	private void setBtnListener() {
		
		final ToDoListsDAO tdDao = new ToDoListsDAO(this);
		final Context context = this;

		Log.w("moj", tdDao.toString());
		
		btnSaveList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Bundle extras = getIntent().getExtras();

				if (extras != null) {
				    ListTitle = extras.getString("title");
				
				
				Toast.makeText(getApplicationContext(), ListTitle + coordinates, Toast.LENGTH_SHORT).show();
				}
				
				//TODO: Stavi limit do 5 lise za lite
				
				
				ToDoList td = new ToDoList(ListTitle, coordinates.latitude, coordinates.longitude);
				
				tdDao.open();
				tdDao.AddList(td);
				tdDao.close();
				
			 Intent intent = new Intent(context, HomeActivity.class);
             startActivity(intent); 
				
				
			}
		});
		
	}

	@Override

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void getMapAndSetMap(){
	 try{
		
		map = ((SupportMapFragment) getSupportFragmentManager()
			     .findFragmentById(R.id.mMap)).getMap();
		
		setUpMap();
		
	 }
	 catch (Exception e) {
		// TODO: handle exception
	}
	}
	
	private void setUpMap() {
		  
		 //TODO: Možnos da se mewnja tip na mapu
		  map.setMapType(GoogleMap.MAP_TYPE_NORMAL); // Normal MapView	
		
		  
		  try{
		  map.setMyLocationEnabled(true);
		  }catch (Exception e) {
			// TODO: handle exception
		  }
		  
		  map.setOnMapLongClickListener(new OnMapLongClickListener() {
			
			@Override
			public void onMapLongClick(LatLng arg0) {
				
				map.clear();
				map.addMarker(new MarkerOptions().position(arg0)
						.title(ListTitle).visible(true)
						  /*
						   * Add Snippet when clicked on marker
						   */
						 .snippet("").visible(true));
						  
				map.moveCamera(CameraUpdateFactory.newLatLng(arg0));
				
				//Toast.makeText(getApplicationContext(),Double.toString(arg0.longitude), Toast.LENGTH_LONG).show();
				 coordinates = arg0;
				
			}
		});
		  
		 }
	
	}
