package com.eduardo.speculate.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.eduardo.speculate.game.GameBoard;
import com.eduardo.speculate.game.Player;
import com.eduardo.speculate.game.SixFaceDice;

public class SpeculateGameServer implements SpeculateRemote{


	static private Integer nextPID = 1;
	private final GameBoard board;
	private final ArrayList<GameRoom> playerLobby;
	private final SixFaceDice dice;

	public SpeculateGameServer() throws RemoteException, NumberFormatException {

		board = new GameBoard();
		playerLobby = new ArrayList<GameRoom>(ServerProperties.MAX_MATCH_COUNT.getInt() * 2 );
		dice = new SixFaceDice();

		dice.equals(new SixFaceDice());
		board.equals(new GameBoard());
	}


	/**
	 * Server Interface
	 * Methods available for the client to call
	 */


	//returns the player iD
	public int getPID() throws RemoteException {
		int pid;
		pid = nextPID;
		insertPlayer(pid);
		++nextPID;
		return pid;
	}



	public int getNewGame(int playerID) throws RemoteException {

		return 0;
	}



	// logic that determines whether it's the current player time based on their ID
	public GameState getNextMove(int playerID) throws RemoteException {

		return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), isNextPlayer(playerID));


	}





	private int getAdversaryRemainingMoves(int playerID) {

		return 0;
	}


	private boolean isNextPlayer(int playerID) {

		return false;
	}

	public GameState makePlayerMove(int numberOfThrows) {
		return null;



	}


	/**
	 * Server utilities
	 * Private Methods to handle game objects according to the server needs
	 */

	private synchronized GameBoard getCurrentBoard() {
		return board;
	}

	private synchronized void insertPlayer(int playerID) {
		/**
		 * TODO
		 * check if there is a game for this player
		 * put 'em there if needed
		 * Create new game if there's space enough
		 */
		GameRoom insertPoint = null;
		boolean found = false;
		for (GameRoom g:playerLobby) {
			if(!g.full()){
				insertPoint = g;
				found = true;
				break;
			}
			if(g==null){
				insertPoint = new GameRoom(new Player(0));
			}
		}

		if(!found){
			;

		}
		insertPoint.addPlayer(new Player(playerID));

	}



	private synchronized void alterPlayerRemoveBall(int playerID) {

	}











}
