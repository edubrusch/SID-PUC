package com.eduardo.speculate.game;

import com.eduardo.speculate.server.ServerProperties;


public class Player {

	private final int playerID;
	@SuppressWarnings("unused")
	private int ballsCount = ServerProperties.DEFAULT_PLAYER_BALL_COUNT.getInt();
	@SuppressWarnings("unused")
	private int currentMatchId = 0;

	public Player(int ID) {
		this.playerID = ID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void giveNewMatch(int matchNumber) {
		this.currentMatchId = matchNumber;
	}

	public void reduceBallcount() {
		ballsCount ++;

	}

	public void increaseBallcount() {
		ballsCount--;
	}

}
