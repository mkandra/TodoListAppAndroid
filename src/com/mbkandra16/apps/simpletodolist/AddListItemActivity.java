package com.mbkandra16.apps.simpletodolist;

import com.mbkandra16.apps.simpletodolist.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddListItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_entry);
		
	}
	
	public void SaveEntry(View v) {
		Intent data = new Intent();
		EditText etName = (EditText) findViewById(R.id.editText1);
		data.putExtra("name", etName.getText().toString());
		EditText etPriority = (EditText) findViewById(R.id.editText2);
		data.putExtra("priority", etPriority.getText().toString());
		EditText etDueDate = (EditText) findViewById(R.id.EditText01);
		data.putExtra("duedate", etDueDate.getText().toString());		
		
		setResult(RESULT_OK, data);
		
		finish();
	}
	
}
