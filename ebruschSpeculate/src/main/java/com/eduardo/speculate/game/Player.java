package com.eduardo.speculate.game;

import com.eduardo.speculate.server.ServerProperties;


public class Player {

	private final int playerID;
	private int ballsCount = ServerProperties.DEFAULT_PLAYER_BALL_COUNT.getInt();



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

}
