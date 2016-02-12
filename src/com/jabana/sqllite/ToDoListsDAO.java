package com.jabana.sqllite;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.internal.cu;
import com.jabana.Objects.ToDoList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ToDoListsDAO {
	
	 private SQLiteDatabase database;
	 private MySQLiteHelper dbHelper;
	 private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
		      MySQLiteHelper.COLUMN_NAME,MySQLiteHelper.COLUMN_LATITUDE,MySQLiteHelper.COLUMN_LONGITUDE };
	 
	 public ToDoListsDAO(Context context) {
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
	  
	 public ToDoList AddList(ToDoList td){
		 ContentValues values = new ContentValues();
		 
		 values.put(MySQLiteHelper.COLUMN_NAME, td.getTitle());
		 values.put(MySQLiteHelper.COLUMN_LATITUDE, td.getLatitude());
		 values.put(MySQLiteHelper.COLUMN_LONGITUDE, td.getLongitude());
		 
		 Log.w("moj", "");
		 		 
		 long insertId = 				 
				 database.insert(MySQLiteHelper.TABLE_TB_TODO_LIST,
				 null, 
				 values);
		 
		
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_TB_TODO_LIST, allColumns ,MySQLiteHelper.COLUMN_ID + " = " + insertId,null,
			        null, null, null);
		 
		 cursor.moveToFirst();
		 
		ToDoList tdLi = cursorToList(cursor);
		cursor.close();
		return tdLi;		 
	 }
	 
	 public void deleteList(ToDoList td) {
		    long id = td.getId();
		    System.out.println("List deleted with id: " + id);
		    database.delete(MySQLiteHelper.TABLE_TB_TODO_LIST, MySQLiteHelper.COLUMN_ID
		        + " = " + id, null);
		  }
	 
	 
	 public List<ToDoList> getAllComments() {
		    List<ToDoList> comments = new ArrayList<ToDoList>();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_TB_TODO_LIST,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    
		    while (!cursor.isAfterLast()) {
		      ToDoList ttt = cursorToList(cursor);
		      comments.add(ttt);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return comments;
		  }
	 

	private ToDoList cursorToList(Cursor cursor) {
		 	
		ToDoList td = new ToDoList();
		    
			td.setId(cursor.getLong(0));		    
		    td.setTitle(cursor.getString(1));
		    td.setLatitude(cursor.getFloat(2));
		    td.setLongitude(cursor.getFloat(3));
		    
		    return td;
	}
}
