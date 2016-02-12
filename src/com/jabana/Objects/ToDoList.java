package com.jabana.Objects;

import android.text.Editable;

public class ToDoList {
	
	private long id;
	private String title;
	private float latitude;
	private float longitude;
	
	
	
	public ToDoList() {
		super();
	}
	public ToDoList(String title, double latitude2, double longitude2) {
		super();
		this.title = title;
		this.latitude = (float) latitude2;
		this.longitude = (float) longitude2;
	}
	public ToDoList(long id, String title, float latitude, float longitude) {
		super();
		this.id = id;
		this.title = title;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return  title;
	}
	
	

}
