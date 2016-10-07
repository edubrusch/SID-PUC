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


	public int getPID() throws RemoteException {

		int pid;
		pid = nextPID;
		if(!insertPlayer(pid)){
			pid = 0;
			/*
			 * if client receives 0, it must say it servers were full.
			 * */
		} else {
			++nextPID;
		}
		return pid;
	}



	// logic that determines whether it's the current player time based on their ID
	//also controls the game state
	public GameState getNextMove(int playerID) throws RemoteException {
		
		GameRoom current = getGameRoom(playerID);

		if(!current.full()) {
			// game didn't began yet
			return null;
		} else {
			// validade for game current state
			if(!current.isOngoingGame()) {
				if(current.isNext(playerID)){
					return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), true);
				} else {
					return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), true);
				}
			} 
		}

		return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), isNextPlayer(playerID));
	}

	public int getNewGame(int playerID) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}



	public GameState makePlayerMove(int numberOfThrows) {

		return null;
	}


	/**
	 * Server utilities
	 * Private Methods to handle game objects according to the server needs
	 */

	private synchronized GameRoom getGameRoom(int playerID) {
		GameRoom insertPoint = null;
		boolean found = false;
		for(int i = 0; i< playerLobby.size(); i++){
			if(playerLobby.get(i) == null){
				insertPoint = new GameRoom();
				playerLobby.add(insertPoint);
				found = true;
				break;
			}
			if(!playerLobby.get(i).full()){
				insertPoint = playerLobby.get(i);
				found = true;
				break;
			}
		}

		if(found){
			insertPoint.addPlayer(new Player(playerID));
		}


		return null;
	}


	private synchronized GameBoard getCurrentBoard() {

		return board;
	}

	private synchronized int getAdversaryRemainingMoves(int playerID) {

		return 0;
	}


	private synchronized boolean isNextPlayer(int playerID) {

		return false;
	}

	private synchronized boolean insertPlayer(int playerID) {

		GameRoom insertPoint = null;
		boolean found = false;
		for(int i = 0; i< playerLobby.size(); i++){
			if(playerLobby.get(i) == null){
				insertPoint = new GameRoom();
				playerLobby.add(insertPoint);
				found = true;
				break;
			}
			if(!playerLobby.get(i).full()){
				insertPoint = playerLobby.get(i);
				found = true;
				break;
			}
		}

		if(found){
			insertPoint.addPlayer(new Player(playerID));
		}

		return found;
	}



	private synchronized void alterPlayerRemoveBall(int playerID) {


	}


	//http://stackoverflow.com/questions/33476910/eclipse-mars-consistently-fails-resolving-imports-after-saving-but-cleaning-pro


}
