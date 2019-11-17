package com.epam.lab.batterfield;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static com.epam.lab.battlefield.util.Constants.*;
public class DialogWithComputer {	
	private Scanner scanner = new Scanner(System.in);
	private Battlefield b;	
	private List<String> userLogs = new ArrayList<>();	
	private Random random = new Random();
	private String yourAnswer;	
	private int numberOfDeadShips = 0;	
	BattlefieldOfUserWithComputerEyes bFromCompEyes = new BattlefieldOfUserWithComputerEyes();

	public DialogWithComputer(Battlefield b) {
		this.b = b;
	}

	public void start() {
		if (random.nextBoolean()) userStartGaming();
		else computerStartGaming("missed");
	}

	public void userStartGaming() {
		for (int untilUserGame = 0; untilUserGame <= NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; untilUserGame++) {			
			System.out.println("=== Please write your step here ===");
			yourAnswer = scanner.nextLine();			
			if (userLogs.contains(yourAnswer)) {
				System.out.println("=== You did this step already, try again");
				continue;
			}
			userLogs.add(yourAnswer);		
			if (!isInputForHitValid(yourAnswer)) {
				System.out.println("=== Your input is invalid, try it once more");
				continue; 
			}			
			if (b.isAnyShipOnThisPointDamaged(yourAnswer)) {
				b.setBodyPartAsDamaged(yourAnswer);
				ifShipDamagedOrHitFromUserSide();				
				continue;				
			} else 
				ifUserMissedComputerStartGaming();			
		}
	}	
	
	public void computerStartGaming(String status) {	
            doingComputerStep(status);            
			userInputWithVerification();			
			if (yourAnswer.equals("win") || yourAnswer.equals("w")) {
				ifUserAnswerIsWin();				
			}			
			if (yourAnswer.equals("hit") || yourAnswer.equals("h")) {
				ifUserAnswerIsHit();				
			}			
			if (yourAnswer.equals("damaged") || yourAnswer.equals("d")) {
				ifUserAnswerIsDamaged();				
			}			
			if (yourAnswer.equals("missed") || yourAnswer.equals("m")) {				
				ifUserAnswerIsMissed();				
			}					
	}
	
	public void ifUserAnswerIsWin(){
		System.out.println("*** Computer: I won - You lost!!! ***");				
		System.out.println("=== Bye, next time ===");
		scanner.close();
		System.exit(0);
	}
	
	public void ifUserAnswerIsHit(){		
		numberOfDeadShips++;
		if (numberOfDeadShips == SHIPS_IN_BATTLE) {
			System.out.println("***Computer win! See you later***");
			scanner.close();
			System.exit(0);
		} else 
			computerNextStepAfterHitShip();		
	}
	
	public void computerNextStepAfterHitShip(){
		bFromCompEyes.battleWithComputerEys[Integer.parseInt(bFromCompEyes.lastCompStep.substring(1))]
				                           [Integer.parseInt(bFromCompEyes.lastCompStep.substring(0, 1))] = HIT;
        bFromCompEyes.setShipAlreadyDamaged(false);
        bFromCompEyes.markFieldsAroundDeadShipAsMissed( Integer.parseInt(bFromCompEyes.lastCompStep.substring(0,1)),
                                                        Integer.parseInt(bFromCompEyes.lastCompStep.substring(1)));
        computerStartGaming("m");
	}
	
	public void ifUserAnswerIsDamaged() {					
		bFromCompEyes.battleWithComputerEys[Integer.parseInt(bFromCompEyes.lastCompStep.substring(1))]
		                                   [Integer.parseInt(bFromCompEyes.lastCompStep.substring(0,1))] = DAMAGED;        		
        computerStartGaming("d");
	}
	
	public void ifUserAnswerIsMissed(){
			System.out.println("*** from ifUserAnswerIsMissed");
		System.out.println("*** Computer: your answer = missed ***");				
		bFromCompEyes.battleWithComputerEys[Integer.parseInt(bFromCompEyes.lastCompStep.substring(1))]
				             [Integer.parseInt(bFromCompEyes.lastCompStep.substring(0, 1))] = MISSED;									
		userStartGaming();		
	}
	
	public void doingComputerStep(String status){				
		if (status == "d" || status == "damaged") {
			bFromCompEyes.doingComputerStepWhenStatusDamaged();				
		}		
		if (status == "m" || status == "missed") {
			bFromCompEyes.doingComputerStepWhenStatusMissed();			
		}
	}
		
	public void userInputWithVerification() {
		for (int verifyGrammar = 0; verifyGrammar <= NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; verifyGrammar++) {
			yourAnswer = scanner.nextLine();
			if (userInputVerification(yourAnswer))	break;
			System.out.println("*** Computer: grammatically wrong answer, try again ***");
		}
	}

	public boolean isInputForHitValid(String input) {		
		if (input == null || input.isEmpty() ||			
			input.length() != 2	|| !isStringInt(input)) {				
				return false;
		    }
		int x = Integer.parseInt(input.substring(0,1));
		int y = Integer.parseInt(input.substring(1));
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				if (x == i & y == j) return true;
			}
		}		
		return false;
	}

	public boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public boolean userInputVerification(String yourAnswer) {
		return     yourAnswer.equals("win") || yourAnswer.equals("w")
				|| yourAnswer.equals("hit") || yourAnswer.equals("h")
				|| yourAnswer.equals("damaged") || yourAnswer.equals("d")
				|| yourAnswer.equals("missed") || yourAnswer.equals("m");
	}
	
	public void ifShipDamagedOrHitFromUserSide() {		
		b.setSomePartOfBodyOfAllShipsNotAlive(yourAnswer);
		b.setUserStepInCompFieldAsDamaged(yourAnswer);			
		if (b.fleet.isShipHit(b.getShipNameFromStep(yourAnswer))) {		
			System.out.println("from ifShipDamagedOrHitFromUserSide(): +++ hit +++");			
			if (!b.fleet.isFleetAlive()) {
				System.out.println("+++ you win! +++");
				System.out.println("=== Bye, next time");
				scanner.close();
				System.exit(0);
			}			
		} else {
			System.out.println("from ifShipDamagedOrHitFromUserSide(): +++ damaged +++");
		}					
	}
	
	public void ifUserMissedComputerStartGaming() {
			System.out.println("===from ifUserMissedComputerStartGaming:");
		System.out.println("+++ Missed +++");
		b.setUserStepInCompFieldAsMissed(yourAnswer);		
		computerStartGaming("m");
	}
	
}
