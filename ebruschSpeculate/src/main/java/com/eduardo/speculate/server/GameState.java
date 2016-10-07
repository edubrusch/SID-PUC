package com.eduardo.speculate.server;

import java.io.Serializable;

import com.eduardo.speculate.game.GameBoard;

/**
 * More like an Data Transfer Object. It's purpose is to refresh
 * the clients' view, since they are going to ask the game state anyway.
 * @author ebrusch
 *
 */

public class GameState implements Serializable{

	private static final long serialVersionUID = 2016100613421L;

	private GameBoard currentBoard;
	private boolean myTime;
	private int adversaryRemainingDiceThrows;

	public GameState(GameBoard b, int art, boolean isPlayerTime) {
		setCurrentBoard(b);
		setAdversaryRemainingDiceThrows(art);
		setMyTime(isPlayerTime);
	}


	public GameBoard getCurrentBoard() {
		return currentBoard;
	}
	public void setCurrentBoard(GameBoard currentBoard) {
		this.currentBoard = currentBoard;
	}
	public boolean isMyTime() {
		return myTime;
	}
	public void setMyTime(boolean myTime) {
		this.myTime = myTime;
	}
	public int getAdversaryRemainingDiceThrows() {
		return adversaryRemainingDiceThrows;
	}
	public void setAdversaryRemainingDiceThrows(int adversaryRemainingDiceThrows) {
		this.adversaryRemainingDiceThrows = adversaryRemainingDiceThrows;
	}


}
