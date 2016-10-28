package com.eduardo.speculate.game;

import java.util.Random;

public class SixFaceDice {

	public int rollDice() {
		int face = 0;
		
		Random randomEvent = new Random();
		
		face = randomEvent.nextInt(6) + 1;
		
		return face;
	}

}
