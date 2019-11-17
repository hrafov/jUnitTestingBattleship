package com.epam.lab.batterfield;

import java.util.ArrayList;
import java.util.List;

public class View {

	private int[][] computerBattlefieldForTesting = new int[10][10];
	List<BodyPart> vicinity = new ArrayList<>();

	public void displayBattleFieldInConsole(Battlefield b) {

		List<BodyPart> carrierBody = b.fleet.carrier.getBodyOfShip();
		List<BodyPart> battleshipBody = b.fleet.battleship.getBodyOfShip();
		List<BodyPart> cruiserBody = b.fleet.cruiser.getBodyOfShip();
		List<BodyPart> submarineBody = b.fleet.submarine.getBodyOfShip();
		List<BodyPart> destroyerBody = b.fleet.destroyer.getBodyOfShip();

		for (int i = 0; i < carrierBody.size(); i++) {
			computerBattlefieldForTesting[carrierBody.get(i).getY()][carrierBody
					.get(i).getX()] = 1;
		}
		for (int i = 0; i < battleshipBody.size(); i++) {
			computerBattlefieldForTesting[battleshipBody.get(i).getY()][battleshipBody
					.get(i).getX()] = 2;
		}
		for (int i = 0; i < cruiserBody.size(); i++) {
			computerBattlefieldForTesting[cruiserBody.get(i).getY()][cruiserBody
					.get(i).getX()] = 3;
		}
		for (int i = 0; i < submarineBody.size(); i++) {
			computerBattlefieldForTesting[submarineBody.get(i).getY()][submarineBody
					.get(i).getX()] = 4;
		}
		for (int i = 0; i < destroyerBody.size(); i++) {
			computerBattlefieldForTesting[destroyerBody.get(i).getY()][destroyerBody
					.get(i).getX()] = 5;
		}
		displayBattleField(computerBattlefieldForTesting);
	}
	
	public void displayBattleFieldWithVicinityInConsole(     // this method only for development testing
			List<BodyPart> shipBody, List<BodyPart> ship2Body,
			List<BodyPart> ship3Body, List<BodyPart> ship4Body,
			List<BodyPart> ship5Body) {
		vicinity = createVicinityOfTheShip(shipBody);
		for (int i = 0; i < vicinity.size(); i++) {
			computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(
					i).getX()] = 8;
		}
		for (int i = 0; i < shipBody.size(); i++) {
			computerBattlefieldForTesting[shipBody.get(i).getY()][shipBody.get(
					i).getX()] = 1;
		}
		vicinity.clear();
		vicinity = createVicinityOfTheShip(ship2Body);
		for (int i = 0; i < vicinity.size(); i++) {
			computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(
					i).getX()] = 8;
		}
		for (int i = 0; i < ship2Body.size(); i++) {
			computerBattlefieldForTesting[ship2Body.get(i).getY()][ship2Body
					.get(i).getX()] = 2;
		}
		vicinity.clear();
		vicinity = createVicinityOfTheShip(ship3Body);
		for (int i = 0; i < vicinity.size(); i++) {
			computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(
					i).getX()] = 8;
		}
		for (int i = 0; i < ship3Body.size(); i++) {
			computerBattlefieldForTesting[ship3Body.get(i).getY()][ship3Body
					.get(i).getX()] = 3;
		}
		vicinity.clear();
		vicinity = createVicinityOfTheShip(ship4Body);
		for (int i = 0; i < vicinity.size(); i++) {
			computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(
					i).getX()] = 8;
		}
		for (int i = 0; i < ship4Body.size(); i++) {
			computerBattlefieldForTesting[ship4Body.get(i).getY()][ship4Body
					.get(i).getX()] = 4;
		}
		vicinity.clear();
		vicinity = createVicinityOfTheShip(ship5Body);
		for (int i = 0; i < vicinity.size(); i++) {
			computerBattlefieldForTesting[vicinity.get(i).getY()][vicinity.get(
					i).getX()] = 8;
		}
		for (int i = 0; i < ship5Body.size(); i++) {
			computerBattlefieldForTesting[ship5Body.get(i).getY()][ship5Body
					.get(i).getX()] = 5;
		}
		displayBattleField(computerBattlefieldForTesting);
	}

	public List<BodyPart> createVicinityOfTheShip(List<BodyPart> shipBody) {
		int x,y;		
		for (int i = 0; i < shipBody.size(); i++) {
			x = shipBody.get(i).getX();
			y = shipBody.get(i).getY();
			if (y > 0) vicinity.add(new BodyPart(x, y - 1, true));
			if (y < 9) vicinity.add(new BodyPart(x, y + 1, true));
			if (x < 9) vicinity.add(new BodyPart(x + 1, y, true));
			if (x > 0) vicinity.add(new BodyPart(x - 1, y, true));
			if (x < 9 && y > 0)	vicinity.add(new BodyPart(x + 1, y - 1, true));
			if (x < 9 && y < 9)	vicinity.add(new BodyPart(x + 1, y + 1, true));
			if (x > 0 && y < 9)	vicinity.add(new BodyPart(x - 1, y + 1, true));
			if (x > 0 && y > 0)	vicinity.add(new BodyPart(x - 1, y - 1, true));
		}
		return vicinity;
	}

	public void displayBattleField(int[][] battlefield) {
		System.out.println("   0   1   2   3   4   5   6   7   8   9\n");
		for (int i = 0; i <= 9; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j <= 9; j++) {
				System.out.print(battlefield[i][j] + "   ");
			}
			System.out.println("\n");
		}
	}
}
