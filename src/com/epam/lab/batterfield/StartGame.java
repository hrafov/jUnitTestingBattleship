package com.epam.lab.batterfield;

public class StartGame {
	// https://en.wikipedia.org/wiki/Battleship_(game)
	// https://upload.wikimedia.org/wikipedia/commons/e/e4/Battleships_Paper_Game.svg
	public static void main(String[] args) {
				
		//int[][] he = new int[10][10]; //TODO it is not need to?
		BattleField b = new BattleField();
		DialogWithComputer dialog;
		boolean youFirstStep;		
		
		b.initialize();
					
		//b.displayBatterFieldForUserInFightingProcessInConsole(); //TODO 4testing purpose

		// who start the game?
		if( b.randomChoiceWhoFirst() == 0 ) {
			// user start the game with your first step			
			youFirstStep = true;			
		} else {
			// computer start the game with his/her first step			
			youFirstStep = false;					
		}		
		
		//display for testing
		            b.displayBattleFieldInConsole(b.carrier.getBodyOfShip(),
		            		                      b.battleship.getBodyOfShip(),
		            		                      b.cruiser.getBodyOfShip(),
		            		                      b.submarine.getBodyOfShip(),
		            		                      b.destroyer.getBodyOfShip());
		System.out.println();
//        b.displayBatterFieldWithVicinityInConsole(b.carrier.getBodyOfShip(),
//        		                                  b.battleship.getBodyOfShip(),
//        		                                  b.cruiser.getBodyOfShip(),
//        		                                  b.submarine.getBodyOfShip(),
//        		                                  b.destroyer.getBodyOfShip());
        
        dialog = new DialogWithComputer(youFirstStep, b);
        dialog.start();
        
	}	

}
