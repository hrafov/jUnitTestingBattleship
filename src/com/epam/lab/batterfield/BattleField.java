package com.epam.lab.batterfield;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.epam.lab.battlefield.util.Constants.*;
public class Battlefield {		
	private int[][] computerBattlefieldForTesting = new int[10][10];
	private int[][] computerField = new int[10][10];
	private Random randomValue = new Random();
	
	Battleship carrier = new Battleship("Carrier", 5);
	Battleship battleship = new Battleship("Battleship", 4);
	Battleship cruiser = new Battleship("Cruiser", 3);
	Battleship submarine = new Battleship("Submarine", 3);
	Battleship destroyer = new Battleship("Destroyer", 2);
	
	private List<Battleship> fleet = new ArrayList<>();
	
	private List<BodyPart> carrierBody = new ArrayList<>();
	private List<BodyPart> battleshipBody = new ArrayList<>();
	private List<BodyPart> cruiserBody = new ArrayList<>();
	private List<BodyPart> submarineBody = new ArrayList<>();
	private List<BodyPart> destroyerBody = new ArrayList<>();
	
	private List<BodyPart> carrierBodyVicinity = new ArrayList<>();
	private List<BodyPart> battleshipBodyVicinity = new ArrayList<>();
	private List<BodyPart> cruiserBodyVicinity = new ArrayList<>();
	private List<BodyPart> submarineBodyVicinity = new ArrayList<>();	
	@SuppressWarnings("unused")
	private List<BodyPart> destroyerBodyVicinity = new ArrayList<>();
	
	List<BodyPart> vicinity = new ArrayList<>();
	private BodyPart bodyPart;
	
	public Battlefield() {
		fleet.add(battleship);
		fleet.add(carrier);
		fleet.add(cruiser);
		fleet.add(destroyer);
		fleet.add(submarine);
		this.initializeComputerBattlefieldWithShips();
	}	
    	
	public void initializeComputerBattlefieldWithShips() {		
		carrierBody = createShipAtStart(carrier);
		carrierBodyVicinity = createVicinityOfTheShip(carrierBody);		
		
		for (int n = 0; n < 1000; n++){
			battleshipBody.clear();
			battleshipBody = createShipAtStart(battleship);			
			if( !isShipsInterrelated( battleshipBody, carrierBodyVicinity)) break;			
		}
		battleshipBodyVicinity = createVicinityOfTheShip(battleshipBody);		
		
		for (int n = 0; n < 1000; n++){
			cruiserBody.clear();
			cruiserBody = createShipAtStart(cruiser);			
			if( !isShipsInterrelated( cruiserBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( cruiserBody, battleshipBodyVicinity) ) break;
		}
		cruiserBodyVicinity = createVicinityOfTheShip(cruiserBody);		
		
		for (int n = 0; n < 1000; n++){
			submarineBody.clear();
			submarineBody = createShipAtStart(submarine);			
			if( !isShipsInterrelated( submarineBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( submarineBody, battleshipBodyVicinity) &&
			    !isShipsInterrelated( submarineBody, cruiserBodyVicinity)) break;
		}
		submarineBodyVicinity = createVicinityOfTheShip(submarineBody);
				
		for (int n = 0; n < 1000; n++){
			destroyerBody.clear();
			destroyerBody = createShipAtStart(destroyer);			
			if( !isShipsInterrelated( destroyerBody, carrierBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, battleshipBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, cruiserBodyVicinity) &&
			    !isShipsInterrelated( destroyerBody, submarineBodyVicinity)) break;
		}
		destroyerBodyVicinity = createVicinityOfTheShip(destroyerBody);
		
	}
		
	public void displayBattleFieldInConsole(List<BodyPart> carrierBody, List<BodyPart> battleshipBody,
			                                List<BodyPart> cruiserBody, List<BodyPart> submarineBody,
			                                List<BodyPart> destroyerBody) { 	
		for (int i = 0; i < carrierBody.size(); i++) {
			computerBattlefieldForTesting[carrierBody.get(i).getY()][carrierBody.get(i).getX()] = 1;
		}
		for (int i = 0; i < battleshipBody.size(); i++) {
			computerBattlefieldForTesting[battleshipBody.get(i).getY()][battleshipBody.get(i).getX()] = 2;
		}
		for (int i = 0; i < cruiserBody.size(); i++) {
			computerBattlefieldForTesting[cruiserBody.get(i).getY()][cruiserBody.get(i).getX()] = 3;
		}
		for (int i = 0; i < submarineBody.size(); i++) {
			computerBattlefieldForTesting[submarineBody.get(i).getY()][submarineBody.get(i).getX()] = 4;
		}
		for (int i = 0; i < destroyerBody.size(); i++) {
			computerBattlefieldForTesting[destroyerBody.get(i).getY()][destroyerBody.get(i).getX()] = 5;
		}		
		displayBattleField(computerBattlefieldForTesting);		
	}
	
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
		int x; 
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
	
	//this method only for development testing
	public void displayBattleFieldWithVicinityInConsole(List<BodyPart> shipBody, List<BodyPart> ship2Body,
			    List<BodyPart> ship3Body, List<BodyPart> ship4Body, List<BodyPart> ship5Body){		
		    vicinity = createVicinityOfTheShip(shipBody);			
			for (int i = 0; i < vicinity.size(); i++) {
				computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < shipBody.size(); i++) {
				computerBattlefieldForTesting[shipBody.get(i).getY()][shipBody.get(i).getX()] = 1;
			}			
			vicinity.clear();
			vicinity = createVicinityOfTheShip(ship2Body);
			for (int i = 0; i < vicinity.size(); i++) {
				computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship2Body.size(); i++) {
				computerBattlefieldForTesting[ship2Body.get(i).getY()][ship2Body.get(i).getX()] = 2;
			}			
			vicinity.clear();
			vicinity = createVicinityOfTheShip(ship3Body);
			for (int i = 0; i < vicinity.size(); i++) {
				computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship3Body.size(); i++) {
				computerBattlefieldForTesting[ship3Body.get(i).getY()][ship3Body.get(i).getX()] = 3;
			}			
			vicinity.clear();
			vicinity = createVicinityOfTheShip(ship4Body);
			for (int i = 0; i < vicinity.size(); i++) {
				computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship4Body.size(); i++) {
				computerBattlefieldForTesting[ship4Body.get(i).getY()][ship4Body.get(i).getX()] = 4;
			}			
			vicinity.clear();
			vicinity = createVicinityOfTheShip(ship5Body);
			for (int i = 0; i < vicinity.size(); i++) {
				computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(i).getX()] = 8;
			}
			for (int i = 0; i < ship5Body.size(); i++) {
				computerBattlefieldForTesting[ship5Body.get(i).getY()][ship5Body.get(i).getX()] = 5;
			}			
			displayBattleField(computerBattlefieldForTesting);
	}
		
	public List<BodyPart> createShipAtStart(Battleship b){		
		int randomX;
		int randomY;	
		int direction;
		boolean shipReady;		
				for (int j = 0; j < 10000; j++) {
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
		
	public void displayBattleField( int[][] battlefield) {		
		System.out.println("   0   1   2   3   4   5   6   7   8   9\n");		
		for (int i = 0; i <= 9; i++) {
			System.out.print(i + "  "); 
			for (int j = 0; j <= 9; j++) {
				System.out.print(battlefield[i][j] + "   ");
			}
			System.out.println("\n");
		}		
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
		for ( Battleship bs: fleet) {		
			for (BodyPart bp: bs.getBodyOfShip() ) {
				if( bp.getX() == x && bp.getY() == y ) bp.setIsAlive(false);
			}		
		}
	}
	
	public int[][] getBattlefield(){
		return this.computerField;
	}
	
	public List<Battleship> getFleet() {
		return fleet;
	}		
	
}

