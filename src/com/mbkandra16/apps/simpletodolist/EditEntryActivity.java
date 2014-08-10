package com.mbkandra16.apps.simpletodolist;

import com.mbkandra16.apps.simpletodolist.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditEntryActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_entry);
		
		String name = getIntent().getStringExtra("name");
		String priority = getIntent().getStringExtra("priority");
		String duedate = getIntent().getStringExtra("duedate");
		
		EditText etName = (EditText) findViewById(R.id.etName);
		etName.setText(name);
		etName.setTextColor(Color.parseColor("#548B54"));
		etName.setSelection(etName.getText().length());
		
		EditText etPriority = (EditText) findViewById(R.id.etPriority);
		etPriority.setText(priority);
		etPriority.setTextColor(Color.parseColor("#548B54"));
		etPriority.setSelection(etPriority.getText().length());		

		EditText etDueDate = (EditText) findViewById(R.id.etDueDate);
		etDueDate.setText(duedate);
		etDueDate.setTextColor(Color.parseColor("#548B54"));
		etDueDate.setSelection(etDueDate.getText().length());	
		
		Button btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent data = new Intent();
				EditText etName = (EditText) findViewById(R.id.etName);
				data.putExtra("name", etName.getText().toString());
				EditText etPriority = (EditText) findViewById(R.id.etPriority);
				data.putExtra("priority", etPriority.getText().toString());
				EditText etDueDate = (EditText) findViewById(R.id.etDueDate);
				data.putExtra("duedate", etDueDate.getText().toString());				
				setResult(RESULT_OK, data);
				finish();
			}
			
		});
		
	}

}
