package com.jabana.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper  extends SQLiteOpenHelper {

	public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	// All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 4;
 
    // Database Name
    public static final String DATABASE_NAME = "reminderot_db";
 
    // todList table name
    public static final String TABLE_TB_TODO_LIST = "todo_lists";
 
    // Contacts Table Columns names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "title";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    
    
 // todList table name
    public static final String TABLE_TASK = "task";
 
    // Contacts Table Columns names
    public static final String COLUMN_ID_TASK = "id";
    public static final String COLUMN_NAME_TASK = "title";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_LOCATIONALREMINDER = "locationRe";
    public static final String COLUMN_TIMEREMINDER = "timeRe";
    public static final String COLUMN_REMINDERON = "remindertime";
    public static final String COLUMN_LIST_ID = "listId";
    public static final String COLUMN_ACTIVE = "active";

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        
    	String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TB_TODO_LIST + "("
                + COLUMN_ID + " integer primary key autoincrement," + COLUMN_NAME + " TEXT,"
                + COLUMN_LATITUDE + " REAL," + COLUMN_LONGITUDE + " REAL" +")";
          	
    	
        db.execSQL(CREATE_CONTACTS_TABLE);
        
        String CREATE_TASK_TABLE= "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID_TASK + " integer primary key autoincrement," 
        		+ COLUMN_NAME_TASK + " TEXT,"
                + COLUMN_DESC + " TEXT," 
                + COLUMN_LIST_ID + " INTEGER,"
                + COLUMN_REMINDERON + " REAL,"
                + COLUMN_LOCATIONALREMINDER + " INTEGER," 
                + COLUMN_TIMEREMINDER + " INTEGER," 
                + COLUMN_ACTIVE + " INTEGER"+")";
        
        db.execSQL(CREATE_TASK_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TB_TODO_LIST );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK );
 
        // Create tables again
        onCreate(db);
    }


}
