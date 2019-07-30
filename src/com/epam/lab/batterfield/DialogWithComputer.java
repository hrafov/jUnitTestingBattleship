package com.epam.lab.batterfield;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DialogWithComputer {

	private boolean youFirstStep;
	private Scanner scanner = new Scanner(System.in);
	private Battlefield b;
	private List<String> userLogs = new ArrayList<>();
	private int[][] battleWithComputerEys = new int[10][10];
	private Random rand = new Random();
	String yourStep;

	public DialogWithComputer(boolean youFirstStep, Battlefield b) {
		this.youFirstStep = youFirstStep;
		this.b = b;
	}

	public void start() {
		if (youFirstStep) {			
			
			for (int iii = 0; iii <= 10000; iii++) {
				System.out.println("=== Please write your step here ===");
				yourStep = scanner.nextLine();
				if(userLogs.contains(yourStep)) {
					System.out.println("=== You already did this step, please try again ===");
					continue;
				}
				userLogs.add(yourStep);
				System.out.println("=== Your step: " + yourStep);
								
				// validation of user input
				if (!isInputForHittingValid(yourStep))
					continue;

				if (isShipDamaged(yourStep)) {
					System.out.println("+++ from start method: damaged or hit or you win +++");					
					b.setSomePartOfBodyOfShipIsNotAlive(yourStep);
					b.setYourStepToBattleAsDamaged(yourStep); // with 8 integer
					
					if (isShipHit(getShipNameFromStep(yourStep))) {
						System.out.println("+++ from start method: hit or you win +++");
						if (!isFleetAlive()) {
							System.out.println("+++ from start method: you win +++");
							break; 
						}
					}
					b.displayBatterFieldForUserInFightingProcessInConsole();
					continue;
				} else {// TODO it is not need isMissed method at all
					if (isMissed(yourStep)) {
						System.out.println("+++ Missed +++");
						// mark battlefield point as missed
						b.setYourStepToBattleAsMissed(yourStep);
						b.displayBatterFieldForUserInFightingProcessInConsole();
						computerStartGaming();
					}
				}

			}
		// computer is doing its first step	
		} else {
			System.out.println("=== Computer writing first step here ===");
			System.out
					.println("=== Sorry, this option is in development now and not ready yet, continue trying ===");
		}

		scanner.close();
		System.out.println("=== Bye, next time ===");
	}

	public boolean isShipDamaged(String yourStep) {
		
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));
		
		// check through all fleet
		for (BodyPart bp : b.battleship.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {
				bp.setIsAlive(false);
				return true;
			}
		}

		for (BodyPart bp : b.carrier.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {
				bp.setIsAlive(false);
				return true;
			}
		}

		for (BodyPart bp : b.cruiser.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {
				bp.setIsAlive(false);
				return true;
			}
		}

		for (BodyPart bp : b.destroyer.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {
				bp.setIsAlive(false);
				return true;
			}
		}

		for (BodyPart bp : b.submarine.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {
				bp.setIsAlive(false);
				return true;
			}
		}

		return false;
	}

	public boolean isShipHit(String shipName) {
		// through all fleet
//		System.out.println("+++ from isShipHit: shipName" + shipName);
		if ((b.battleship.getName() == shipName) && !b.battleship.isShipAlive())
			return true;
		else if ((b.carrier.getName() == shipName) && !b.carrier.isShipAlive())
			return true;
		else if ((b.cruiser.getName() == shipName) && !b.cruiser.isShipAlive())
			return true;
		else if ((b.destroyer.getName() == shipName) && !b.destroyer.isShipAlive())
			return true;
		else if ((b.submarine.getName() == shipName) && !b.submarine.isShipAlive())
			return true;
		return false;
	}

	public String getShipNameFromStep(String yourStep) {
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));
		String name = "Unknown)";
		// check through all fleet
		for (BodyPart bp : b.battleship.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
//				System.out.println(" ++++ from getShipNameFromStep: this is - "
//						+ b.battleship.getName());
				return b.battleship.getName();
			}
		}

		for (BodyPart bp : b.carrier.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
//				System.out.println(" ++++ from getShipNameFromStep: this is - "
//						+ b.carrier.getName());
				return b.carrier.getName();
			}
		}

		for (BodyPart bp : b.cruiser.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
//				System.out.println(" ++++ from getShipNameFromStep: this is - "
//						+ b.cruiser.getName());
				return b.cruiser.getName();
			}
		}

		for (BodyPart bp : b.destroyer.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
//				System.out.println(" ++++ from getShipNameFromStep: this is - "
//						+ b.destroyer.getName());
				return b.destroyer.getName();
			}
		}

		for (BodyPart bp : b.submarine.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
//				System.out.println(" ++++ from getShipNameFromStep: this is - "
//						+ b.submarine.getName());
				return b.submarine.getName();
			}
		}

//		System.out.println(" ++++ from getShipNameFromStep: this is - " + name);
		return name;
	}

	public boolean isFleetAlive() {
		if (b.battleship.isShipAlive() || b.carrier.isShipAlive()
				|| b.cruiser.isShipAlive() || b.destroyer.isShipAlive()
				|| b.submarine.isShipAlive())
			return true;
		else
			return false;
	}

	public boolean isMissed(String yourStep) {		
		//TODO may be it is not needed at all
		return true;
	}

	public void computerStartGaming() {		
	
		int x = 0;
		int y = 0;
		
		for (int iii = 0; iii <= 10000; iii++) {// while computer did damage or hit 
			
			for (int ii = 0; ii <= 10000; ii++){
				x = rand.nextInt(10);
				y = rand.nextInt(10);
				if( battleWithComputerEys[y][x] == 0 ) { 
					System.out.println("*** Computer step: " + x + y);	//request
					System.out.println("=== Please write your step here ===");
					break;
				}			
			}
			
			yourStep = scanner.nextLine(); // response 
			
			if( yourStep.equals("win") || yourStep.equals("w") ){
				System.out.println("*** Computer: win!!! ***");
				//TODO? stop application
				break;
			}
			else if( yourStep.equals("hit") || yourStep.equals("h") ){
				System.out.println("*** Computer: your answer = hit ***");				
				battleWithComputerEys[y][x] = 1;
				//TODO
				//TODO look for some strategy for game optimizing
				//TODO
			}
			else if( yourStep.equals("damaged") || yourStep.equals("d") ){
				System.out.println("*** Computer: your answer = damaged ***");				
				battleWithComputerEys[y][x] = 1;
				//TODO
				//TODO look for some strategy for game optimizing
				//TODO
			}
			else if( yourStep.equals("missed") || yourStep.equals("m") ){
				System.out.println("*** Computer: your answer = missed ***");				
				battleWithComputerEys[y][x] = 7;
				break;
			}
			else{
				System.out.println("*** Computer: something come up) - some problem with my logic or our write wrong answer ***");				
			}
			
		}
		
	}

	public boolean isInputForHittingValid(String input) {
		
		if (input == null || input.isEmpty() || input.length() != 2) {
			System.out.println(" === Sorry sir, your input is invalid, try it once more ===");
			return false;
		}
		
		int x = Integer.parseInt(input.substring(0, 1));
		int y = Integer.parseInt(input.substring(1));
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (x == i & y == j)
					return true;
			}
		}
		System.out.println(" === Sorry sir, your input is invalid, try it once more ===");
		return false;
	}

}
