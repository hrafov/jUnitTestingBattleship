package com.epam.lab.batterfield;

// https://en.wikipedia.org/wiki/Battleship_(game)
// https://upload.wikimedia.org/wikipedia/commons/e/e4/Battleships_Paper_Game.svg

public class StartGame {	
	public static void main(String[] args) {
		Battlefield b = new Battlefield();
		View view = new View();		
		view.displayBattleFieldInConsole(b); // for testing		
		DialogWithComputer dialog = new DialogWithComputer(b);
		dialog.start();
	}

}
