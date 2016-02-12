package com.jabana.sqllite;

import java.util.ArrayList;
import java.util.List;

import com.jabana.Objects.Task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TaskDAO {
	
	 private SQLiteDatabase database;
	 private MySQLiteHelper dbHelper;
	 private String[] allColumns = {
			 MySQLiteHelper.COLUMN_ID_TASK,
		      MySQLiteHelper.COLUMN_NAME_TASK,
		      MySQLiteHelper.COLUMN_DESC,
		      MySQLiteHelper.COLUMN_LOCATIONALREMINDER,
		      MySQLiteHelper.COLUMN_TIMEREMINDER,
		      MySQLiteHelper.COLUMN_REMINDERON,
		      MySQLiteHelper.COLUMN_LIST_ID, 
		      MySQLiteHelper.COLUMN_ACTIVE};
	 
	 public TaskDAO(Context context) {
		
		    dbHelper = new MySQLiteHelper(context);
	}
	 
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		    
		    if(database!=null){
		    
		    Log.w("moj","Baza ok" + database.getVersion() );
		    }else
		    	Log.w("moj","Baza Ne Ok" );
		  }

	  public void close() {
		  dbHelper.close();
	  }
	  
	  public Task AddTask(Task tsk){
			 ContentValues values = new ContentValues();
			 
			 values.put(MySQLiteHelper.COLUMN_NAME_TASK, tsk.getName());
			 values.put(MySQLiteHelper.COLUMN_DESC, tsk.getDesc());
			 values.put(MySQLiteHelper.COLUMN_LOCATIONALREMINDER, tsk.isLocationRe());
			 values.put(MySQLiteHelper.COLUMN_TIMEREMINDER, tsk.isTimeRe());
			 values.put(MySQLiteHelper.COLUMN_REMINDERON, tsk.getReminderTime());
			 values.put(MySQLiteHelper.COLUMN_LIST_ID, tsk.getListId());
			 values.put(MySQLiteHelper.COLUMN_ACTIVE, 1);
			 
			 Log.w("moj", "");
			 		 
			 long insertId = 				 
					 database.insert(MySQLiteHelper.TABLE_TASK,
					 null, 
					 values);
			 
			
			 Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, allColumns ,MySQLiteHelper.COLUMN_ID_TASK + " = " + insertId,null,
				        null, null, null);
			 
			 cursor.moveToFirst();
			 
			Task task = cursorToList(cursor);
			cursor.close();
			return task;
			
		 }
	  
	  
	  public List <Task> getActiveTasksByListId(int idList) {
		   
		  List<Task> comments = new ArrayList<Task>();

		  String selectQuery = "SELECT  * FROM " 
		  + MySQLiteHelper.TABLE_TASK + " WHERE "
		  + MySQLiteHelper.COLUMN_ACTIVE + " = 1";
		  
		//  Cursor cursor = db.rawQuery(selectQuery, null);

		  
		    Cursor cursor = database.rawQuery(selectQuery, null);

		    cursor.moveToFirst();
		    
		    while (!cursor.isAfterLast()) {
		      Task ttt = cursorToList(cursor);
		      comments.add(ttt);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return comments;
		  }
		 
	  private Task cursorToList(Cursor cursor) {
		 	
			Task td = new Task();
			    
				td.setId(cursor.getLong(0));		    
			    td.setName(cursor.getString(1));			    
			    td.setDesc(cursor.getString(2));			   
			    td.setList(cursor.getInt(3));
			    
			    boolean b = false;
			    if( cursor.getInt(4)==1){
			    	b=true;
			    }			    
			    td.setLocationRe(b);
			    
			    boolean b1 = false;
			    if( cursor.getInt(5)==1){
			    	b1=true;
			    }   
			    td.setLocationRe(b1);
			    
			    td.setReminderTime(cursor.getDouble(6));
			    
			    boolean b2 = false;
			    if( cursor.getInt(7)==1){
			    	b2=true;
			    }   
			    td.setActive(b2);
			    
			    return td;
		}

}
