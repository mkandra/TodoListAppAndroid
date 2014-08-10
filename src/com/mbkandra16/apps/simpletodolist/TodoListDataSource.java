package com.mbkandra16.apps.simpletodolist;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TodoListDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_TASK_NAME,
			MySQLiteHelper.COLUMN_TASK_PRIORITY,
			MySQLiteHelper.COLUMN_TASK_DUE_DATE};
	
	public TodoListDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public void updateEntry(TodoListEntry newData) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_TASK_NAME, newData.getName());
		values.put(MySQLiteHelper.COLUMN_TASK_PRIORITY, newData.getPriority());
		values.put(MySQLiteHelper.COLUMN_TASK_DUE_DATE, newData.getDueDate());	
		database.update(MySQLiteHelper.TABLE_TODOLIST_ITEMS, values, MySQLiteHelper.COLUMN_ID + " =?", new String[] {String.valueOf(newData.getId())});
	}
	
	public TodoListEntry createEntry(String n, String p, String d) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_TASK_NAME, n);
		values.put(MySQLiteHelper.COLUMN_TASK_PRIORITY, p);
		values.put(MySQLiteHelper.COLUMN_TASK_DUE_DATE, d);
		long insertId = database.insert(MySQLiteHelper.TABLE_TODOLIST_ITEMS, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TODOLIST_ITEMS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		TodoListEntry entry = cursorToTodoListEntry(cursor);
		cursor.close();
		return entry;
	}
	
	public void deleteEntry(TodoListEntry entry) {
		long id = entry.getId();
		database.delete(MySQLiteHelper.TABLE_TODOLIST_ITEMS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	public ArrayList<TodoListEntry> getAllEntries() {
		ArrayList<TodoListEntry> data = new ArrayList<TodoListEntry>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TODOLIST_ITEMS, allColumns,
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			TodoListEntry entry = cursorToTodoListEntry(cursor);
			data.add(entry);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	private TodoListEntry cursorToTodoListEntry(Cursor cursor) {
		TodoListEntry entry = new TodoListEntry();
		entry.setId(cursor.getLong(0));
		entry.setName(cursor.getString(1));
		entry.setPriority(cursor.getString(2));
		entry.setDueDate(cursor.getString(3));
		
		return entry;
	}
	
}
