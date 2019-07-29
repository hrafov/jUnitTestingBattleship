package com.epam.lab.batterfield;

public class BodyPart {
	private int x;
	private int y;
	private String c;
	private boolean isAlive; 

	public BodyPart(int x, int y, boolean isAlive) {
		this.x = x;
		this.y = y;
		this.isAlive = isAlive;
	}
	
	public BodyPart(int x, String c, boolean isAlive) {
		this.x = x;
		this.c = c;
		this.isAlive = isAlive;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}
	
	public boolean getIsAlive() {
		return isAlive;
	}
	
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}