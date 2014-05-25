package com.azarena.usfinest.database;

public class HighScore implements Comparable<HighScore> {
	
	private String date;
	private int value;
	
	public HighScore() {
		super();
	}
	
	public HighScore(String date, int value) {
		super();
		this.date = date;
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(HighScore compareHighScore) {
		int compareValue = ((HighScore) compareHighScore).getValue(); 
 
		return compareValue - this.value;
	}

}
