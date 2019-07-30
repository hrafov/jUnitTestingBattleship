package com.epam.lab.batterfield;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BattleshipTests {
   	
	private int[][] he = new int[10][10];
	Battlefield b = new Battlefield();	
		
	@Test
	public void battleshipInitializingAtStart() {
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// System.out.print( he[i][j] + "   ");
				assertEquals(he[i][j], 0);
			}			
		}
	}

	@Test
	public void sumOfAllShipElements() {
		b.initializeComputerBattlefield();
		int sum = 0;
		sum += b.carrier.getBodyOfShip().size();
		sum += b.battleship.getBodyOfShip().size();
		sum += b.cruiser.getBodyOfShip().size();
		sum += b.submarine.getBodyOfShip().size();
		sum += b.destroyer.getBodyOfShip().size();
		assertEquals(sum, 17);
	}
	
	@Test
	public void isRandomChoiceWhoFirst() {
		int isR = b.randomChoiceWhoFirst();		
		assertTrue( isR == 0 || isR == 1 ); 
	}	
	
	@Test
	public void isMethodIsAliveInBattleshipWorking() {		
		Battleship Carrier = new Battleship("Carrier",5);
		Battleship Battleship = new Battleship("Battleship",4);
		Battleship Cruiser = new Battleship("Cruiser",3);
		Battleship Submarine = new Battleship("Submarine",3);
		Battleship Destroyer = new Battleship("Destroyer",2);		
		assertEquals(Carrier.isShipAlive(),true);		
		assertEquals(Battleship.isShipAlive(),true); 		
		assertEquals(Cruiser.isShipAlive(),true);		
		assertEquals(Submarine.isShipAlive(),true);		
		assertEquals(Destroyer.isShipAlive(),true); 
	}
	
	@Test
	public void translationFromIntToString() {		
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
	public void isUserInputForHittingGrammaticallyValid() {
		//user hit first
		DialogWithComputer dialog = new DialogWithComputer(true, b);
		String input;		
		boolean isValid = true;		
		for( int x = 0; x < 10; x++ ){
			for( int y = 0; y < 10; y++ ){
				input = Integer.toString(x) + Integer.toString(y);
				//System.out.println(" === testing test --- x + y = " + input);
				if ( ! dialog.isInputForHittingValid(input) ) {
					isValid = false;
					break;
				}
			}
		}		
		assertTrue(isValid);		
		assertTrue( !dialog.isInputForHittingValid(null));
		assertTrue( !dialog.isInputForHittingValid(""));
		assertTrue( !dialog.isInputForHittingValid("Too long string"));		
	}
		
	//test ships are not diagonal(
	
	//test ships not touching each other(	
	
	@Test
	public void method4Copy() {
		int a=0;
		int b=0;
		assertEquals(a,b); 
	}

}
