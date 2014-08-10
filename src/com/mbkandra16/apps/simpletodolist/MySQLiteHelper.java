package com.mbkandra16.apps.simpletodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{
	private static final String TAG = "MySQLiteHelper";
	
	public static final String TABLE_TODOLIST_ITEMS = "TodoList";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TASK_NAME = "name";
	public static final String COLUMN_TASK_PRIORITY = "priority";
	public static final String COLUMN_TASK_DUE_DATE = "duedate";
	
	private static final String DATABASE_NAME = "TodoList.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_TODOLIST_ITEMS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_TASK_NAME
			+ " text, " + COLUMN_TASK_PRIORITY
			+ " text, " + COLUMN_TASK_DUE_DATE
			+ " text);";

	
	public MySQLiteHelper(Context context) {
		super(context,  DATABASE_NAME, null, DATABASE_VERSION);
		Log.e(TAG, "MySQLiteHelper E");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e(TAG, "onCreate");
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOLIST_ITEMS);
		onCreate(db);
	}
}
