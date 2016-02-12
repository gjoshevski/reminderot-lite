package com.jabana;

import java.util.List;

import com.google.android.gms.internal.bt;
import com.jabana.R;
import com.jabana.Objects.ToDoList;
import com.jabana.sqllite.ToDoListsDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HomeActivity extends Activity {

	private static Button btnAddList, btnAddTask;
	final Context context = this;
	private static Spinner spinList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		btnAddList = (Button) findViewById(R.id.btnAddList);
		btnAddTask = (Button) findViewById(R.id.btnAddTask);
		spinList = (Spinner) findViewById(R.id.spinLists);
		
		setBtnListner();
		setSpinList();
	}

	private void setSpinList() {
		

	    ToDoListsDAO datasource = new ToDoListsDAO(this);
	    datasource.open();

	    List<ToDoList> values = datasource.getAllComments();
	    datasource.close();
	    
	 // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<ToDoList> adapter = new ArrayAdapter<ToDoList>(this,
	        android.R.layout.simple_spinner_dropdown_item, values);
	    
	    spinList.setAdapter(adapter);
	    
		
	}

	private void setBtnListner() {
		
		
		btnAddList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 Intent intent = new Intent(context, AddNewListActivity.class);
                 startActivity(intent);   				
				
				
			}
		});
		
		btnAddTask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(context, AddNewTaskActivity.class);
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
