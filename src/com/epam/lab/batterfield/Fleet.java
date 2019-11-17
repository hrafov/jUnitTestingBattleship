package com.epam.lab.batterfield;

import java.util.ArrayList;
import java.util.List;

public class Fleet {	
	private List<Battleship> fleet = new ArrayList<>();
	Battleship carrier = new Battleship("Carrier", 5);
	Battleship battleship = new Battleship("Battleship", 4);
	Battleship cruiser = new Battleship("Cruiser", 3);
	Battleship submarine = new Battleship("Submarine", 3);
	Battleship destroyer = new Battleship("Destroyer", 2);
	
	public Fleet() {
		this.fleet.add(battleship);
		this.fleet.add(carrier);
		this.fleet.add(cruiser);
		this.fleet.add(destroyer);
		this.fleet.add(submarine);
	}
	public List<Battleship> getFleet() {
		return fleet;
	}	
	public boolean isFleetAlive() {
		if (battleship.isShipAlive() || carrier.isShipAlive()
				|| cruiser.isShipAlive() || destroyer.isShipAlive()
				|| submarine.isShipAlive()) return true;
		else return false;
	}	
	public boolean isShipHit(String shipName) {		
		for ( Battleship bs: fleet) {
			if ((bs.getName() == shipName) && bs.isShipAlive())
				return false;		
		}
		return true;
	}	
}
