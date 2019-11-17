package com.epam.lab.batterfield;

import static com.epam.lab.battlefield.util.Constants.*;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattlefieldOfUserWithComputerEyes {
	
	private View view = new View();		
	int[][] battleWithComputerEys = new int[10][10];	
	private List<BodyPart> deadShipBody;
	List<String> compLogsForKillingShip;
	private boolean isShipAlreadyDamaged = false;
	String lastCompStep;
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);
	
	public void setShipAlreadyDamaged(boolean isShipAlreadyDamaged) {
		this.isShipAlreadyDamaged = isShipAlreadyDamaged;
	}	

	public void markAllAroundShipSquaresAsMissed(String lastStep) { // 0 1 2
		int x = Integer.parseInt(lastStep.substring(0, 1));         // 3 h 4
		int y = Integer.parseInt(lastStep.substring(1));            // 5 6 7

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

	public void markFieldsAroundDeadShipAsMissed(int x, int y) {
		deadShipBody = new ArrayList<>();
		deadShipBody.add(new BodyPart(x, y, false));
		createDeadShipBodyWhenUserSaidHit(x, y);
		createDeadShipBodyWhenUserSaidHit(x, y);
		for (BodyPart bp : deadShipBody) {
			markAllAroundShipSquaresAsMissed(Integer.toString(bp.getX())
					                       + Integer.toString(bp.getY()));
		}		
	}

	public void createDeadShipBodyWhenUserSaidHit(int x, int y) { // 0 1 2
		                                                          // 3 h 4 find BodyPart around h
		                                                          // 5 6 7
		for (int i = 0; i < 4; i++) {
			if ((y - 1 >= 0) && (battleWithComputerEys[y - 1][x] == DAMAGED)) {
				battleWithComputerEys[y - 1][x] = HIT;
				deadShipBody.add(new BodyPart(x, y - 1, false));
				y = y - 1;
				continue;
			}
			if ((x - 1 >= 0) && (battleWithComputerEys[y][x - 1] == DAMAGED)) {
				battleWithComputerEys[y][x - 1] = HIT;
				deadShipBody.add(new BodyPart(x - 1, y, false));
				x = x - 1;
				continue;
			}
			if ((x + 1 <= 9) && (battleWithComputerEys[y][x + 1] == DAMAGED)) {
				battleWithComputerEys[y][x + 1] = HIT;
				deadShipBody.add(new BodyPart(x + 1, y, false));
				x = x + 1;
				continue;
			}
			if ((y + 1 <= 9) && (battleWithComputerEys[y + 1][x] == DAMAGED)) {
				battleWithComputerEys[y + 1][x] = HIT;
				deadShipBody.add(new BodyPart(x, y + 1, false));
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
				System.out.println("===from nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst: something come up maybe user provided bad info somewhere then step=00");
				System.out.println("===battleWithComputerEys:");
				view.displayBattleField(battleWithComputerEys);//for testing only
				step = "00";
			}
			return step;
	}
	
	public String nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst(int x, int y) {		
		String nextStep = "something wrong";		
		int sizeOfLogs = compLogsForKillingShip.size();
		
		int previousX = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(0,1));//!=sizeOfLogs-2 from 0 its need to go
		int previousY = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(1));  // and not previousX Y but firstPointWhereDamageStart
		
			System.out.println("===from nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst: sizeOfLogs = " + sizeOfLogs);//TODO 4test
			System.out.println("===from nextStepAccordingToDamageStrategyWhenShipDamagedNotAtFirst: previousX = " +  //TODO 4test
		                    previousX + " previousY = " + previousY );
		
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
		int sizeOfLogs = compLogsForKillingShip.size();				
		int previousX = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(0,1));
		int previousY = Integer.parseInt(compLogsForKillingShip.get(sizeOfLogs-2).substring(1));
			System.out.println("===from ...ButLastStepWasMissed: sizeOfLogs = " + sizeOfLogs + 
					           " previousX = " + previousX + " previousY = " + previousY);
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

	public String doingRandomStepWithoutAnyStrategy() {
		int x = 0;
		int y = 0;		
		for (int lookingForGoodStep = 0; lookingForGoodStep <= NOT_RALLY_BIG_MAGIC_NUMBER_OF_ITERATIONS; lookingForGoodStep++) {			
			x = random.nextInt(10);
			y = random.nextInt(10);			
			if ( battleWithComputerEys[y][x] == BLANK ) {				
				System.out.println("=== Please write your answer here ===");
				return Integer.toString(x) + Integer.toString(y);
			}			
		}			
		System.out.println("===from doingRandomStepWithoutAnyStrategy: its funny, but there is no blanks, return 00");
		return "00";
	}

	public void doingComputerStepWhenStatusDamaged() {
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
	
	public void doingComputerStepWhenStatusMissed() {
		if (isShipAlreadyDamaged) {
			if (compLogsForKillingShip.size() == 1) {
					System.out.println("===from doingComputerStepWhenStatusMissed: compLogsForKillingShip.size() = " + compLogsForKillingShip.size());
				lastCompStep = nextStepAccordingToDamagedStrategyWhenShipDamagedAtFirst(
						          Integer.parseInt(compLogsForKillingShip.get(0).substring(0,1)), 
						          Integer.parseInt(compLogsForKillingShip.get(0).substring(1)));
					System.out.println("===from doingComputerStepWhenStatusMissed: lastCompStep = " + lastCompStep); 
			}			
			if (compLogsForKillingShip.size() > 1) {
				System.out.println("===from doingComputerStepWhenStatusMissed: compLogsForKillingShip.size() = " + compLogsForKillingShip.size());
				lastCompStep = doingStepWhenShipAlreadyDamagedFromTheCenterOfShipButLastStepWasMissed(
						                                Integer.parseInt(lastCompStep.substring(0,1)),
                                                        Integer.parseInt(lastCompStep.substring(1)));
					System.out.println("===from doingComputerStepWhenStatusMissed: lastCompStep = " + lastCompStep);
			}				
		} else {
			lastCompStep = doingRandomStepWithoutAnyStrategy();
			System.out.println("***doingComputerStep: random step: " + lastCompStep);
		}
	}	
	
}
