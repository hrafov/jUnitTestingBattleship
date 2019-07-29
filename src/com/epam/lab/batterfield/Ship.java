package com.epam.lab.batterfield;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship implements shippable {
	private String name;
	private int numberOfBodyParts;
	private List<BodyPart> bodyOfShip = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfBodyParts() {
		return numberOfBodyParts;
	}

	public void setNunmberOfBodyParts(int numberOfBodyParts) {
		this.numberOfBodyParts = numberOfBodyParts;
	}

	public List<BodyPart> getBodyOfShip() {
		return bodyOfShip;
	}

	public void setBodyOfShip(List<BodyPart> bodyOfShip) {
		this.bodyOfShip = bodyOfShip;
	}	
	
	public boolean isShipAlive(){		
    	for (int i = 0; i < bodyOfShip.size(); i++){    		
    		System.out.println("+++ test from isShipAlive === i = " + i + " bodyOfShip.get(i).getIsAlive() = " + bodyOfShip.get(i).getIsAlive());
    		if( bodyOfShip.get(i).getIsAlive() ) return true; 
    	}    		
    	return false;
    }
		
}
