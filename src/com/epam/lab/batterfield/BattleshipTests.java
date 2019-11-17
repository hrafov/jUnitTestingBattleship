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
		sum += b.fleet.carrier.getBodyOfShip().size();
		sum += b.fleet.battleship.getBodyOfShip().size();
		sum += b.fleet.cruiser.getBodyOfShip().size();
		sum += b.fleet.submarine.getBodyOfShip().size();
		sum += b.fleet.destroyer.getBodyOfShip().size();
		assertEquals(17, sum);
	}	
	
	@Test
	public void isMethodIsAliveInBattleshipWorkingAfterInitialization() {
		b = new Battlefield();					
		assertEquals(b.fleet.battleship.isShipAlive(),true);		
		assertEquals(b.fleet.carrier.isShipAlive(),true); 		
		assertEquals(b.fleet.cruiser.isShipAlive(),true);		
		assertEquals(b.fleet.destroyer.isShipAlive(),true);		
		assertEquals(b.fleet.submarine.isShipAlive(),true); 
	}
			
	@Test 
	public void isUserInputForHitGrammaticallyValid() {		
		dialog = new DialogWithComputer(new Battlefield());
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
		int x0 = b.fleet.battleship.getBodyOfShip().get(0).getX();
		int y0 = b.fleet.battleship.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.fleet.battleship.getBodyOfShip().get(0).getIsAlive() );		
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveAndIsShipAliveWorkingForBattleship() {
		b = new Battlefield();		
		int x0 = b.fleet.battleship.getBodyOfShip().get(0).getX();
		int y0 = b.fleet.battleship.getBodyOfShip().get(0).getY();
		int x1 = b.fleet.battleship.getBodyOfShip().get(1).getX();
		int y1 = b.fleet.battleship.getBodyOfShip().get(1).getY();
		int x2 = b.fleet.battleship.getBodyOfShip().get(2).getX();
		int y2 = b.fleet.battleship.getBodyOfShip().get(2).getY();
		int x3 = b.fleet.battleship.getBodyOfShip().get(3).getX();
		int y3 = b.fleet.battleship.getBodyOfShip().get(3).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x1) + Integer.toString(y1));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x2) + Integer.toString(y2));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x3) + Integer.toString(y3));
		assertEquals(false, b.fleet.battleship.isShipAlive());		
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForCarrier() {
		b = new Battlefield();		
		int x0 = b.fleet.carrier.getBodyOfShip().get(0).getX();
		int y0 = b.fleet.carrier.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.fleet.carrier.getBodyOfShip().get(0).getIsAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveAndIsShipAliveWorkingForCarrier() {
		b = new Battlefield();		
		int x0 = b.fleet.carrier.getBodyOfShip().get(0).getX();
		int y0 = b.fleet.carrier.getBodyOfShip().get(0).getY();
		int x1 = b.fleet.carrier.getBodyOfShip().get(1).getX();
		int y1 = b.fleet.carrier.getBodyOfShip().get(1).getY();
		int x2 = b.fleet.carrier.getBodyOfShip().get(2).getX();
		int y2 = b.fleet.carrier.getBodyOfShip().get(2).getY();
		int x3 = b.fleet.carrier.getBodyOfShip().get(3).getX();
		int y3 = b.fleet.carrier.getBodyOfShip().get(3).getY();
		int x4 = b.fleet.carrier.getBodyOfShip().get(4).getX();
		int y4 = b.fleet.carrier.getBodyOfShip().get(4).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x1) + Integer.toString(y1));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x2) + Integer.toString(y2));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x3) + Integer.toString(y3));
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x4) + Integer.toString(y4));
		assertEquals(false, b.fleet.carrier.isShipAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForCruiser() {
		b = new Battlefield();		
		int x0 = b.fleet.cruiser.getBodyOfShip().get(0).getX();
		int y0 = b.fleet.cruiser.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.fleet.cruiser.getBodyOfShip().get(0).getIsAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForDestroyer() {
		b = new Battlefield();		
		int x0 = b.fleet.destroyer.getBodyOfShip().get(0).getX();
		int y0 = b.fleet.destroyer.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.fleet.destroyer.getBodyOfShip().get(0).getIsAlive() ); 
	}
	
	@Test
	public void isMethodsetSomePartOfBodyOfShipIsNotAliveWorkingForSubmarine() {
		b = new Battlefield();		
		int x0 = b.fleet.submarine.getBodyOfShip().get(0).getX();
		int y0 = b.fleet.submarine.getBodyOfShip().get(0).getY();
		b.setSomePartOfBodyOfAllShipsNotAlive( Integer.toString(x0) + Integer.toString(y0));		
		assertEquals(false, b.fleet.submarine.getBodyOfShip().get(0).getIsAlive() ); 
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
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();		
		bFromCompEyes.markAllAroundShipSquaresAsMissed("11");                
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[0][0]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[0][1]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[0][2]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[1][0]);		
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[1][2]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[2][0]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[2][1]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[2][2]);
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInLeftUpperCorner() {
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();	        
		bFromCompEyes.markAllAroundShipSquaresAsMissed("00");		
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[0][1]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[1][0]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[1][1]);		
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInRightUpperCorner() {
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();	        
		bFromCompEyes.markAllAroundShipSquaresAsMissed("90");		
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[0][8]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[1][8]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[1][9]);		
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInLeftDownCorner() {
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();		
        bFromCompEyes.markAllAroundShipSquaresAsMissed("09");		
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[8][0]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[8][1]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[9][1]);		
	}
	
	@Test
	public void markAllAroundSquaresAsMissedWorkingInRightDownCorner() {
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();	        
		bFromCompEyes.markAllAroundShipSquaresAsMissed("99");		
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[8][8]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[8][9]);
		assertEquals(MISSED, bFromCompEyes.battleWithComputerEys[9][8]);		
	}
	
	@Test
	public void isnextDamagedStrategyStepWorkingInCenter() {						
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();        
		assertEquals("10", bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to north
		bFromCompEyes.battleWithComputerEys[0][1] = MISSED;		
		assertEquals("21", bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to east
		bFromCompEyes.battleWithComputerEys[1][2] = MISSED;
		assertEquals("12", bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to south
		bFromCompEyes.battleWithComputerEys[2][1] = MISSED;
		assertEquals("01", bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(1,1)); //to west
	}
	
	@Test
	public void isStringIntWorking() {				
		dialog = new DialogWithComputer(new Battlefield()); 
		assertEquals(true, dialog.isStringInt("12"));
		assertEquals(false,dialog.isStringInt("if"));
		assertEquals(false,dialog.isStringInt("if I am a good negotiator?"));
		assertEquals(false,dialog.isStringInt(""));
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToNord() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("23");
		l.add("22");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("21",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(2, 2)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToNordWith3Parts() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("23");
		l.add("22");
		l.add("21");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("20",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(2, 1)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToNordWith3PartsWith20AsLastStep() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("22");
		l.add("21");
		l.add("20");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("23",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(2, 0)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToNordWith4Parts() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("24");
		l.add("23");
		l.add("22");
		l.add("21");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("20",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(2, 1)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToNordNearBorder() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("41");
		l.add("40");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("42",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(4, 0)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToEast() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("63");
		l.add("73");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("83",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(7, 3)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToEastNearBorder() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("89");
		l.add("99");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("79",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(9, 9)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToSouth() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("66");
		l.add("67");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("68",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(6, 7)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToSouthNearBorder() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("08");
		l.add("09");		
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("07",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(0, 9)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToWest() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("25");
		l.add("15");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("05",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(1, 5)); 
	}
	
	@Test
	public void nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirstFromCenterToWestNearBorder() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		List<String> l = new ArrayList<>();
		l.add("10");
		l.add("00");
		bFromCompEyes.compLogsForKillingShip = l;				
		assertEquals("20",bFromCompEyes.nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(0, 0)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstUp() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();		 				
		assertEquals("37",bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstRight() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		bFromCompEyes.battleWithComputerEys[7][3] = 7;				
		assertEquals("48",bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstDown() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		bFromCompEyes.battleWithComputerEys[7][3] = 7;
		bFromCompEyes.battleWithComputerEys[8][4] = 7;
		assertEquals("39",bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test
	public void nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirstLeft() {		
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		bFromCompEyes.battleWithComputerEys[7][3] = 7;
		bFromCompEyes.battleWithComputerEys[8][4] = 7;
		bFromCompEyes.battleWithComputerEys[9][3] = 7;
		assertEquals("28",bFromCompEyes.nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(3, 8)); 
	}
	
	@Test	
	public void isMethodIsAliveInBattleshipWorkingForCarrierIfAlive() {		
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,true));
		bodyOfTestShip.add(new BodyPart(0,2,true));
		bodyOfTestShip.add(new BodyPart(0,3,true));
		bodyOfTestShip.add(new BodyPart(0,4,true));		
		b.fleet.carrier.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.fleet.carrier.isShipAlive()); 
	}
	
	@Test	
	public void isMethodIsAliveInBattleshipWorkingForCarrierIfNotAlive() {		
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,false));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		bodyOfTestShip.add(new BodyPart(0,2,false));
		bodyOfTestShip.add(new BodyPart(0,3,false));
		bodyOfTestShip.add(new BodyPart(0,4,false));		
		b.fleet.carrier.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, b.fleet.carrier.isShipAlive()); 
	}
	
	@Test	
	public void isMethodIsAliveInBattleshipWorkingForCarrierIfNotAliveWhen1BodyPartIsNotAlive() {		
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		bodyOfTestShip.add(new BodyPart(0,2,false));
		bodyOfTestShip.add(new BodyPart(0,3,false));
		bodyOfTestShip.add(new BodyPart(0,4,false));		
		b.fleet.carrier.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.fleet.carrier.isShipAlive()); 
	}
	
	@Test
	public void isShipAliveWorkingWhenShipIsAlive() {
		b = new Battlefield();
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,true));
		b.fleet.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.fleet.destroyer.isShipAlive()); 
	}
	
	@Test
	public void isShipAliveWorkingWhenShipIsNotAlive() {
		b = new Battlefield();
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,false));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.fleet.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, b.fleet.destroyer.isShipAlive()); 
	}
	
	@Test
	public void isShipAliveWorkingWhenShipIsParticularlyAlive() {
		b = new Battlefield();
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.fleet.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.fleet.destroyer.isShipAlive()); 
	}
	
	@Test
	public void isShipHitWorkingWhenShipIsAlive() {
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,true));
		b.fleet.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, b.fleet.isShipHit(b.fleet.destroyer.getName()) ); 
	}
	
	@Test
	public void isShipHitWorkingWhenShipIsNotAlive() {
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,false));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.fleet.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(true, b.fleet.isShipHit(b.fleet.destroyer.getName()) ); 
	}
	
	@Test	
	public void isShipHitWorkingWhenShipIsParticularlyAlive() {
		b = new Battlefield();		
		bodyOfTestShip = new ArrayList<>();
		bodyOfTestShip.add(new BodyPart(0,0,true));
		bodyOfTestShip.add(new BodyPart(0,1,false));
		b.fleet.destroyer.setBodyOfShip(bodyOfTestShip);		
		assertEquals(false, b.fleet.isShipHit(b.fleet.destroyer.getName()) ); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4CarrierWorking() {
		b = new Battlefield();
		String step = Integer.toString(b.fleet.carrier.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.fleet.carrier.getBodyOfShip().get(0).getY());
		assertEquals("Carrier", b.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4BattleshipWorking() {
		b = new Battlefield();
		String step = Integer.toString(b.fleet.battleship.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.fleet.battleship.getBodyOfShip().get(0).getY());
		assertEquals("Battleship", b.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4CruiserWorking() {
		b = new Battlefield();
		String step = Integer.toString(b.fleet.cruiser.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.fleet.cruiser.getBodyOfShip().get(0).getY());
		assertEquals("Cruiser", b.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4SubmarineWorking() {
		b = new Battlefield();
		String step = Integer.toString(b.fleet.submarine.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.fleet.submarine.getBodyOfShip().get(0).getY());
		assertEquals("Submarine", b.getShipNameFromStep(step)); 
	}
	
	@Test	
	public void isMethodGetShipNameFromStep4DestroyerWorking() {
		b = new Battlefield();
		String step = Integer.toString(b.fleet.destroyer.getBodyOfShip().get(0).getX()) +
				      Integer.toString(b.fleet.destroyer.getBodyOfShip().get(0).getY());
		assertEquals("Destroyer", b.getShipNameFromStep(step)); 
	}
	
	@Test
	public void isMethodisFleetAliveWorking() {
		b = new Battlefield();		
		assertEquals(true,b.fleet.isFleetAlive()); 
	}
	
	@Test
	public void isMethodisFleetAliveWithNotAliveWithDeadDestroyerWorking() {
		b = new Battlefield();
		b.fleet.destroyer.getBodyOfShip().get(0).setIsAlive(false);
		b.fleet.destroyer.getBodyOfShip().get(1).setIsAlive(false);
		assertEquals(true,b.fleet.isFleetAlive()); 
	}
	
	public void isMethod_doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed_ToNorth() {
		List<String> compLogsForKillingShip = new ArrayList<>();	;
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		compLogsForKillingShip.add("24");
		compLogsForKillingShip.add("23");
		compLogsForKillingShip.add("22");
		String step = bFromCompEyes.doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed(2,1);
		assertEquals("25",step); 
	}
	
	public void isMethod_doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed_ToEast() {
		List<String> compLogsForKillingShip = new ArrayList<>();	;
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		compLogsForKillingShip.add("15");
		compLogsForKillingShip.add("25");
		compLogsForKillingShip.add("25");
		String step = bFromCompEyes.doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed(4,5);
		assertEquals("05",step); 
	}
	
	public void isMethod_doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed_ToSouth() {
		List<String> compLogsForKillingShip = new ArrayList<>();	;
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		compLogsForKillingShip.add("52");
		compLogsForKillingShip.add("53");
		compLogsForKillingShip.add("54");
		String step = bFromCompEyes.doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed(5,5);
		assertEquals("51",step); 
	}
	
	public void isMethod_doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed_ToWest() {
		List<String> compLogsForKillingShip = new ArrayList<>();	;
		BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();
		compLogsForKillingShip.add("35");
		compLogsForKillingShip.add("25");
		compLogsForKillingShip.add("15");
		String step = bFromCompEyes.doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed(0,5);
		assertEquals("45",step); 
	}
	
	//TODO test - isAnyShipOnThisPointDamaged
	//TODO test - computerStartGaming("m")	
	//TODO test - doingComputerStepWhenStatusMissed
	
	// template
	public void method4Copy() {
		int a=0;
		int b=0;
		assertEquals(a,b); 
	}

}
