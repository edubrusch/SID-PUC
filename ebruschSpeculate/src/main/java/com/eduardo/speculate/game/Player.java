package com.eduardo.speculate.game;

import com.eduardo.speculate.server.ServerProperties;


public class Player {

	private final int playerID;
	private int ballsCount = ServerProperties.DEFAULT_PLAYER_BALL_COUNT.getInt();
	private int remainingDiceRollsInTheMatch = 0;



	public Player(int ID) {
		this.playerID = ID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void decreaseBallcount() {
		ballsCount ++;

	}

	public void increaseBallcount() {
		ballsCount--;
	}

	public int getballCount() {
		return ballsCount;
	}

	public int getRemainingRolls() {
		return remainingDiceRollsInTheMatch;
	}

	// update this value constantly so that it can be seen by the other player. It should make part in th gamestate.
	public void updateRemainingRolls(int amount) {
		this.remainingDiceRollsInTheMatch  = amount;
	}

}
