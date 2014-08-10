package com.mbkandra16.apps.simpletodolist;

import java.util.ArrayList;

import com.mbkandra16.apps.simpletodolist.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomDataAdapter extends ArrayAdapter<TodoListEntry>{
	public CustomDataAdapter(Context context, ArrayList<TodoListEntry> entries) {
		super(context, 0, entries);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TodoListEntry entry = getItem(position);
		
		if(convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todolist, parent, false);
		}
		
		TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
		TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
		TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvDueDate);
		
		tvName.setText(entry.getName());
		tvPriority.setText(entry.getPriority());
		tvDueDate.setText(entry.getDueDate());

//		if(entry.getPriority().equals("Urgent".toString())) 
//			convertView.setBackgroundColor(Color.parseColor("#EE8262"));
//		else if(entry.getPriority().equals("High".toString())) 
//			convertView.setBackgroundColor(Color.parseColor("#6495ED"));
		
		if(entry.getPriority().equals("Urgent".toString())) {
			tvPriority.setTextColor(Color.parseColor("#EE8262"));
			//ivTaskIcon.setImageResource(R.drawable.)
		} else if(entry.getPriority().equals("High".toString())) { 
			tvPriority.setTextColor(Color.parseColor("#6495ED"));
		} else if(entry.getPriority().equals("Medium".toString())) { 
			tvPriority.setTextColor(Color.parseColor("#548B54"));	
		} else
			tvPriority.setTextColor(Color.parseColor("#548B54"));
		
		tvName.setTextColor(Color.parseColor("#CDAD00"));
		tvDueDate.setTextColor(Color.parseColor("#EE9A00"));
		
		return convertView;
	}
	
	
}
