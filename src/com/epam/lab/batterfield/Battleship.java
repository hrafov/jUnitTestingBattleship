package com.epam.lab.batterfield;

public class Battleship extends Ship implements shippable {	
	//TODO if I need of the variable name at all?
	//private List<BodyPart> l;	
		
	public Battleship( String name, int numberOfBodyParts){		
		this.setName(name);
		this.setNunmberOfBodyParts(numberOfBodyParts);
		//l = this.getBodyOfShip(); //TODO its not need to
		//mocking buildAtStart();
	}    
	
    //TODO  mock method for testing - delete it? 
//	public void buildAtStart(){
//		//List<BodyPart> bodyOfShip = new ArrayList<>(); 
//		//System.out.println(" == this.getNumberOfBodyParts() = " + this.getNumberOfBodyParts());
//		for( int i=0; i < this.getNumberOfBodyParts(); i++){
//			BodyPart b = new BodyPart(1,'A',true);
//			l.add(b);
//			//System.out.println("=== i = " + i);//Debug
//		}
//	}

}
