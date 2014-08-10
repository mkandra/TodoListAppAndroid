package com.mbkandra16.apps.simpletodolist;

import java.util.ArrayList;

import com.mbkandra16.apps.simpletodolist.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class TodoListActivity extends Activity {
	private static final String TAG = "CustomListActivity";
	
	private TodoListDataSource datasource;
	
	CustomDataAdapter adapter;
	ArrayList<TodoListEntry> arrayOfEntries;
	ListView listView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        
        datasource = new TodoListDataSource(this);
        datasource.open();
        
        populateUsersList();
        
        setupListViewListener();
        
        setupListEditListener();
    }

    private void setupListEditListener() {
    	listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent EditFormIntent = new Intent("com.mbkandra16.apps.simpletodolist.EDIT_ENTRY");
				TodoListEntry temp = arrayOfEntries.get(position);
				EditFormIntent.putExtra("name", temp.getName());
				EditFormIntent.putExtra("priority", temp.getPriority());
				EditFormIntent.putExtra("duedate", temp.getDueDate());
				EditFormIntent.putExtra("position", position);
				Log.e(TAG, "Starting EDIT ENTRY ACTIVITY");
				startActivityForResult(EditFormIntent, position);				
			}
    		
    	});
    }
    
    private void setupListViewListener() {
    	listView.setOnItemLongClickListener(new OnItemLongClickListener() {
    		public boolean onItemLongClick(AdapterView<?> parent,
    				View view, int position, long rowId) {
    			TodoListEntry entry = arrayOfEntries.get(position);
    			datasource.deleteEntry(entry);
    			arrayOfEntries.remove(position);
    			adapter.notifyDataSetChanged();
    			return true;
    		}
    	});
    }
    
	private void populateUsersList() {
		arrayOfEntries = datasource.getAllEntries();
		adapter = new CustomDataAdapter(this, arrayOfEntries);
		listView = (ListView) findViewById(R.id.lvUsers);
		listView.setAdapter(adapter);
	}
	
	public void addUserEntry(View v) {
		Intent addButtonIntent = new Intent("com.mbkandra16.apps.simpletodolist.ADD_ENTRY");
		startActivityForResult(addButtonIntent, 0xFFFF);
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK) {
			String name = data.getExtras().getString("name");
			String priority = data.getExtras().getString("priority");
			String duedate = data.getExtras().getString("duedate");
			if(requestCode == 0xFFFF) {
				TodoListEntry temp = datasource.createEntry(name, priority, duedate);
				Log.e(TAG, "User created with id " + temp.getId());
				adapter.add(temp);
			} else {
				TodoListEntry temp = arrayOfEntries.get(requestCode);
				temp.setName(name);
				temp.setPriority(priority);
				temp.setDueDate(duedate);
				Log.e(TAG, "Updating entry with id " + temp.getId());
				datasource.updateEntry(temp);
				arrayOfEntries.set(requestCode, temp);
				adapter.notifyDataSetChanged();
			}
		}
	}
}
