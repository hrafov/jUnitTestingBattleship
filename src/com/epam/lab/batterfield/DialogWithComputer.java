package com.epam.lab.batterfield;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DialogWithComputer {

	private boolean youFirstStep;
	private Scanner scanner = new Scanner(System.in);
	private BattleField b;
	private List<String> userLogs = new ArrayList<>();

	public DialogWithComputer(boolean youFirstStep, BattleField b) {
		this.youFirstStep = youFirstStep;
		this.b = b;
	}

	public void start() {
		if (youFirstStep) {			
			
			for (int a = 0; a <= 10000; a++) {
				System.out.println("=== Please write your step here ===");
				String yourStep = scanner.nextLine();
				if(userLogs.contains(yourStep)) {
					System.out.println("=== You already did this step, please try again ===");
					continue;
				}
				userLogs.add(yourStep);
				System.out.println("=== Your step: " + yourStep);
								
				// validation of user input
				if (!isInputForHittingValid(yourStep))
					continue;

				if (isDamaged(yourStep)) {
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
						// TODO mark battlefield like missed
						b.setYourStepToBattleAsMissed(yourStep);
						b.displayBatterFieldForUserInFightingProcessInConsole();
						computerStartGaming();
					}
				}

			}
		} else {
			System.out.println("=== Computer writing first step here ===");
			System.out
					.println("=== Sorry, this option is in development now and not ready yet, continue trying ===");
		}

		scanner.close();
		System.out.println("=== Bye, next time ===");
	}

	public boolean isDamaged(String yourStep) {
		
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
		System.out.println("+++ from isShipHit: shipName" + shipName);
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
				System.out.println(" ++++ from getShipNameFromStep: this is - "
						+ b.battleship.getName());
				return b.battleship.getName();
			}
		}

		for (BodyPart bp : b.carrier.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
				System.out.println(" ++++ from getShipNameFromStep: this is - "
						+ b.carrier.getName());
				return b.carrier.getName();
			}
		}

		for (BodyPart bp : b.cruiser.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
				System.out.println(" ++++ from getShipNameFromStep: this is - "
						+ b.cruiser.getName());
				return b.cruiser.getName();
			}
		}

		for (BodyPart bp : b.destroyer.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
				System.out.println(" ++++ from getShipNameFromStep: this is - "
						+ b.destroyer.getName());
				return b.destroyer.getName();
			}
		}

		for (BodyPart bp : b.submarine.getBodyOfShip()) {
			if ((bp.getX() == x) && (bp.getY() == y)) {
				System.out.println(" ++++ from getShipNameFromStep: this is - "
						+ b.submarine.getName());
				return b.submarine.getName();
			}
		}

		System.out.println(" ++++ from getShipNameFromStep: this is - " + name);
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
		//TODO
		System.out.println("=== Computer start gaming - not realized yet( ===");
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
