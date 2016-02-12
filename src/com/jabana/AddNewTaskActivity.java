package com.jabana;

import com.jabana.Objects.Task;
import com.jabana.sqllite.TaskDAO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddNewTaskActivity extends Activity {
	
	private static Button btnSaveTask;
	private static EditText etTitle, etDesc;
	private static CheckBox cbLocation, cbTime;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_task);
		
		etTitle = (EditText) findViewById(R.id.etTaskTitle);
		etDesc = (EditText) findViewById(R.id.etTaskDesc);
		
		// TODO: vreme i datum za vremenski reminder
		
		
		btnSaveTask = (Button) findViewById(R.id.btnSaveNewTask);
		
		setBtnListner();
	
	}

	private void setBtnListner() {
		
		final Context context = this;
		
		btnSaveTask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				TaskDAO tdao = new TaskDAO(context);
				
				//TODO: sredi vnos
				tdao.open();
				tdao.AddTask(new Task(etTitle.getText().toString(), 2, false, false, 0, etDesc.getText().toString()));
				tdao.close();
				
				
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
