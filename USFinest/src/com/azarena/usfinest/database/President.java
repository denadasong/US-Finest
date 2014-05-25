package com.azarena.usfinest.database;

public class President {
	int resourceId;
	String president;
	
	public President(int resourceId, String president) {
		super();
		this.resourceId = resourceId;
		this.president = president;
	}
	
	public President() {
	}

	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	
}
