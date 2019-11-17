package com.epam.lab.batterfield;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epam.lab.battlefield.util.Constants.*;
public class Battlefield {	
	private View view = new View();
	private int[][] computerField = new int[10][10];
	private Random randomValue = new Random();
	Fleet fleet;	
	private List<BodyPart> carrierBody = new ArrayList<>();
	private List<BodyPart> battleshipBody = new ArrayList<>();
	private List<BodyPart> cruiserBody = new ArrayList<>();
	private List<BodyPart> submarineBody = new ArrayList<>();
	private List<BodyPart> destroyerBody = new ArrayList<>();
	
	private List<BodyPart> carrierBodyVicinity = new ArrayList<>();
	private List<BodyPart> battleshipBodyVicinity = new ArrayList<>();
	private List<BodyPart> cruiserBodyVicinity = new ArrayList<>();
	private List<BodyPart> submarineBodyVicinity = new ArrayList<>();	
	private BodyPart bodyPart;
	
	public Battlefield() {
		this.fleet = new Fleet();		
		this.initializeComputerBattlefieldWithShips();
	}	
    	
	public void initializeComputerBattlefieldWithShips() {		
		carrierBody = createShipAtStart(fleet.carrier);
		carrierBodyVicinity = view.createVicinityOfTheShip(carrierBody);		
		
		for (int n = 0; n < NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; n++){
			battleshipBody.clear();
			battleshipBody = createShipAtStart(fleet.battleship);			
			if( !isShipsInterrelated( battleshipBody, carrierBodyVicinity)) break;			
		}
		battleshipBodyVicinity = view.createVicinityOfTheShip(battleshipBody);		
		
		for (int n = 0; n < NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; n++){
			cruiserBody.clear();
			cruiserBody = createShipAtStart(fleet.cruiser);			
			if( !isShipsInterrelated( cruiserBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( cruiserBody, battleshipBodyVicinity) ) break;
		}
		cruiserBodyVicinity = view.createVicinityOfTheShip(cruiserBody);		
		
		for (int n = 0; n < NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; n++){
			submarineBody.clear();
			submarineBody = createShipAtStart(fleet.submarine);			
			if( !isShipsInterrelated( submarineBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( submarineBody, battleshipBodyVicinity) &&
			    !isShipsInterrelated( submarineBody, cruiserBodyVicinity)) break;
		}
		submarineBodyVicinity = view.createVicinityOfTheShip(submarineBody);
				
		for (int n = 0; n < NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; n++){
			destroyerBody.clear();
			destroyerBody = createShipAtStart(fleet.destroyer);			
			if( !isShipsInterrelated( destroyerBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, battleshipBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, cruiserBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, submarineBodyVicinity)) break;
		}				
	}	
	
	public List<BodyPart> createShipAtStart(Battleship b){		
		int randomX;
		int randomY;	
		int direction;
		boolean shipReady;		
				for (int j = 0; j < NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; j++) {
					randomX = randomValue.nextInt(10);
					randomY = randomValue.nextInt(10);
					direction = randomValue.nextInt(4);
					shipReady = false;
					if (direction == 0) { // to north
						if (randomY >= b.getNumberOfBodyParts()-1) {													
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(randomX, randomY - i, true);
								b.getBodyOfShip().add(bodyPart);
							}							
							shipReady = true;
						}
					} else if (direction == 1) { // to east
						if (randomX <= b.getNumberOfBodyParts()) {							
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(randomX + i, randomY, true);
								b.getBodyOfShip().add(bodyPart);
							}	
							shipReady = true;
						}
					} else if (direction == 2) { // to south
						if (randomY <= b.getNumberOfBodyParts()) {							
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(randomX, randomY + i, true);
								b.getBodyOfShip().add(bodyPart);
							}	
							shipReady = true;
						}
					} else { // to west
						if (randomX >= b.getNumberOfBodyParts()-1) {							
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(randomX - i, randomY, true);
								b.getBodyOfShip().add(bodyPart);
							}	
							shipReady = true;
						}
					}
					if (shipReady)
						break;
				}										
		return b.getBodyOfShip();
	}
	
	public boolean isShipsInterrelated( List<BodyPart> firstShipBody,			                            
			                            List<BodyPart> secondShipBodyVicinity) {		
		if( secondShipBodyVicinity.size() == 0 ) 
			System.out.println("=====ERROR secondShipBodyVicinity.size() == 0");		
		for (int i = 0; i < firstShipBody.size(); i++ ){
			for (int k = 0; k < secondShipBodyVicinity.size(); k++ ){				
				if( (firstShipBody.get(i).getX() == secondShipBodyVicinity.get(k).getX()) &&
					(firstShipBody.get(i).getY() == secondShipBodyVicinity.get(k).getY())	){
					return true;
				}
			}
		}
		return false;
	}
			
	public void setUserStepInCompFieldAsDamaged(String yourStep){
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));
		if ( computerField[y][x] == BLANK ) computerField[y][x] = DAMAGED;		
	}
		
	public void setUserStepInCompFieldAsMissed(String yourStep){
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));
		if ( computerField[y][x] == BLANK ) computerField[y][x] = MISSED;		
	}
	
	public void setSomePartOfBodyOfAllShipsNotAlive(String yourStep){		
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));		
		for ( Battleship bs: fleet.getFleet()) {		
			for (BodyPart bp: bs.getBodyOfShip() ) {
				if( bp.getX() == x && bp.getY() == y ) bp.setIsAlive(false);
			}		
		}
	}
	
	public boolean isAnyShipOnThisPointDamaged(String yourStep) {			
		int x = Integer.parseInt(yourStep.substring(0,1));
		int y = Integer.parseInt(yourStep.substring(1));		
		for ( Battleship bs: fleet.getFleet()) {
			for (BodyPart bp : bs.getBodyOfShip()) {
				if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {					
					return true;
				}
			}		
		}
		return false;
	}
	
	public void setBodyPartAsDamaged(String yourStep){
		int x = Integer.parseInt(yourStep.substring(0,1));
		int y = Integer.parseInt(yourStep.substring(1));
		for ( Battleship bs: fleet.getFleet()) {
			for (BodyPart bp : bs.getBodyOfShip()) {
				if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {
					bp.setIsAlive(false);					
				}
			}		
		}
	}
	
	public String getShipNameFromStep(String yourStep) {
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));		
		for ( Battleship bs: fleet.getFleet()) {
			for (BodyPart bp : bs.getBodyOfShip()) {
				if ((bp.getX() == x) && (bp.getY() == y)) {
					return bs.getName();
				}
			}		
		}
		System.out.println("===from getShipNameFromStep: there is no ship at " + yourStep);
		return "Unknown name of Ship";
	}

	public int[][] getBattlefield() {		
		return computerField;
	}	
		
}

