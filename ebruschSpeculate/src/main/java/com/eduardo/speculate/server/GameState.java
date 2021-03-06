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
	private boolean winner;
	private boolean looser;
	private int adversaryRemainingDiceThrows;
	private int adversaryRemainingBalls;
	private int playerRemainingBalls;
	

	/**
	 *
	 * @param b current game board
	 * @param art adversary remaining rolls
	 * @param arb adversary remaining balls
	 * @param prb players remaining balls
	 * @param isPlayerTime is player time?
	 */
	public GameState(GameBoard b, int art, int arb, int prb, boolean isPlayerTime) {
		setCurrentBoard(b);
		setAdversaryRemainingDiceThrows(art);
		setAdversaryRemainingBalls(arb);
		setPlayerRemainingBalls(prb);
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

	public void declareWinner() {
		winner = true;
	}
	
	public void declareLooser() {
		looser = true;
	}

	public boolean isWinner() {
		return winner;
	}
	
	public boolean isLooser() {
		return looser;
	}

	public int getAdversaryRemainingBalls() {
		return adversaryRemainingBalls;
	}


	public void setAdversaryRemainingBalls(int adversaryRemainingBalls) {
		this.adversaryRemainingBalls = adversaryRemainingBalls;
	}


	public int getPlayerRemainingBalls() {
		return playerRemainingBalls;
	}


	public void setPlayerRemainingBalls(int playerRemainingBalls) {
		this.playerRemainingBalls = playerRemainingBalls;
	}



}
