package com.mbkandra16.apps.simpletodolist;

public class TodoListEntry {
	private long id;
	private String name;
	private String priority;
	private String dueDate;
	
	public TodoListEntry() {
		
	}
	
	public TodoListEntry(String n, String p, String d) {
		name = n;
		priority = p;
		dueDate = d;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getPriority() {
		return priority;
	}

	public String getDueDate() {
		return dueDate;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	public void setName(String n) {
		name = n;
	}

	public void setPriority(String p) {
		priority = p;
	}

	public void setDueDate(String d) {
		dueDate = d;
	}
}
