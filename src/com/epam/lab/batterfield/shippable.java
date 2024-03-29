package com.epam.lab.batterfield;

import java.util.List;

public interface shippable {
	public String getName();
	public void setName(String name);
	public int getNumberOfBodyParts();
	public void setNumberOfBodyParts(int numberOfBodyParts);
	public List<BodyPart> getBodyOfShip();
	public void setBodyOfShip(List<BodyPart> bodyOfShip);
	public boolean isShipAlive();		
}
