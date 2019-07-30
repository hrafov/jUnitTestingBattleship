package com.epam.lab.batterfield;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battlefield {	
	private int[][] he = new int[10][10];
	private int[][] battle = new int[10][10];
	private Random rand = new Random();	
	
	Battleship carrier = new Battleship("Carrier", 5);
	Battleship battleship = new Battleship("Battleship", 4);
	Battleship cruiser = new Battleship("Cruiser", 3);
	Battleship submarine = new Battleship("Submarine", 3);
	Battleship destroyer = new Battleship("Destroyer", 2);
	
	private List<BodyPart> carrierBody = new ArrayList<>();
	private List<BodyPart> battleshipBody = new ArrayList<>();
	private List<BodyPart> cruiserBody = new ArrayList<>();
	private List<BodyPart> submarineBody = new ArrayList<>();
	private List<BodyPart> destroyerBody = new ArrayList<>();
	
	private List<BodyPart> carrierBodyVicinity = new ArrayList<>();
	private List<BodyPart> battleshipBodyVicinity = new ArrayList<>();
	private List<BodyPart> cruiserBodyVicinity = new ArrayList<>();
	private List<BodyPart> submarineBodyVicinity = new ArrayList<>();
	private List<BodyPart> destroyerBodyVicinity = new ArrayList<>();
	
	List<BodyPart> vicinity = new ArrayList<>();
	private BodyPart bodyPart;	
	
// initialize all 5 Battleships	
	public void initializeComputerBattlefield() {		
		
		// Carrier and vicinity - start point of building
		carrierBody = createShipAtStart(carrier);
		carrierBodyVicinity = createVicinityOfTheShip(carrierBody);
		
		// Battleship and vicinity - start point of building
		for (int n = 0; n < 1000; n++){
			battleshipBody.clear();
			battleshipBody =createShipAtStart(battleship);
			//if battleshipBody not in vicinity or in carrierBody
			if( !isShipsInterrelated( battleshipBody, carrierBodyVicinity)) break;			
		}
		battleshipBodyVicinity = createVicinityOfTheShip(battleshipBody);
		
		// Cruiser - start point of building
		for (int n = 0; n < 1000; n++){
			cruiserBody.clear();
			cruiserBody = createShipAtStart(cruiser);
			//if battleshipBody not in in carrierBody or carrierBodyvicinity
			if( !isShipsInterrelated( cruiserBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( cruiserBody, battleshipBodyVicinity) ) break;
		}
		cruiserBodyVicinity = createVicinityOfTheShip(cruiserBody);
		
		// Submarine - start point of building
		for (int n = 0; n < 1000; n++){
			submarineBody.clear();
			submarineBody = createShipAtStart(submarine);
			//if battleshipBody not in in carrierBody or carrierBodyvicinity
			if( !isShipsInterrelated( submarineBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( submarineBody, battleshipBodyVicinity) &&
			    !isShipsInterrelated( submarineBody, cruiserBodyVicinity)) break;
		}
		submarineBodyVicinity = createVicinityOfTheShip(submarineBody);
		
		// Destroyer - start point of building
		for (int n = 0; n < 1000; n++){
			destroyerBody.clear();
			destroyerBody = createShipAtStart(destroyer);
			//if battleshipBody not in in carrierBody or carrierBodyvicinity
			if( !isShipsInterrelated( destroyerBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, battleshipBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, cruiserBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, submarineBodyVicinity)) break;
		}
		destroyerBodyVicinity = createVicinityOfTheShip(destroyerBody);
		
	}

	public int randomChoiceWhoFirst() {
		return rand.nextInt(2);
	}
	
	public void displayBattleFieldInConsole(List<BodyPart> carrierBody, List<BodyPart> battleshipBody,
			                                List<BodyPart> cruiserBody, List<BodyPart> submarineBody,
			                                List<BodyPart> destroyerBody) { 	
		for (int i = 0; i < carrierBody.size(); i++) {
			he[carrierBody.get(i).getY()][carrierBody.get(i).getX()] = 1;
		}
		for (int i = 0; i < battleshipBody.size(); i++) {
			he[battleshipBody.get(i).getY()][battleshipBody.get(i).getX()] = 2;
		}
		for (int i = 0; i < cruiserBody.size(); i++) {
			he[cruiserBody.get(i).getY()][cruiserBody.get(i).getX()] = 3;
		}
		for (int i = 0; i < submarineBody.size(); i++) {
			he[submarineBody.get(i).getY()][submarineBody.get(i).getX()] = 4;
		}
		for (int i = 0; i < destroyerBody.size(); i++) {
			he[destroyerBody.get(i).getY()][destroyerBody.get(i).getX()] = 5;
		}
		// TODO change 0-9 to 1-10 at finishing game for customer
		System.out.println("  0   1   2   3   4   5   6   7   8   9");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " "); //TODO from 0-9 to A-J
			for (int j = 0; j < 10; j++) {
				System.out.print(he[i][j] + "   ");
			}
			System.out.println("\n");
		}
	}

	//TODO not need to
	public String fromIntToString(int y) {
		String c = "Z";
		switch (y) {
		case 0:
			c = "A";
			break;
		case 1:
			c = "B";
			break;
		case 2:
			c = "C";
			break;
		case 3:
			c = "D";
			break;
		case 4:
			c = "E";
			break;
		case 5:
			c = "F";
			break;
		case 6:
			c = "G";
			break;
		case 7:
			c = "H";
			break;
		case 8:
			c = "I";
			break;
		case 9:
			c = "J";
			break;
		default:
			System.out.println("=== Error in method from09ToAJ ===");
		}
		return c;
	}
	
	public List<BodyPart> createVicinityOfTheShip(List<BodyPart> shipBody){				
		int x; //coordinates of bodyPart
		int y;
		for(int i = 0; i < shipBody.size(); i++){
			x = shipBody.get(i).getX();
			y = shipBody.get(i).getY();
			if( y > 0 ) vicinity.add(new BodyPart(x,y-1,true));
			if( y < 9 ) vicinity.add(new BodyPart(x,y+1,true));
			if( x < 9 ) vicinity.add(new BodyPart(x+1,y,true));
			if( x > 0 ) vicinity.add(new BodyPart(x-1,y,true));
			if( x < 9 && y > 0 ) vicinity.add(new BodyPart(x+1,y-1,true));
			if( x < 9 && y < 9 ) vicinity.add(new BodyPart(x+1,y+1,true));
			if( x > 0 && y < 9 ) vicinity.add(new BodyPart(x-1,y+1,true));
			if( x > 0 && y > 0 ) vicinity.add(new BodyPart(x-1,y-1,true));			
		}
		return vicinity;
	}
	
	public void displayBatterFieldWithVicinityInConsole(List<BodyPart> shipBody, List<BodyPart> ship2Body,
			    List<BodyPart> ship3Body, List<BodyPart> ship4Body, List<BodyPart> ship5Body){ //for testing
		
		    vicinity = createVicinityOfTheShip( shipBody );			
			for (int i = 0; i < vicinity.size(); i++) {
				he[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < shipBody.size(); i++) {
				he[shipBody.get(i).getY()][shipBody.get(i).getX()] = 1;
			}
			
			vicinity.clear();
			vicinity = createVicinityOfTheShip( ship2Body );
			for (int i = 0; i < vicinity.size(); i++) {
				he[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship2Body.size(); i++) {
				he[ship2Body.get(i).getY()][ship2Body.get(i).getX()] = 2;
			}
			
			vicinity.clear();
			vicinity = createVicinityOfTheShip( ship3Body );
			for (int i = 0; i < vicinity.size(); i++) {
				he[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship3Body.size(); i++) {
				he[ship3Body.get(i).getY()][ship3Body.get(i).getX()] = 3;
			}
			
			vicinity.clear();
			vicinity = createVicinityOfTheShip( ship4Body );
			for (int i = 0; i < vicinity.size(); i++) {
				he[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship4Body.size(); i++) {
				he[ship4Body.get(i).getY()][ship4Body.get(i).getX()] = 4;
			}
			
			vicinity.clear();
			vicinity = createVicinityOfTheShip( ship5Body );
			for (int i = 0; i < vicinity.size(); i++) {
				he[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship5Body.size(); i++) {
				he[ship5Body.get(i).getY()][ship5Body.get(i).getX()] = 5;
			}			
			// TODO change 0-9 to 1-10 at finishing game for customer
			System.out.println("  0   1   2   3   4   5   6   7   8   9");
			System.out.println();
			for (int i = 0; i < 10; i++) {
				System.out.print(i + " "); //TODO from 0-9 to A-J for customer
				for (int j = 0; j < 10; j++) {
					System.out.print(he[i][j] + "   ");
				}
				System.out.println("\n");
			}	
	}
	
	public List<BodyPart> createShipAtStart(Battleship b){		
		int x;
		int y;	
		int direction;
		boolean shipReady;		
				for (int j = 0; j < 10000; j++) {
					x = rand.nextInt(10);
					y = rand.nextInt(10);
					direction = rand.nextInt(4);
//					System.out.println("=== x = " + x);
//					System.out.println("=== y = " + y);
//					System.out.println("=== y in A-J format = " + fromIntToString(y));
//					System.out.println("=== direction = " + direction);
					shipReady = false;
					if (direction == 0) { // to north
						if (y >= b.getNumberOfBodyParts()-1) {													
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(x, y - i, true);
								b.getBodyOfShip().add(bodyPart);
							}							
							shipReady = true;
						}
					} else if (direction == 1) { // to east
						if (x <= b.getNumberOfBodyParts()) {							
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(x + i, y, true);
								b.getBodyOfShip().add(bodyPart);
							}	
							shipReady = true;
						}
					} else if (direction == 2) { // to south
						if (y <= b.getNumberOfBodyParts()) {							
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(x, y + i, true);
								b.getBodyOfShip().add(bodyPart);
							}	
							shipReady = true;
						}
					} else { // to west
						if (x >= b.getNumberOfBodyParts()-1) {							
							for( int i = 0; i < b.getNumberOfBodyParts(); i++ ){
								bodyPart = new BodyPart(x - i, y, true);
								b.getBodyOfShip().add(bodyPart);
							}	
							shipReady = true;
						}
					}
					if (shipReady)
						break;
				}
				//System.out.println("=== " + b.getName() + " built ===");						
		return b.getBodyOfShip();
	}
	
	// is first ship body interrelated with second ship body and second ship vicinity
	public boolean isShipsInterrelated( List<BodyPart> firstShipBody,			                            
			                            List<BodyPart> secondShipBodyVicinity){
		if( secondShipBodyVicinity.size() == 0 ) 
			System.out.println(" ===ERROR secondShipBodyVicinity.size()==0 - it is not good) ===");
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
	
	// you will not see ships at all, only steps of user
	public void displayBatterFieldForUserInFightingProcessInConsole(){
		System.out.println("  0   1   2   3   4   5   6   7   8   9");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " "); //TODO from 0-9 to A-J
			for (int j = 0; j < 10; j++) {
				System.out.print(battle[i][j] + "   ");
			}
			System.out.println("\n");
		}
		
	}
	
	// mark with 8 integer
	public void setYourStepToBattleAsDamaged(String yourStep){
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));
		battle[y][x] = 8;		
	}
	
	// mark with 7 integer
	public void setYourStepToBattleAsMissed(String yourStep){
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));
		if ( battle[y][x] == 0 ) battle[y][x] = 7;		
	}
	
	public void setSomePartOfBodyOfShipIsNotAlive(String yourStep){
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));
		//through all fleet
		for (BodyPart bp:carrierBody ) {
			if( bp.getX() == x && bp.getY() == y ) bp.setIsAlive(false);
		}
		for (BodyPart bp:battleshipBody ) {
			if( bp.getX() == x && bp.getY() == y ) bp.setIsAlive(false);
		}
		for (BodyPart bp:cruiserBody ) {
			if( bp.getX() == x && bp.getY() == y ) bp.setIsAlive(false);
		}
		for (BodyPart bp:submarineBody ) {
			if( bp.getX() == x && bp.getY() == y ) bp.setIsAlive(false);
		}
		for (BodyPart bp:destroyerBody ) {
			if( bp.getX() == x && bp.getY() == y ) bp.setIsAlive(false);
		}
		
	}
	
}
