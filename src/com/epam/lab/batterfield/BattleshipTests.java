package com.epam.lab.batterfield;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import static com.epam.lab.battlefield.util.Constants.*;
import org.junit.Test;

public class BattleshipTests { 

	
	Battlefield b;
	List<BodyPart> bodyOfTestShip;
	DialogWithComputer dialog;
	int[][] battle;
		
	@Test
	public void sumOfAllShipElements() {
		b = new Battlefield();		
		int sum = 0;
		sum += b.carrier.getBodyOfShip().size();
		sum += b.battleship.getBodyOfShip().size();
		sum += b.cruiser.getBodyOfShip().size();
		sum += b.submarine.getBodyOfShip().size();
		sum += b.destroyer.getBodyOfShip().size();
		assertEquals(17, sum);
	}	
	
	@Test
	public void isMethodIsAliveInBattleshipWorkingAfterInitialization() {
		b = new Battlefield();					
		assertEquals(b.battleship.isShipAlive(),true);		
		assertEquals(b.carrier.isShipAlive(),true); 		
		assertEquals(b.cruiser.isShipAlive(),true);		
		assertEquals(b.destroyer.isShipAlive(),true);		
		assertEquals(b.submarine.isShipAlive(),true); 
	}
	
	@Test
	public void translationFromIntToString() {	
		b = new Battlefield();
		assertEquals( b.fromIntToString(0), "A" ); 
		assertEquals( b.fromIntToString(1), "B" );
		assertEquals( b.fromIntToString(2), "C" );
		assertEquals( b.fromIntToString(3), "D" );
		assertEquals( b.fromIntToString(4), "E" );
		assertEquals( b.fromIntToString(5), "F" );
		assertEquals( b.fromIntToString(6), "G" );
		assertEquals( b.fromIntToString(7), "H" );
		assertEquals( b.fromIntToString(8), "I" );
		assertEquals( b.fromIntToString(9), "J" );
	}
	
	@Test 
	public void isUserInputForHitGrammaticallyValid() {		
		dialog = new DialogWithComputer(b);
		String input;		
		boolean isValid = true;		
		for( int x = 0; x <= 9; x++ ){
			for( int y = 0; y <= 9; y++ ){
				input = Integer.toString(x) + Integer.toString(y);				
				if ( ! dialog.isInputForHitValid(input) ) {
					isValid = false;
					break;
				}
			}
		}		
		assertTrue( isValid);		
		assertTrue( !dialog.isInputForHitValid(null));
		assertTrue( !dialog.isInputForHitValid(""));
		assertTrue( !dialog.isInputForHitValid("3"));
		assertTrue( !dialog.isInputForHitValid("dd"));
		assertTrue( !dialog.isInputForHitValid("12345"));
		assertTrue( !dialog.isInputForHitValid("Too long string"));		
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForBattleship() {
		b = new Battlefield();		
		int x0 = b.battleship.getBodyOfShip().get(0).getX();
		int y0 = b.battleship.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.battleship.getBodyOfShip().get(0).getIsAlive() );		
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveAndIsShipAliveWorkingForBattleship() {
		b = new Battlefield();		
		int x0 = b.battleship.getBodyOfShip().get(0).getX();
		int y0 = b.battleship.getBodyOfShip().get(0).getY();
		int x1 = b.battleship.getBodyOfShip().get(1).getX();
		int y1 = b.battleship.getBodyOfShip().get(1).getY();
		int x2 = b.battleship.getBodyOfShip().get(2).getX();
		int y2 = b.battleship.getBodyOfShip().get(2).getY();
		int x3 = b.battleship.getBodyOfShip().get(3).getX();
		int y3 = b.battleship.getBodyOfShip().get(3).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x1) + Integer.toString(y1));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x2) + Integer.toString(y2));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x3) + Integer.toString(y3));
		assertEquals(false, b.battleship.isShipAlive());		
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForCarrier() {
		b = new Battlefield();		
		int x0 = b.carrier.getBodyOfShip().get(0).getX();
		int y0 = b.carrier.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.carrier.getBodyOfShip().get(0).getIsAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveAndIsShipAliveWorkingForCarrier() {
		b = new Battlefield();		
		int x0 = b.carrier.getBodyOfShip().get(0).getX();
		int y0 = b.carrier.getBodyOfShip().get(0).getY();
		int x1 = b.carrier.getBodyOfShip().get(1).getX();
		int y1 = b.carrier.getBodyOfShip().get(1).getY();
		int x2 = b.carrier.getBodyOfShip().get(2).getX();
		int y2 = b.carrier.getBodyOfShip().get(2).getY();
		int x3 = b.carrier.getBodyOfShip().get(3).getX();
		int y3 = b.carrier.getBodyOfShip().get(3).getY();
		int x4 = b.carrier.getBodyOfShip().get(4).getX();
		int y4 = b.carrier.getBodyOfShip().get(4).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x1) + Integer.toString(y1));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x2) + Integer.toString(y2));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x3) + Integer.toString(y3));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x4) + Integer.toString(y4));
		assertEquals(false, b.carrier.isShipAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForCruiser() {
		b = new Battlefield();		
		int x0 = b.cruiser.getBodyOfShip().get(0).getX();
		int y0 = b.cruiser.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.cruiser.getBodyOfShip().get(0).getIsAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForDestroyer() {
		b = new Battlefield();		
		int x0 = b.destroyer.getBodyOfShip().get(0).getX();
		int y0 = b.destroyer.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.destroyer.getBodyOfShip().get(0).getIsAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForSubmarine() {
		b = new Battlefield();		
		int x0 = b.submarine.getBodyOfShip().get(0).getX();
		int y0 = b.submarine.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.submarine.getBodyOfShip().get(0).getIsAlive() ); 
	}
	
	@Test
	public void isMethodSetYourStepToBattleAsDamagedWorking() {
		b = new Battlefield();		
		b.setUserStepInCompFieldAsDamaged("00");
		b.setUserStepInCompFieldAsDamaged("99");		
		assertEquals( DAMAGED, b.getBattlefield()[0][0]);
		assertEquals( DAMAGED, b.getBattlefield()[9][9]);
		assertFalse( b.getBattlefield()[1][1] == DAMAGED);		
	}
	
	
	@Test
	public void isMethodSetYourStepToBattleAsMissedWorking() {
		b = new Battlefield();		
		b.setUserStepInCompFieldAsMissed("00");
		b.setUserStepInCompFieldAsMissed("99");				
		assertEquals( MISSED, b.getBattlefield()[0][0]);
		assertEquals( MISSED, b.getBattlefield()[9][9]);
		assertFalse( b.getBattlefield()[1][1] == MISSED);		
	}
	
	@Test
	public void isMethodSetYourStepToBattleAsMissedInTheCenterOfWorking() {
		b = new Battlefield();		
		b.setUserStepInCompFieldAsMissed("01");
		b.setUserStepInCompFieldAsMissed("12");				
		assertEquals( MISSED, b.getBattlefield()[1][0]);
		assertEquals( MISSED, b.getBattlefield()[2][1]);
		assertFalse( b.getBattlefield()[1][1] == MISSED);		
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInCenter() {
		b = new Battlefield();					
		dialog = new DialogWithComputer(b);				
        dialog.markAllAroundSquaresAsMissed("11");                
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[0][0]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[0][1]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[0][2]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[1][0]);		
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[1][2]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[2][0]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[2][1]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[2][2]);
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInLeftUpperCorner() {
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);        
        dialog.markAllAroundSquaresAsMissed("00");		
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[0][1]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[1][0]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[1][1]);		
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInRightUpperCorner() {
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);        
        dialog.markAllAroundSquaresAsMissed("90");		
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[0][8]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[1][8]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[1][9]);		
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInLeftDownCorner() {
		b = new Battlefield();
		int[][] battleWithComputerEys = new int[10][10];				
		dialog = new DialogWithComputer(b);        
        battleWithComputerEys = dialog.getbattleWithComputerEys();
        dialog.markAllAroundSquaresAsMissed("09");		
		assertEquals(MISSED, battleWithComputerEys[8][0]);
		assertEquals(MISSED, battleWithComputerEys[8][1]);
		assertEquals(MISSED, battleWithComputerEys[9][1]);		
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInRightDownCorner() {
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);        
        dialog.markAllAroundSquaresAsMissed("99");		
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[8][8]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[8][9]);
		assertEquals(MISSED, dialog.getbattleWithComputerEys()[9][8]);		
	}
	
	@Test
	public void isnextDamagedStrategyStepWorkingInCenter() {
		b = new Battlefield();
		battle = new int[10][10];				
		dialog = new DialogWithComputer(b);        
        battle = dialog.getbattleWithComputerEys();
		assertEquals("10", dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to north
		battle[0][1] = MISSED;		
		assertEquals("21", dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to east
		battle[1][2] = MISSED;
		assertEquals("12", dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to south
		battle[2][1] = MISSED;
		assertEquals("01", dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to west
	}
	
	@Test
	public void isStringIntWorking() {
		b = new Battlefield();					
		dialog = new DialogWithComputer(b); 
		assertEquals(true, dialog.isStringInt("12"));
		assertEquals(false,dialog.isStringInt("if"));
		assertEquals(false,dialog.isStringInt("if I am a good negotiator?"));
		assertEquals(false,dialog.isStringInt(""));
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToNord() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("23");
		l.add("22");
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("21",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(2, 2)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToNordNearBorder() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("41");
		l.add("40");
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("42",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(4, 0)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToEast() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("63");
		l.add("73");
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("83",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(7, 3)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToEastNearBorder() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("89");
		l.add("99");
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("79",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(9, 9)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToSouth() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("66");
		l.add("67");
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("68",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(6, 7)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToSouthNearBorder() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("08");
		l.add("09");		
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("07",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(0, 9)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToWest() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("25");
		l.add("15");
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("05",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(1, 5)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToWestNearBorder() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		List<String> l = new ArrayList<>();
		l.add("10");
		l.add("00");
		dialog.setCompLogsForKillingShip(l);				
		assertEquals("20",dialog.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(0, 0)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstUp() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		//dialog.getbattleWithComputerEys()[][] = 				
		assertEquals("37",dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstRight() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		dialog.getbattleWithComputerEys()[7][3] = 7;				
		assertEquals("48",dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstDown() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		dialog.getbattleWithComputerEys()[7][3] = 7;
		dialog.getbattleWithComputerEys()[8][4] = 7;
		assertEquals("39",dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstLeft() {		
		b = new Battlefield();						
		dialog = new DialogWithComputer(b);
		dialog.getbattleWithComputerEys()[7][3] = 7;
		dialog.getbattleWithComputerEys()[8][4] = 7;
		dialog.getbattleWithComputerEys()[9][3] = 7;
		assertEquals("28",dialog.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test
	//we are working with class Ship
	public void isMethodIsAliveInBattleshipWorkingForCarrierIfAlive() {		
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,true));
		bodyOfTestShip.add(new BodyPart(0,2,true));
		bodyOfTestShip.add(new BodyPart(0,3,true));
		bodyOfTestShip.add(new BodyPart(0,4,true));		
		b.carrier.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.carrier.isShipAlive()); 
	}
	
	@Test
	//we are working with class Ship
	public void isMethodIsAliveInBattleshipWorkingForCarrierIfNotAlive() {		
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,false));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		bodyOfTestShip.add(new BodyPart(0,2,false));
		bodyOfTestShip.add(new BodyPart(0,3,false));
		bodyOfTestShip.add(new BodyPart(0,4,false));		
		b.carrier.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, b.carrier.isShipAlive()); 
	}
	
	@Test
	//we are working with class Ship
	public void isMethodIsAliveInBattleshipWorkingForCarrierIfNotAliveWhen1BodyPartIsNotAlive() {		
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		bodyOfTestShip.add(new BodyPart(0,2,false));
		bodyOfTestShip.add(new BodyPart(0,3,false));
		bodyOfTestShip.add(new BodyPart(0,4,false));		
		b.carrier.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.carrier.isShipAlive()); 
	}
	
	@Test
	public void isShipAliveWorkingWhenShipIsAlive() {
		b = new Battlefield();
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,true));
		b.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.destroyer.isShipAlive()); 
	}
	
	@Test
	public void isShipAliveWorkingWhenShipIsNotAlive() {
		b = new Battlefield();
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,false));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, b.destroyer.isShipAlive()); 
	}
	
	@Test
	public void isShipAliveWorkingWhenShipIsParticularlyAlive() {
		b = new Battlefield();
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.destroyer.isShipAlive()); 
	}
	
	@Test
	public void isShipHitWorkingWhenShipIsAlive() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,true));
		b.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, dialog.isShipHit(b.destroyer.getName()) ); 
	}
	
	@Test
	public void isShipHitWorkingWhenShipIsNotAlive() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,false));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, dialog.isShipHit(b.destroyer.getName()) ); 
	}
	
	@Test	
	public void isShipHitWorkingWhenShipIsParticularlyAlive() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, dialog.isShipHit(b.destroyer.getName()) ); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4CarrierWorking() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		String step = Integer.toString(b.carrier.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.carrier.getBodyOfShip().get(0).getY());
		assertEquals("Carrier", dialog.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4BattleshipWorking() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		String step = Integer.toString(b.battleship.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.battleship.getBodyOfShip().get(0).getY());
		assertEquals("Battleship", dialog.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4CruiserWorking() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		String step = Integer.toString(b.cruiser.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.cruiser.getBodyOfShip().get(0).getY());
		assertEquals("Cruiser", dialog.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4SubmarineWorking() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		String step = Integer.toString(b.submarine.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.submarine.getBodyOfShip().get(0).getY());
		assertEquals("Submarine", dialog.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4DestroyerWorking() {
		b = new Battlefield();
		DialogWithComputer dialog = new DialogWithComputer(b);
		String step = Integer.toString(b.destroyer.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.destroyer.getBodyOfShip().get(0).getY());
		assertEquals("Destroyer", dialog.getShipNameFromStep(step)); 
	}
	
	//TODO test markFieldAroundDeadShipAsMissed()
	
	@Test
	//for template
	public void method4Copy() {
		int a=0;
		int b=0;
		assertEquals(a,b); 
	}

}
