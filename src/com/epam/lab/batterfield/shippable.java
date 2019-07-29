package com.epam.lab.batterfield;

import java.util.List;

public interface shippable {
	public String getName();
	public void setName(String name);
	public int getNumberOfBodyParts();
	public void setNunmberOfBodyParts(int numberOfBodyParts);
	public List<BodyPart> getBodyOfShip();
	public void setBodyOfShip(List<BodyPart> bodyOfShip);
	public boolean isShipAlive();	
	//TODO may be method buildAtStart is not needed?	
	//public void buildAtStart();
	
}
