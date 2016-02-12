package com.jabana;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddNewListActivity extends Activity {
	
	private static EditText ListTitle;
    private static Button btnAddLocation;
	final Context context = this;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_list);
		
		ListTitle = (EditText) findViewById(R.id.edtxListTitle);
		btnAddLocation = (Button) findViewById(R.id.btnPickLocation);
		
		setBtnListner();
	
	}

	private void setBtnListner() {
		
		btnAddLocation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 Intent intent = new Intent(context, MapActivity.class);
				 intent.putExtra("title", ListTitle.getText().toString());
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


}
