package com.epam.lab.batterfield;

public class Battleship extends Ship implements shippable {	
			
	public Battleship( String name, int numberOfBodyParts){		
		this.setName(name);
		this.setNunmberOfBodyParts(numberOfBodyParts);		
	}    
	
}
