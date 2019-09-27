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
	private List<String> compLogsForKillingShip;
	private int[][] battleWithComputerEys = new int[10][10];
	private Random random = new Random();
	private String yourAnswer;	
	private int numberOfDeadShips = 0;
	private List<BodyPart> deadShipBody;
	private boolean isShipAlreadyDamaged = false;	
	private String lastCompStep;

	public DialogWithComputer(Battlefield b) {
		this.b = b;
	}

	public void start() {
		if (random.nextBoolean())
			userStartGaming();
		else
			computerStartGaming("missed");
	}

	public void userStartGaming() {
		for (int untilUserGame = 0; untilUserGame <= 1000; untilUserGame++) {			
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
			if (isShipDamaged(yourAnswer)) {					
				ifShipDamagedOrHitFromUserSide();				
				continue;				
			} else 
				ifUserMissedComputerStartGaming();			
		}
	}	
	
	public boolean isShipDamaged(String yourStep) {	
		//TODO 2 different aims: is and set - split it 
		int x = Integer.parseInt(yourStep.substring(0,1));
		int y = Integer.parseInt(yourStep.substring(1));		
		for ( Battleship bs: b.getFleet()) {
			for (BodyPart bp : bs.getBodyOfShip()) {
				if ((bp.getX() == x) && (bp.getY() == y) && bp.getIsAlive()) {
					bp.setIsAlive(false);
					return true;
				}
			}		
		}
		return false;
	}
	
	public boolean isShipHit(String shipName) {		
		for ( Battleship bs: b.getFleet()) {
			if ((bs.getName() == shipName) && bs.isShipAlive())
				return false;		
		}
		return true;
	}

	public String getShipNameFromStep(String yourStep) {
		int x = Integer.parseInt(yourStep.substring(0, 1));
		int y = Integer.parseInt(yourStep.substring(1));		
		for ( Battleship bs: b.getFleet()) {
			for (BodyPart bp : bs.getBodyOfShip()) {
				if ((bp.getX() == x) && (bp.getY() == y)) {
					return bs.getName();
				}
			}		
		}
		return "Unknown name of Ship";
	}

	public boolean isFleetAlive() {
		if (b.battleship.isShipAlive() || b.carrier.isShipAlive()
				|| b.cruiser.isShipAlive() || b.destroyer.isShipAlive()
				|| b.submarine.isShipAlive())
			return true;
		else
			return false;
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
		}		
		battleWithComputerEys[Integer.parseInt(lastCompStep.substring(1))]
				             [Integer.parseInt(lastCompStep.substring(0, 1))] = HIT;
		isShipAlreadyDamaged = false;
		markFieldsAroundDeadShipAsMissed( Integer.parseInt(lastCompStep.substring(0,1)),
	                                      Integer.parseInt(lastCompStep.substring(1)));
		computerStartGaming("m");
	}
	
	public void ifUserAnswerIsDamaged() {					
		battleWithComputerEys[Integer.parseInt(lastCompStep.substring(1))]
		                     [Integer.parseInt(lastCompStep.substring(0,1))] = DAMAGED;					
        //System.out.println("===from ifUserAnswerIsDamaged line#171: lastCompStep = " + lastCompStep);		
        computerStartGaming("d");
	}
	
	public void ifUserAnswerIsMissed(){
		System.out.println("*** Computer: your answer = missed ***");				
		battleWithComputerEys[Integer.parseInt(lastCompStep.substring(1))]
				             [Integer.parseInt(lastCompStep.substring(0, 1))] = MISSED;									
		userStartGaming();		
	}
	
	public void doingComputerStep(String status){
		//TODO in separate methods
		if (status == "d" || status == "damaged") {
			if(isShipAlreadyDamaged){				
				System.out.println("*** Last step where damage was: " + lastCompStep);
				compLogsForKillingShip.add(lastCompStep);
				lastCompStep = nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(Integer.parseInt(lastCompStep.substring(0,1)),
	                                                                                      Integer.parseInt(lastCompStep.substring(1)));			
				System.out.println("*** Computer: step to kill the ship: " + lastCompStep);	
			} else { // first blood				
				compLogsForKillingShip = new ArrayList<>();			
				isShipAlreadyDamaged = true;
				compLogsForKillingShip.add(lastCompStep);
				lastCompStep = nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(Integer.parseInt(lastCompStep.substring(0,1)),
	                                                                                    Integer.parseInt(lastCompStep.substring(1)));			
				System.out.println("*** Computer: step to kill the ship when damaged at first(second step to): " 
				      + lastCompStep);
			}			
		}		
		if (status == "m" || status == "missed") {			
			if (isShipAlreadyDamaged) {
				if (compLogsForKillingShip.size() == 1){					
					lastCompStep = nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(
							          Integer.parseInt(compLogsForKillingShip.get(0).substring(0,1)), 
							          Integer.parseInt(compLogsForKillingShip.get(0).substring(1)) );
					//System.out.println("***doingComputerStep:isShipAlreadyDamaged&&compLogsForKillingShip.size()=1: " +
					//		                                                                                 lastCompStep);
				}			
				if (compLogsForKillingShip.size() > 1){
					lastCompStep = doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed(Integer.parseInt(lastCompStep.substring(0,1)),
	                                                                                  Integer.parseInt(lastCompStep.substring(1)));
					//System.out.println("***doingComputerStep: step from the center of damaged ship: " + lastCompStep);
				}				
			} else {
				lastCompStep = doingRandomStepWithoutAnyStrategy();
				System.out.println("***doingComputerStep: random step: " + lastCompStep);
			}
		}
	}
	
	public void userInputWithVerification() {
		for (int verifyGrammar = 0; verifyGrammar <= 100; verifyGrammar++) {
			yourAnswer = scanner.nextLine();
			if (userInputVerification(yourAnswer))	break;
			System.out.println("*** Computer: grammatically wrong answer, try again ***");
		}
	}

	public boolean isInputForHitValid(String input) {		
		if (input == null ||
			input.isEmpty() ||			
			input.length() != 2	||
			!isStringInt(input)) {				
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

	public void markAllAroundSquaresAsMissed(String lastStep) {
		int x = Integer.parseInt(lastStep.substring(0,1));
		int y = Integer.parseInt(lastStep.substring(1));
		// 0 1 2
		// 3 h 4
		// 5 6 7
		if ((x - 1 >= 0) && (y - 1 >= 0)
				&& (battleWithComputerEys[y - 1][x - 1] == BLANK)) {
			battleWithComputerEys[y - 1][x - 1] = MISSED;			
		}
		if ((y - 1 >= 0) && (battleWithComputerEys[y - 1][x] == BLANK)) {
			battleWithComputerEys[y - 1][x] = MISSED;			
		}
		if ((x + 1 <= 9) && (y - 1 >= 0)
				&& (battleWithComputerEys[y - 1][x + 1] == BLANK)) {
			battleWithComputerEys[y - 1][x + 1] = MISSED;			
		}
		if ((x - 1 >= 0) && (battleWithComputerEys[y][x - 1] == BLANK)) {
			battleWithComputerEys[y][x - 1] = MISSED;			
		}
		if ((x + 1 <= 9) && (battleWithComputerEys[y][x + 1] == BLANK)) {
			battleWithComputerEys[y][x + 1] = MISSED;			
		}
		if ((x - 1 >= 0) && (y + 1 <= 9)
				&& (battleWithComputerEys[y + 1][x - 1] == BLANK)) {
			battleWithComputerEys[y + 1][x - 1] = MISSED;			
		}
		if ((y + 1 <= 9) && (battleWithComputerEys[y + 1][x] == BLANK)) {
			battleWithComputerEys[y + 1][x] = MISSED;			
		}
		if ((x + 1 <= 9) && (y + 1 <= 9)
				&& (battleWithComputerEys[y + 1][x + 1] == BLANK)) {
			battleWithComputerEys[y + 1][x + 1] = MISSED;			
		}
	}

	public int[][] getbattleWithComputerEys() {
		return this.battleWithComputerEys;
	}

	public void markFieldsAroundDeadShipAsMissed(int x, int y) {
		deadShipBody = new ArrayList<>();		
		deadShipBody.add(new BodyPart(x, y, false));		
		createDeadShipBodyWhenUserSaidHit(x, y);		
		createDeadShipBodyWhenUserSaidHit(x, y);		
		for (BodyPart bp : deadShipBody) {
			markAllAroundSquaresAsMissed(Integer.toString(bp.getX())
					+ Integer.toString(bp.getY()));
		}		
		computerStartGaming("missed");
	}

	public void createDeadShipBodyWhenUserSaidHit(int x, int y) {
		// 0 1 2
		// 3 h 4 find BodyPart around h
		// 5 6 7
		for (int i = 0; i < 4; i++) {
			if ((y - 1 >= 0) && (battleWithComputerEys[y - 1][x] == DAMAGED)) {
				battleWithComputerEys[y - 1][x] = HIT;
				deadShipBody.add(new BodyPart(x, y - 1, false));
				System.out.println("=deadBodyPart-1: "
						+ battleWithComputerEys[y - 1][x]);
				y = y - 1;
				continue;
			}
			if ((x - 1 >= 0) && (battleWithComputerEys[y][x - 1] == DAMAGED)) {
				battleWithComputerEys[y][x - 1] = HIT;
				deadShipBody.add(new BodyPart(x - 1, y, false));
				System.out.println("=deadBodyPart-3: "
						+ battleWithComputerEys[y][x - 1]);
				x = x - 1;
				continue;
			}
			if ((x + 1 <= 9) && (battleWithComputerEys[y][x + 1] == DAMAGED)) {
				battleWithComputerEys[y][x + 1] = HIT;
				deadShipBody.add(new BodyPart(x + 1, y, false));
				System.out.println("=deadBodyPart-4: "
						+ battleWithComputerEys[y][x + 1]);
				x = x + 1;
				continue;
			}
			if ((y + 1 <= 9) && (battleWithComputerEys[y + 1][x] == DAMAGED)) {
				battleWithComputerEys[y + 1][x] = HIT;
				deadShipBody.add(new BodyPart(x, y + 1, false));
				System.out.println("=deadBodyPart-6: "
						+ battleWithComputerEys[y + 1][x]);
				y = y + 1;
				continue;
			}
		}
	}

	public String nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(int x, int y) {
	// We will try to kill this ship
	// in 4 direction clockwise and if there are free point - hit	
		String step;
		if ((y - 1 >= 0) && (battleWithComputerEys[y - 1][x] == BLANK ))//to north
			step = Integer.toString(x) + Integer.toString(y - 1);
		else if ((x + 1 <= 9) && (battleWithComputerEys[y][x + 1] == BLANK))//to east
			step = Integer.toString(x + 1) + Integer.toString(y);
		else if ((y + 1 <= 9) && (battleWithComputerEys[y + 1][x] == BLANK))//to south
			step = Integer.toString(x) + Integer.toString(y + 1);
		else if ((x - 1 >= 0) && (battleWithComputerEys[y][x - 1] == BLANK))//to west
			step = Integer.toString(x - 1) + Integer.toString(y);
		else {
			System.out.println("===== from nextDamagedStrategyStep: something come up");
			step = "=== something come up, maybe user provided bad info somewhere";
		}
		return step;
	}
	
	public String nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(int x, int y) {		
		String nextStep = "somethingWrong";		
		//System.out.println("===from nextStep...NotInFirst: x = " + x + " y = " + y);		
		int sizeOfLogs = compLogsForKillingShip.size();
		//System.out.println("===from nextStep...NotInFirst: sizeOfLogs = " + sizeOfLogs);
		int previousX = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(0,1));
		int previousY = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(1));
		//System.out.println("===from nextStep...NotInFirst: previousX = " + previousX + " previousY = " + previousY);
		
		if(y < previousY){ //north
			if( y == 0 ){
				return  Integer.toString(x) + Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(1)) + 1);
			}
			if((y > 0) && (battleWithComputerEys[y-1][x] == BLANK)) nextStep = Integer.toString(x) + Integer.toString(y-1); 
			else nextStep = Integer.toString(x) + Integer.toString(y+2); //to south
		}		
		if(x > previousX){ //east
			if( x == 9 ){
				return Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(0,1)) - 1) + Integer.toString(y);
			}
			if((x < 9) && (battleWithComputerEys[y][x+1] == BLANK)) nextStep = Integer.toString(x+1) + Integer.toString(y); 
			else nextStep = Integer.toString(x-2) + Integer.toString(y); //to west
		}		
		if(y > previousY){ //south
			if( y == 9 ){
				return Integer.toString(x) + Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(1)) - 1);
			}
			if((y < 9) && (battleWithComputerEys[y+1][x] == BLANK)) nextStep = Integer.toString(x) + Integer.toString(y+1); 
			else nextStep = Integer.toString(x) + Integer.toString(y-2); //to north
		}		
		if(x < previousX){ //west
			if( x == 0 ){
				return Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(0,1)) + 1) + Integer.toString(y);
			}
			if((x > 0) && (battleWithComputerEys[y][x-1] == BLANK)) nextStep = Integer.toString(x-1) + Integer.toString(y); 
			else nextStep = Integer.toString(x+2) + Integer.toString(y); //to east
		}				
		return nextStep;
	}
	
	public String doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed(int x, int y){
		String nextStep = "somethingWrong";		
		//System.out.println("===from doingStepWhenShipAlreadyDamagedFromTheCenterOfShip: x = " + x + " y = " + y);	
		int sizeOfLogs = compLogsForKillingShip.size();
		//System.out.println("===from doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed: sizeOfLogs = " + sizeOfLogs);		
		int previousX = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(0,1));
		int previousY = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(1));
		//System.out.println("===from doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed: previousX = " + previousX + " previousY = " + previousY);		
		if(y < previousY){ //from the center of ship to south
			nextStep = Integer.toString(x) + Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(1)) + 1);			
		}		
		if(x > previousX){ // ...to west
			nextStep = Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(0,1)) - 1) + Integer.toString(y);
		}		
		if(y > previousY){ // ...to north
			nextStep = Integer.toString(x) + Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(1)) - 1);
		}		
		if(x < previousX){ // ...to east
			nextStep = Integer.toString(Integer.parseInt(compLogsForKillingShip.get(0).substring(0,1)) + 1) + Integer.toString(y);
		}		
		return nextStep;
	}
	
	public void setCompLogsForKillingShip(List<String> l) {
		this.compLogsForKillingShip = l;
	}

	public boolean userInputVerification(String yourAnswer) {
		return yourAnswer.equals("win") || yourAnswer.equals("w")
				|| yourAnswer.equals("hit") || yourAnswer.equals("h")
				|| yourAnswer.equals("damaged") || yourAnswer.equals("d")
				|| yourAnswer.equals("missed") || yourAnswer.equals("m");
	}

	public String doingRandomStepWithoutAnyStrategy() {
		int x = 0;
		int y = 0;		
		for (int lookingForGoodStep = 0; lookingForGoodStep <= 10000; lookingForGoodStep++) {			
			x = random.nextInt(10);
			y = random.nextInt(10);			
			if ( battleWithComputerEys[y][x] == BLANK ) {				
				//System.out.println("***from doingRandomStepWithoutAnyStrategy(): Computer step: " + x + y);
				System.out.println("=== Please write your answer here ===");
				break;
			}			
		}			
		return Integer.toString(x) + Integer.toString(y);
	}
	
	public void ifShipDamagedOrHitFromUserSide() {		
		b.setSomePartOfBodyOfAllShipsNotAlive(yourAnswer);
		b.setUserStepInCompFieldAsDamaged(yourAnswer);			
		if (isShipHit(getShipNameFromStep(yourAnswer))) {		
			System.out.println("from ifShipDamagedOrHitFromUserSide(): +++ hit +++");			
			if (!isFleetAlive()) {
				System.out.println("+++ you win! +++");
				System.out.println("=== Bye, next time");
				scanner.close();
				System.exit(0);
			}			
		} else {
			System.out.println("from ifShipDamagedOrHitFromUserSide(): +++ damaged +++");
		}
		//b.displayBattleField(battleWithComputerEys);//for testing only			
	}
	
	public void ifUserMissedComputerStartGaming() {
		System.out.println("+++ Missed +++");
		b.setUserStepInCompFieldAsMissed(yourAnswer);					
		b.displayBattleField(battleWithComputerEys);//for test only
		computerStartGaming("m");
	}
	
}