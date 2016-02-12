package com.jabana.Objects;


public class Task {
	
	private long id;
	private String name;
	private String desc;
	private int listId;
	private boolean locationRe;
	private boolean timeRe;
	private double reminderTime;
	private boolean active;
	
	
	
	public long getId() {
		return id;
	}
		
	public Task(long id, String name, String desc, int list, boolean locationRe,
			boolean timeRe, double reminderTime) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.listId = list;
		this.locationRe = locationRe;
		this.timeRe = timeRe;
		this.reminderTime = reminderTime;
	}
	
	



	public Task(String name, int list, boolean locationRe, boolean timeRe,
			double reminderTime, String desc) {
		super();
		this.name = name;
		this.desc = desc;
		this.listId = list;
		this.locationRe = locationRe;
		this.timeRe = timeRe;
		this.reminderTime = reminderTime;
	}




	public Task() {
		// TODO Auto-generated constructor stub
	}




	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getList() {
		return listId;
	}
	public void setList(int list) {
		this.listId = list;
	}
	public boolean isLocationRe() {
		return locationRe;
	}
	public void setLocationRe(boolean locationRe) {
		this.locationRe = locationRe;
	}
	public boolean isTimeRe() {
		return timeRe;
	}
	public void setTimeRe(boolean timeRe) {
		this.timeRe = timeRe;
	}
	public double getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(double reminderTime) {
		this.reminderTime = reminderTime;
	}




	public String getDesc() {
		return desc;
	}




	public void setDesc(String desc) {
		this.desc = desc;
	}




	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Task [name=" + name + "]";
	}
	

}
