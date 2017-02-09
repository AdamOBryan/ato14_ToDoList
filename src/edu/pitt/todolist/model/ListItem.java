package edu.pitt.todolist.model;

import java.sql.Timestamp;
import java.util.Calendar;

public class ListItem {
	private int id;
	private String description;
	private Timestamp timestamp;
	
	public ListItem(String description, Timestamp timestamp, int id) {
		this.description = description;
		this.timestamp = timestamp;
		this.id = id;
	}
	
	public ListItem(String description) {
		this.description = description;
		timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
